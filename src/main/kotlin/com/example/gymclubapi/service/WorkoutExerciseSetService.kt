package com.example.gymclubapi.service

import com.example.gymclubapi.entity.WorkoutExerciseSet
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.WorkoutExerciseSetRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class WorkoutExerciseSetService(
    private val workoutExerciseSetRepository: WorkoutExerciseSetRepository,
    private val workoutExerciseService: WorkoutExerciseService
) {

    fun getSets(page: Pageable): Page<WorkoutExerciseSet> {
        return workoutExerciseSetRepository.findAll(page)
    }

    fun getSet(setId: Long): WorkoutExerciseSet? = workoutExerciseSetRepository.findById(setId).orElseThrow {
        ResourceNotFoundException("Workout exercise set with id $setId doesn't exist!")
    }

    fun getWorkoutExerciseSets(workoutId: Long, exerciseId: Long): MutableList<WorkoutExerciseSet> {
        val workoutExercise = workoutExerciseService.getWorkoutExercise(workoutId, exerciseId)
        return workoutExerciseSetRepository.findWorkoutExerciseSetByWorkoutExercise(workoutExercise)
    }


    fun createSet(workoutId: Long, exerciseId: Long, repetitions: Int, weight: Int): Long? {
        val workoutExercise = workoutExerciseService.getWorkoutExercise(workoutId, exerciseId)
        val exerciseSet = WorkoutExerciseSet(repetitions, weight, workoutExercise)
        return workoutExerciseSetRepository.save(exerciseSet).id
    }
}