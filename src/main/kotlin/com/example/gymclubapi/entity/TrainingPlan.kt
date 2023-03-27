package com.example.gymclubapi.entity

import jakarta.persistence.*

@Entity
class TrainingPlan(
    @Column val isPublic: Boolean,
    @ManyToOne(targetEntity = TrainingPlan::class)
    val parent: TrainingPlan?
) : BaseEntity()