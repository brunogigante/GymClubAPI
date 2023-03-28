package com.example.gymclubapi.repository

import com.example.gymclubapi.entity.TrainingPlan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainingPlanRepository: JpaRepository<TrainingPlan, Long> {
    fun findTrainingPlanById(id: Long): TrainingPlan?
}