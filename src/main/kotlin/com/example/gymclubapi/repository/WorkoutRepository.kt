package com.example.gymclubapi.repository

import com.example.gymclubapi.entity.Workout
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkoutRepository : JpaRepository<Workout, Long> {
    fun findWorkoutByName(name: String): Workout?
    fun findWorkoutByPlanId(id: Long): MutableList<Workout>
    fun findWorkoutById(id: Long): Workout
}