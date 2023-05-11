package com.example.gymclubapi.controller.exercise

import com.example.gymclubapi.entity.Exercise
import com.example.gymclubapi.entity.ExerciseCategory
import com.example.gymclubapi.service.ExerciseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("exercises")
class ExerciseController(
    private val exerciseService: ExerciseService
) {
    @GetMapping
    fun findAll(): List<Exercise> {
        return exerciseService.getExercises()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Exercise? {
        return exerciseService.getExercise(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody exerciseCreationDto: ExerciseCreationDto): Long? {
        val exerciseName = exerciseCreationDto.name
        val exerciseCategory = exerciseCreationDto.category
        val exerciseDescription = exerciseCreationDto.description
        val exerciseRepetitions = exerciseCreationDto.repetitions
        val exerciseWeight = exerciseCreationDto.weight
        return exerciseService.addExercise(exerciseName, exerciseDescription, exerciseCategory, exerciseRepetitions, exerciseWeight)
    }

    @GetMapping("categories")
    fun findAllCategories(): List<ExerciseCategory> = exerciseService.getExercisesCategories()
}