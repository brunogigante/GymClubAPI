package com.example.gymclubapi.controller.auth

data class AuthResponseDto(val accessToken: String){
    val tokenType = "Bearer "
}