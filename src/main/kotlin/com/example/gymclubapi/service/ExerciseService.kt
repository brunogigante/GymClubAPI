package com.example.gymclubapi.service

import com.example.gymclubapi.entity.Exercise
import com.example.gymclubapi.entity.ExerciseCategory
import com.example.gymclubapi.repository.ExerciseRepository
import jdk.jfr.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class ExerciseService(private val exerciseRepository: ExerciseRepository) {
    fun getExercises(pageable: Pageable): Page<Exercise> {
        return exerciseRepository.findAll(pageable)
    }

    fun getExercisesCategories(): List<ExerciseCategory>  = ExerciseCategory.values().asList()
}