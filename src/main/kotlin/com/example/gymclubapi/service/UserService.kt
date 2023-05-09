package com.example.gymclubapi.service

import com.example.gymclubapi.controller.user.UserUpdateDto
import com.example.gymclubapi.entity.User
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder) {

    fun getUsers(): List<User> = userRepository.findAll()

    fun getUser(id: Long): User? = userRepository.findById(id).orElseThrow{
        ResourceNotFoundException("User with id $id not found")
    }

    fun getLoggedUser(): User {
        val userEmail = SecurityContextHolder.getContext().authentication.name
        return userRepository.findUserByEmail(userEmail)
    }

    fun deactivateLoggedUser(){
        val user = getLoggedUser()
        user.active = false
    }

    fun updateUser(userUpdateDto: UserUpdateDto){
        val user = getLoggedUser()
        user.let { userAux ->
            userUpdateDto.email?.let { userAux.email = it }
            userUpdateDto.firstName?.let { userAux.firstName = it }
            userUpdateDto.lastName?.let { userAux.lastName = it }
            userUpdateDto.password?.let { userAux.password = passwordEncoder.encode(it) }
        }
        userRepository.save(user)
    }
}