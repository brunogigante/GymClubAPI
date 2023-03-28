package com.example.gymclubapi.entity

import jakarta.persistence.*

@Entity
class TrainingSession(
    @ManyToOne(targetEntity = User::class)
    val creator: User
) : BaseEntity() {
    @OneToMany(targetEntity = Workout::class)
    val workouts: MutableList<Workout> = mutableListOf()
}