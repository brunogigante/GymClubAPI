package com.example.gymclubapi.entity

import java.io.Serializable

class WorkoutExerciseKey() : Serializable {
    lateinit var exercise: Exercise
    lateinit var workout: Workout
}