package com.example.gymclubapi.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.ManyToOne

@Entity
@IdClass(WorkoutExerciseKey::class)
class WorkoutExercise(
    @Id
    @ManyToOne(targetEntity = Workout::class)
    val workout: Workout,
    @Id
    @ManyToOne(targetEntity = Exercise::class)
    val exercise: Exercise
)