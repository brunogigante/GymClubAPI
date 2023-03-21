package com.example.gymclubapi.entity

import jakarta.persistence.*


enum class ExerciseCategory(
)

@Entity
class Exercise(
    @Column val name: String,
    @Column val description: String,
    @Column val category: ExerciseCategory
) : BaseEntity()