package com.example.gymclubapi.service

import com.example.gymclubapi.controller.trainingPlan.TrainingPlanUpdateDto
import com.example.gymclubapi.entity.Exercise
import com.example.gymclubapi.entity.TrainingPlan
import com.example.gymclubapi.entity.Workout
import com.example.gymclubapi.entity.WorkoutExercise
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.*
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class TrainingPlanService(
    val trainingPlanRepository: TrainingPlanRepository,
    val workoutRepository: WorkoutRepository,
    val userRepository: UserRepository,
    val exerciseRepository: ExerciseRepository,
    val workoutExerciseRepository: WorkoutExerciseRepository
) {

    fun getTrainingPlans(): List<TrainingPlan> = trainingPlanRepository.findTrainingPlanByEnabled(true)

    fun getTrainingPlan(id: Long): TrainingPlan = trainingPlanRepository.findById(id).orElseThrow {
        ResourceNotFoundException("Training plan with id $id doesn't exist!")
    }

    fun addTrainingPlan(trainingPlanName: String, trainingPlanIsPublic: Boolean): Long? {
        val trainingPlan = TrainingPlan(trainingPlanName, trainingPlanIsPublic)
        val userEmail = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findUserByEmail(userEmail)
        trainingPlan.creator = user
        val trainingPlanById = trainingPlan.id?.let { getTrainingPlan(it) }
        if (trainingPlanById != null)
            throw IllegalStateException("Training plan with id ${trainingPlan.id} already exists!")
        return trainingPlanRepository.save(trainingPlan).id
    }

    fun cloneTrainingPlan(parentPlanId: Long): Long? {
        val parentPlan = getTrainingPlan(parentPlanId)
        if (!parentPlan.isPublic)
            throw IllegalStateException("You cannot access this plan!")
        val newPlanId = cloneObject(parentPlan, parentPlan)
        val newPlan = newPlanId?.let { trainingPlanRepository.findTrainingPlanById(it) }
        val creator = userRepository.findUserByEmail(SecurityContextHolder.getContext().authentication.name)
        if (newPlan != null) {
            newPlan.creator = creator
        }
        val parentWorkouts = workoutRepository.findWorkoutByPlanId(parentPlanId)
        val workouts = mutableMapOf<Long, Long>()
        val exercises = mutableMapOf<Long, Long>()
        var parentExercises: MutableList<Exercise>
        val parentWorkoutExercises = mutableListOf<WorkoutExercise>()
        parentWorkouts.forEach { workoutIt ->
            workoutIt.id?.let {
                if (newPlan != null) {
                    cloneObject(workoutIt, newPlan)?.let { workoutItId ->
                        workouts.put(
                            it,
                            workoutItId
                        )
                    }
                }
            }
            parentExercises = workoutExerciseRepository.findExercisesByWorkout(workoutIt)
            parentExercises.forEach { exerciseIt ->
                exerciseIt.id?.let {
                    if (newPlan != null) {
                        cloneObject(exerciseIt, newPlan)?.let { exerciseItId ->
                            exercises.put(
                                it,
                                exerciseItId
                            )
                        }
                    }
                }
                workoutExerciseRepository.findWorkoutExerciseByWorkoutAndExercise(workoutIt, exerciseIt)?.let {
                    parentWorkoutExercises.add(
                        it
                    )
                }
                parentWorkoutExercises.forEach { workoutExerciseIt ->

                    if (workouts.containsKey(workoutExerciseIt.workout.id) && exercises.containsKey(workoutExerciseIt.exercise.id)
                    ) {
                        val findWorkoutById = workouts[workoutExerciseIt.workout.id]?.let {
                            workoutRepository.findWorkoutById(
                                it
                            )
                        }
                        val findExerciseById = exercises[workoutExerciseIt.exercise.id]?.let {
                            exerciseRepository.findExerciseById(
                                it
                            )
                        }
                        if (findWorkoutById != null) {
                            if (findExerciseById != null) {
                                cloneObject(WorkoutExercise(findWorkoutById, findExerciseById), parentPlan)
                            }
                        }
                    }
                }
            }
        }
        return newPlanId
    }

    fun <T> cloneObject(instance: T, parentPlan: TrainingPlan): Long? {
        when (instance) {
            is Workout -> {
                val workout = Workout(instance.name, instance.description, parentPlan)
                return workout.let { workoutRepository.save(it).id }
            }

            is Exercise -> {
                val exercise = Exercise(instance.name, instance.description, instance.category)
                return exerciseRepository.save(exercise).id
            }

            is WorkoutExercise -> {
                val workoutExercise = WorkoutExercise(instance.workout, instance.exercise)
                workoutExerciseRepository.save(workoutExercise)
            }

            is TrainingPlan -> {
                val trainingPlan = TrainingPlan(instance.name, false)
                trainingPlan.parent = parentPlan
                return trainingPlanRepository.save(trainingPlan).id
            }
        }
        return null
    }

    fun addWorkoutToPlan(trainingPlanId: Long, workoutName: String, workoutDescription: String): Long? {
        val plan = getTrainingPlan(trainingPlanId)
        val workout = Workout(workoutName, workoutDescription, plan)
        val workoutByName = workoutRepository.findWorkoutByName(workout.name)
        if (workoutByName != null)
            throw IllegalStateException("Workout with name ${workout.name} already exists!")
        return workoutRepository.save(workout).id
    }

    fun deletePlan(trainingPlanId: Long) {
        val plan = getTrainingPlan(trainingPlanId)
        plan.enabled = false
        trainingPlanRepository.save(plan)
    }

    fun updatePlan(trainingPlanId: Long, trainingPlanUpdateDto: TrainingPlanUpdateDto) {
        getTrainingPlan(trainingPlanId).let { plan ->
            trainingPlanUpdateDto.name?.let { plan.name = it }
            trainingPlanUpdateDto.isPublic?.let { plan.isPublic = it }
            trainingPlanRepository.save(plan)
        }
    }

    fun getUserTrainingPlans(): List<TrainingPlan> {
        val userEmail = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findUserByEmail(userEmail)
        return trainingPlanRepository.findTrainingPlanByCreatorAndEnabled(user, true)
    }

    fun getPublicPlans(): List<TrainingPlan> {
        return trainingPlanRepository.findTrainingPlanByPublicAndEnabled()
    }
}