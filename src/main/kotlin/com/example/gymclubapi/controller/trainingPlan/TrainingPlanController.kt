package com.example.gymclubapi.controller.trainingPlan

import com.example.gymclubapi.controller.workout.WorkoutCreationDto
import com.example.gymclubapi.controller.workoutExercise.WorkoutExerciseDto
import com.example.gymclubapi.controller.workoutExerciseSet.WorkoutExerciseSetDto
import com.example.gymclubapi.entity.Exercise
import com.example.gymclubapi.entity.TrainingPlan
import com.example.gymclubapi.entity.Workout
import com.example.gymclubapi.entity.WorkoutExerciseSet
import com.example.gymclubapi.service.TrainingPlanService
import com.example.gymclubapi.service.WorkoutExerciseService
import com.example.gymclubapi.service.WorkoutExerciseSetService
import com.example.gymclubapi.service.WorkoutService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("plans")
class TrainingPlanController(
    val trainingPlanService: TrainingPlanService,
    val workoutService: WorkoutService,
    val workoutExerciseService: WorkoutExerciseService,
    val workoutExerciseSetService: WorkoutExerciseSetService
) {
    @GetMapping
    fun findAll(@PageableDefault page: Pageable): Page<TrainingPlan> {
        return trainingPlanService.getTrainingPlans(page)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TrainingPlan? {
        return trainingPlanService.getTrainingPlan(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody trainingPlanDto: TrainingPlanDto): Long? {
        val trainingPlanName = trainingPlanDto.name
        val trainingPlanIsPublic = trainingPlanDto.isPublic
        val trainingPlanParentId = trainingPlanDto.trainingPlanId
        return trainingPlanService.addTrainingPlan(trainingPlanName, trainingPlanIsPublic, trainingPlanParentId)
    }

    @GetMapping("/workouts")
    fun findAllWorkouts(@PageableDefault page: Pageable): Page<Workout> {
        return workoutService.getWorkouts(page)
    }

    @GetMapping("{id}/workouts")
    fun findPlanWorkouts(@PathVariable id: Long): MutableList<Workout> {
        return workoutService.getPlanWorkouts(id)
    }

    @GetMapping("/workouts/{id}")
    fun findWorkoutById(@PathVariable id: Long): Workout? {
        return workoutService.getWorkout(id)
    }

    @PostMapping("{id}/workouts")
    @ResponseStatus(HttpStatus.CREATED)
    fun createWorkout(@PathVariable id: Long, @RequestBody workoutCreationDto: WorkoutCreationDto): Long? {
        val workoutName = workoutCreationDto.name
        return trainingPlanService.addWorkoutToPlan(id, workoutName)
    }

    @PostMapping("/workouts/{workoutId}/exercises")
    @ResponseStatus(HttpStatus.CREATED)
    fun addExerciseToWorkout(
        @PathVariable workoutId: Long,
        @RequestBody workoutExerciseDto: WorkoutExerciseDto
    ) {
        val exerciseId = workoutExerciseDto.exerciseId
        return workoutExerciseService.addExerciseToWorkout(exerciseId, workoutId)
    }

    @GetMapping("/workouts/{workoutId}/exercises")
    fun findWorkoutExercises(@PathVariable workoutId: Long): MutableList<Exercise> {
        return workoutExerciseService.findWorkoutExercises(workoutId)
    }

    @GetMapping("/workouts/exercises/sets/{setId}")
    fun findSetById(@PathVariable setId: Long): WorkoutExerciseSet? {
        return workoutExerciseSetService.getSet(setId)
    }

    @GetMapping("/workouts/exercises/sets")
    fun findAllSets(@PageableDefault page: Pageable): Page<WorkoutExerciseSet> {
        return workoutExerciseSetService.getSets(page)
    }

    @GetMapping("/workouts/{workoutId}/exercises/{exerciseId}/sets")
    fun findWorkoutExerciseSet(
        @PathVariable workoutId: Long,
        @PathVariable exerciseId: Long
    ): MutableList<WorkoutExerciseSet> {
        return workoutExerciseSetService.getWorkoutExerciseSets(workoutId, exerciseId)
    }

    @PostMapping("/workouts/{workoutId}/exercises/{exerciseId}/sets")
    @ResponseStatus(HttpStatus.CREATED)
    fun createWorkoutExerciseSet(
        @PathVariable workoutId: Long,
        @PathVariable exerciseId: Long,
        @RequestBody workoutExerciseSetDto: WorkoutExerciseSetDto
    ): Long? {
        val repetitions = workoutExerciseSetDto.repetitions
        val weight = workoutExerciseSetDto.weight
        return workoutExerciseSetService.createSet(workoutId, exerciseId, repetitions, weight)
    }
}