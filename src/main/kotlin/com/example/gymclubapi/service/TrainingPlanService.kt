package com.example.gymclubapi.service

import com.example.gymclubapi.controller.trainingPlan.TrainingPlanUpdateDto
import com.example.gymclubapi.entity.TrainingPlan
import com.example.gymclubapi.entity.Workout
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.TrainingPlanRepository
import com.example.gymclubapi.repository.UserRepository
import com.example.gymclubapi.repository.WorkoutRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class TrainingPlanService(
    val trainingPlanRepository: TrainingPlanRepository,
    val workoutRepository: WorkoutRepository,
    val userRepository: UserRepository
) {

    fun getTrainingPlans(): List<TrainingPlan> = trainingPlanRepository.findAll()

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

    fun cloneTrainingPlan(parentPlanId: Long): Long?{
        val parentPlan = getTrainingPlan(parentPlanId)
        return 1
    }

    fun addWorkoutToPlan(trainingPlanId: Long, workoutName: String, workoutDescription: String): Long? {
        val plan = getTrainingPlan(trainingPlanId)
        val workout = Workout(workoutName,workoutDescription, plan)
        val workoutByName = workoutRepository.findWorkoutByName(workout.name)
        if (workoutByName != null)
            throw IllegalStateException("Workout with name ${workout.name} already exists!")
        return workoutRepository.save(workout).id
    }

    fun deletePlan(trainingPlanId: Long) {
        val plan = getTrainingPlan(trainingPlanId)
        return trainingPlanRepository.delete(plan)
    }

    fun updatePlan(trainingPlanId: Long, trainingPlanUpdateDto: TrainingPlanUpdateDto){
        getTrainingPlan(trainingPlanId).let { plan ->
            trainingPlanUpdateDto.name?.let { plan.name = it }
            trainingPlanUpdateDto.isPublic?.let { plan.isPublic = it }
            trainingPlanRepository.save(plan)
        }
    }

    fun getUserTrainingPlans(): List<TrainingPlan>{
        val userEmail = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findUserByEmail(userEmail)
        return trainingPlanRepository.findTrainingPlanByCreator(user)
    }
}