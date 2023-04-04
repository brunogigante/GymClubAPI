package com.example.gymclubapi.security

import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    @Autowired
    private val userRepository: UserRepository
) : UserDetailsService {
    fun loadUserByEmail(email: String): UserDetails {
        val user = userRepository.findUserByEmail(email)
            ?: throw ResourceNotFoundException("User with email $email not found!")
        return User(user.email, user.password, listOf())
    }

    override fun loadUserByUsername(username: String?): UserDetails? {
        return null
    }
}