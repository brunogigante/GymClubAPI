package com.example.gymclubapi.entity

import jakarta.persistence.*

@Entity
class Workout (
    @Column val name: String,
    @ManyToOne(targetEntity = TrainingPlan::class)
    val plan: TrainingPlan
) : BaseEntity()