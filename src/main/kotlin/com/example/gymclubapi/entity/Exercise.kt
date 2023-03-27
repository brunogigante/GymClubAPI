package com.example.gymclubapi.entity

import jakarta.persistence.*


enum class ExerciseCategory{
    Test
}

@Entity
class Exercise(
    @Column val name: String,
    @Column val description: String,
    @Column val category: ExerciseCategory
) : BaseEntity()