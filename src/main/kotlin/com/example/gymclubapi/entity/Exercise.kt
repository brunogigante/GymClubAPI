package com.example.gymclubapi.entity

import jakarta.persistence.*


enum class ExerciseCategory{
    CHEST,
    BACK,
    LEGS,
    CALVES,
    SHOULDERS,
    BICEPS,
    TRICEPS,
    ABS,
    DELTOIDS
}

@Entity
class Exercise(
    @Column val name: String,
    @Column val description: String,
    @Column val category: ExerciseCategory,
    @Column val repetitions: Int,
    @Column val weight: Int
) : BaseEntity()