package com.example.gymclubapi.service

import com.example.gymclubapi.entity.Workout
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.WorkoutRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class WorkoutService (private val workoutRepository: WorkoutRepository) {

    fun getWorkouts(pageable: Pageable): Page<Workout>{
        return workoutRepository.findAll(pageable)
    }

    fun getWorkout(id: Long): Workout? = workoutRepository.findById(id).orElseThrow{
        ResourceNotFoundException("Workout with id $id doesn't exist!")
    }


}