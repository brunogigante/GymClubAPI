package com.example.gymclubapi.entity

import jakarta.persistence.*

@Entity
class Workout (
    @ManyToOne(targetEntity = TrainingPlan::class)
    val plan: TrainingPlan
) : BaseEntity()