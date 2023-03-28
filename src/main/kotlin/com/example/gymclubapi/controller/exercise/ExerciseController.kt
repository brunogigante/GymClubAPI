package com.example.gymclubapi.controller.exercise

import com.example.gymclubapi.entity.Exercise
import com.example.gymclubapi.entity.ExerciseCategory
import com.example.gymclubapi.service.ExerciseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("exercises")
class ExerciseController(private val exerciseService: ExerciseService) {
    @GetMapping
    fun findAll(@PageableDefault page: Pageable): Page<Exercise> {
        return exerciseService.getExercises(page)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Exercise? {
        return exerciseService.getExercise(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody exerciseCreationDto: ExerciseCreationDto): Long? {
        return exerciseService.addExercise(exerciseCreationDto)
    }

    @GetMapping("categories")
    fun findAllCategories(): List<ExerciseCategory> = exerciseService.getExercisesCategories()
}