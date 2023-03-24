package com.example.gymclubapi.controller.exercice

import com.example.gymclubapi.entity.Exercise
import com.example.gymclubapi.entity.ExerciseCategory
import com.example.gymclubapi.service.ExerciseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("exercises")
class ExerciseController(private val exerciseService: ExerciseService) {
    @GetMapping
    fun findAll(@PageableDefault page: Pageable): Page<Exercise> {
        return exerciseService.getExercises(page)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody() exerciseCreationDto: ExerciseCreationDto) {
       exerciseCreationDto
    }

    @GetMapping("categories")
    fun findAllCategories(): List<ExerciseCategory> = exerciseService.getExercisesCategories()
}