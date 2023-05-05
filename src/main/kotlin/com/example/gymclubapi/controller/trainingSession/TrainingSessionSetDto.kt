package com.example.gymclubapi.controller.trainingSession

data class TrainingSessionSetDto (
    val repetitions: Int,
    val weight: Int,
    val trainingSessionId: Long,
    val workoutExerciseSetId: Long
)