package com.example.gymclubapi.controller.trainingPlan

import com.example.gymclubapi.controller.workout.WorkoutCreationDto
import com.example.gymclubapi.entity.TrainingPlan
import com.example.gymclubapi.entity.Workout
import com.example.gymclubapi.service.TrainingPlanService
import com.example.gymclubapi.service.WorkoutService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("plans")
class TrainingPlanController(val trainingPlanService: TrainingPlanService, val workoutService: WorkoutService) {
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
    fun create(@RequestBody trainingPlanDto: TrainingPlanDto): Long?{
        return trainingPlanService.addTrainingPlan(trainingPlanDto)
    }

    @GetMapping("/workouts")
    fun findAllWorkouts(@PageableDefault page: Pageable): Page<Workout>{
        return workoutService.getWorkouts(page)
    }

    @GetMapping("{id}/workouts")
    fun findAllPlanWorkouts(@PathVariable id: Long): MutableList<Workout> {
        return workoutService.getPlanWorkouts(id)
    }

    @GetMapping("/workouts/{id}")
    fun findWorkoutsById(@PathVariable id: Long): Workout?{
        return workoutService.getWorkout(id)
    }

    @PostMapping("{id}/workouts")
    @ResponseStatus(HttpStatus.CREATED)
    fun createWorkout(@PathVariable id:Long, @RequestBody workoutCreationDto: WorkoutCreationDto): Long?{
        return trainingPlanService.addWorkoutToPlan(id, workoutCreationDto)
    }
}

