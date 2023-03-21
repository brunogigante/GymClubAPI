package com.example.gymclubapi.entity

import jakarta.persistence.*

@Entity
class WorkoutExerciseSet(
    @Column val repetitions: Int,
    @Column val weight: Int,
    @ManyToOne(targetEntity = Exercise::class)
    val exercise: Exercise
) : BaseEntity()