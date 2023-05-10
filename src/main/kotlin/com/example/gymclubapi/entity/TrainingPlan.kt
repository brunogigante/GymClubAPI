package com.example.gymclubapi.entity

import jakarta.persistence.*

@Entity
class TrainingPlan(
    @Column var name: String,
    @Column var isPublic: Boolean
) : BaseEntity() {
    @ManyToOne(targetEntity = User::class)
    var creator: User? = null
    @ManyToOne(targetEntity = TrainingPlan::class)
    var parent: TrainingPlan? = null
    @Column var enabled: Boolean = true
}