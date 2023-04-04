package com.example.gymclubapi.entity

import jakarta.persistence.*

@Entity
class TrainingSession(
    @ManyToOne(targetEntity = User::class)
    val creator: User,
    @ManyToOne(targetEntity = Workout::class)
    val workout: Workout
) : BaseEntity()