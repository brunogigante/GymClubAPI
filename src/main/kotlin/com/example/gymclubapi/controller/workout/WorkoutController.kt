package com.example.gymclubapi.controller.workout

import com.example.gymclubapi.entity.Workout
import com.example.gymclubapi.service.WorkoutService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("workouts")
class WorkoutController(private val workoutService: WorkoutService) {

    @GetMapping
    fun findAll(@PageableDefault page: Pageable): Page<Workout>{
        return workoutService.getWorkouts(page)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Workout?{
        return workoutService.getWorkout(id)
    }
}