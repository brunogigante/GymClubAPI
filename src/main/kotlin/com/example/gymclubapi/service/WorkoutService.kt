package com.example.gymclubapi.service

import com.example.gymclubapi.entity.Workout
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.TrainingPlanRepository
import com.example.gymclubapi.repository.WorkoutRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class WorkoutService(
    private val workoutRepository: WorkoutRepository,
    private val trainingPlanRepository: TrainingPlanRepository
) {

    fun getWorkouts(): List<Workout>{
        val workouts: MutableList<Workout> = workoutRepository.findAll()
        workouts.forEach {
            if(!it.plan.enabled){
                workouts.remove(it)
            }
        }
        return workouts
    }

    fun getWorkout(id: Long): Workout = workoutRepository.findById(id).orElseThrow {
        ResourceNotFoundException("Workout with id $id doesn't exist!")
    }

    fun getPlanWorkouts(id: Long): MutableList<Workout> {
        if(trainingPlanRepository.findTrainingPlanById(id) == null)
            throw ResourceNotFoundException("Training plan with id $id doesn't exist!")
        return workoutRepository.findWorkoutByPlanId(id)
    }
}