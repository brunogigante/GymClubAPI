package com.example.gymclubapi.repository

import com.example.gymclubapi.entity.WorkoutExercise
import com.example.gymclubapi.entity.WorkoutExerciseSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkoutExerciseSetRepository: JpaRepository<WorkoutExerciseSet, Long> {
    fun findWorkoutExerciseSetByWorkoutExercise(workoutExercise: WorkoutExercise): MutableList<WorkoutExerciseSet>
}