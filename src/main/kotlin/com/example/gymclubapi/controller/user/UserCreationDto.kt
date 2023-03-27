package com.example.gymclubapi.controller.user

data class UserCreationDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)