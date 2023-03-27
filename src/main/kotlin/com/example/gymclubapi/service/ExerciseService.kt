package com.example.gymclubapi.service

import com.example.gymclubapi.entity.Exercise
import com.example.gymclubapi.entity.ExerciseCategory
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.ExerciseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class ExerciseService(private val exerciseRepository: ExerciseRepository) {
    fun getExercises(pageable: Pageable): Page<Exercise> {
        return exerciseRepository.findAll(pageable)
    }

    fun getExercise(id: Long): Exercise? = exerciseRepository.findById(id).orElseThrow{
        ResourceNotFoundException("Exercise with id $id not found")
    }

    fun getExercisesCategories(): List<ExerciseCategory> = ExerciseCategory.values().asList()

    fun addExercise(exercise: Exercise): Long? {
        val exerciseByName = exerciseRepository.findExerciseByName(exercise.name)
        if (exerciseByName != null) {
            throw IllegalStateException("Exercised named ${exerciseByName.name} already exists!")
        }
        return exerciseRepository.save(exercise).id
    }
}