package com.example.gymclubapi.repository

import com.example.gymclubapi.entity.TrainingPlan
import com.example.gymclubapi.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TrainingPlanRepository: JpaRepository<TrainingPlan, Long> {
    fun findTrainingPlanById(id: Long): TrainingPlan?
    fun findTrainingPlanByCreatorAndEnabled(creator: User, status: Boolean): List<TrainingPlan>
    fun findTrainingPlanByEnabled(status: Boolean): List<TrainingPlan>

    @Query("SELECT tp FROM TrainingPlan tp WHERE tp.enabled = true AND tp.isPublic = true")
    fun findTrainingPlanByPublicAndEnabled(): List<TrainingPlan>
}