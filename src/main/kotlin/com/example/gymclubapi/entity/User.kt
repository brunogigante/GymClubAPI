package com.example.gymclubapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity


@Entity(name = "users")
class User(
    @Column val firstName: String? = null,
    @Column val lastName: String? = null,
    @Column val email: String? = null,
    @Column val password: String? = null
) : BaseEntity()