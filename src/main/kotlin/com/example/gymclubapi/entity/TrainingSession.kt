package com.example.gymclubapi.entity

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
class TrainingSession(
    @ManyToOne(targetEntity = User::class)
    val creator: User,
    @OneToMany(targetEntity = Workout::class)
    val workouts: MutableList<Workout> = mutableListOf()
) : BaseEntity()