package com.example.gymclubapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity


@Entity(name = "users")
class User(
    @Column var firstName: String? = null,
    @Column var lastName: String? = null,
    @Column var email: String? = null,
    @Column var password: String? = null,
    @Column var active: Boolean = true
) : BaseEntity()