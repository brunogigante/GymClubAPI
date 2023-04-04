package com.example.gymclubapi.entity

import jakarta.persistence.*

@Entity
class WorkoutExerciseSet(
    @Column val repetitions: Int,
    @Column val weight: Int,
    @ManyToOne(targetEntity = WorkoutExercise::class)
    val workoutExercise: WorkoutExercise
) : BaseEntity()