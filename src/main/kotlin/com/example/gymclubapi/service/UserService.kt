package com.example.gymclubapi.service

import com.example.gymclubapi.repository.UserRepository
import org.springframework.stereotype.Component


@Component
class UserService(private val userRepository: UserRepository) {
}