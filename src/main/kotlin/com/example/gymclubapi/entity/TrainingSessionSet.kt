package com.example.gymclubapi.entity

import jakarta.persistence.*

@Entity
class TrainingSessionSet(
    @Column val repetitions: Int,
    @Column val weight: Int,
    @ManyToOne(targetEntity = TrainingSession::class)
    val session: TrainingSession,
    @ManyToOne(targetEntity = WorkoutExerciseSet::class)
    val sessionSet: WorkoutExerciseSet
) : BaseEntity()