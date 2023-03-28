package com.example.gymclubapi.service

import com.example.gymclubapi.controller.trainingPlan.TrainingPlanDto
import com.example.gymclubapi.controller.workout.WorkoutCreationDto
import com.example.gymclubapi.entity.TrainingPlan
import com.example.gymclubapi.entity.Workout
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.TrainingPlanRepository
import com.example.gymclubapi.repository.WorkoutRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class TrainingPlanService(val trainingPlanRepository: TrainingPlanRepository, val workoutRepository: WorkoutRepository) {

    fun getTrainingPlans(pageable: Pageable): Page<TrainingPlan> = trainingPlanRepository.findAll(pageable)

    fun getTrainingPlan(id: Long): TrainingPlan? = trainingPlanRepository.findById(id).orElseThrow{
        ResourceNotFoundException("Training plan with id $id doesn't exist!")
    }

    fun addTrainingPlan(trainingPlanDto: TrainingPlanDto): Long?{
        val trainingPlanReference = trainingPlanDto.trainingPlanId?.let { getTrainingPlan(it) }
        val trainingPlan = TrainingPlan(trainingPlanDto.name, trainingPlanDto.isPublic, trainingPlanReference)
        val trainingPlanById = trainingPlan.id?.let { getTrainingPlan(it) }
        if(trainingPlanById != null)
            throw IllegalStateException("Training plan with id ${trainingPlan.id} already exists!")
        return trainingPlanRepository.save(trainingPlan).id
    }

    fun addWorkoutToPlan(trainingPlanId: Long, workoutDto: WorkoutCreationDto): Long?{
        val plan = getTrainingPlan(trainingPlanId)
            ?: throw ResourceNotFoundException("Training plan with id $trainingPlanId doesn't exist!")
        val workout = Workout(workoutDto.name, plan)
        val workoutByName = workoutRepository.findWorkoutByName(workout.name)
        if(workoutByName != null)
            throw IllegalStateException("Workout with name ${workout.name} already exists!")
        return workoutRepository.save(workout).id
    }
}
