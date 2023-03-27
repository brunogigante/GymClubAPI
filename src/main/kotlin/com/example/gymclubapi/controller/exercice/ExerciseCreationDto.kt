package com.example.gymclubapi.controller.exercice

import com.example.gymclubapi.entity.ExerciseCategory

data class ExerciseCreationDto(
    val name: String,
    val description: String,
    val category: ExerciseCategory
)
