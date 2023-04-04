package com.example.gymclubapi.service

import com.example.gymclubapi.entity.Exercise
import com.example.gymclubapi.entity.WorkoutExercise
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.WorkoutExerciseRepository
import org.springframework.stereotype.Component

@Component
class WorkoutExerciseService(
    private val workoutExerciseRepository: WorkoutExerciseRepository,
    private val exerciseService: ExerciseService,
    private val workoutService: WorkoutService
) {
    fun getWorkoutExercise(workoutId: Long, exerciseId: Long): WorkoutExercise {
        val workout = workoutService.getWorkout(workoutId)
        val exercise = exerciseService.getExercise(exerciseId)
        return workoutExerciseRepository.findWorkoutExerciseByWorkoutAndExercise(workout, exercise)
            ?: throw ResourceNotFoundException(
                "Workout Exercise with workout id $workoutId and exercise id $exerciseId doesn't exist!"
            )
    }

    fun addExerciseToWorkout(exerciseId: Long, workoutId: Long) {
        val exercise = exerciseService.getExercise(exerciseId)
        val workout = workoutService.getWorkout(workoutId)
        val exerciseWorkout = WorkoutExercise(workout, exercise)
        workoutExerciseRepository.save(exerciseWorkout)
    }

    fun findWorkoutExercises(workoutId: Long): MutableList<Exercise> {
        val workout = workoutService.getWorkout(workoutId)
        return workoutExerciseRepository.findExercisesByWorkout(workout)
    }
}