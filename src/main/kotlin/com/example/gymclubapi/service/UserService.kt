package com.example.gymclubapi.service

import com.example.gymclubapi.entity.User
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component


@Component
class UserService(private val userRepository: UserRepository) {

    fun getUsers(pageable: Pageable): Page<User> {
        return userRepository.findAll(pageable)
    }

    fun getUser(id: Long): User? = userRepository.findById(id).orElseThrow{
        ResourceNotFoundException("User with id $id not found")
    }

    fun addUser(user: User): Long?{
        val userByEmail = userRepository.findUserByEmail(user.email)
        if(userByEmail != null){
            throw IllegalStateException("User with email ${user.email} already exists!")
        }
        return userRepository.save(user).id
    }
}