package com.example.gymclubapi.entity

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
class TrainingSessionSet(
    @Column val repetitions: Int,
    @Column val weight: Int,
    @ManyToOne(targetEntity = TrainingSession::class)
    val session: TrainingSession,
    @ManyToOne(targetEntity = WorkoutExerciseSet::class)
    val sessionSet: WorkoutExerciseSet
) : BaseEntity()