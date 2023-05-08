package com.example.gymclubapi.entity

import jakarta.persistence.*

@Entity
class TrainingPlan(
    @Column var name: String,
    @Column var isPublic: Boolean
) : BaseEntity() {
    @ManyToOne(targetEntity = TrainingPlan::class)
    val parent: TrainingPlan? = null
}