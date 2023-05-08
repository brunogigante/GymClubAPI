package com.example.gymclubapi.service

import com.example.gymclubapi.entity.User
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component


@Component
class UserService(private val userRepository: UserRepository) {

    fun getUsers(): List<User> = userRepository.findAll()

    fun getUser(id: Long): User? = userRepository.findById(id).orElseThrow{
        ResourceNotFoundException("User with id $id not found")
    }

    fun getLoggedUser(): User {
        val userEmail = SecurityContextHolder.getContext().authentication.name
        return userRepository.findUserByEmail(userEmail)
    }
}