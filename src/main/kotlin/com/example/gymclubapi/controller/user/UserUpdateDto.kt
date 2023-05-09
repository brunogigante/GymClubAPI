package com.example.gymclubapi.controller.user

data class UserUpdateDto(
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val password: String?
)