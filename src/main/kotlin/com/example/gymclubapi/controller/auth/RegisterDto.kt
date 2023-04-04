package com.example.gymclubapi.controller.auth

data class RegisterDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)