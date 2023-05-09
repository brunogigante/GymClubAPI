package com.example.gymclubapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Gym(
    @Column val name: String,
    @Column val latitude: Float,
    @Column val longitude: Float
) : BaseEntity() {
}