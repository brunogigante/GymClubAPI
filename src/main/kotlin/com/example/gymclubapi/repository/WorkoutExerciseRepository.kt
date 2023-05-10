package com.example.gymclubapi.repository

import com.example.gymclubapi.entity.Exercise
import com.example.gymclubapi.entity.Workout
import com.example.gymclubapi.entity.WorkoutExercise
import com.example.gymclubapi.entity.WorkoutExerciseKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface WorkoutExerciseRepository: JpaRepository<WorkoutExercise, WorkoutExerciseKey> {
    @Query("SELECT we.exercise FROM WorkoutExercise we WHERE we.workout = :workout")
    fun findExercisesByWorkout(@Param("workout") workout: Workout): MutableList<Exercise>
    fun findWorkoutExerciseByWorkoutAndExercise(workout: Workout, exercise: Exercise): WorkoutExercise?
    fun findWorkoutExerciseByWorkout(workout: Workout): List<WorkoutExercise>
}