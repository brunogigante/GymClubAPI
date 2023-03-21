package com.example.gymclubapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.context.annotation.Primary
import java.sql.Timestamp

@Entity(name = "users")
class User(
    @Column val firstName: String,
    @Column val lastName: String,
    @Column val email: String,
    @Column val password: String
) : BaseEntity()