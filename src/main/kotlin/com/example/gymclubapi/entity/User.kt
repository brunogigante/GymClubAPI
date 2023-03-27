package com.example.gymclubapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity


@Entity(name = "users")
class User(
    @Column val firstName: String,
    @Column val lastName: String,
    @Column val email: String,
    @Column val password: String
) : BaseEntity()