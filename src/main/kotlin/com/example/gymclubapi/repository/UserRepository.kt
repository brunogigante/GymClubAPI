package com.example.gymclubapi.repository


import com.example.gymclubapi.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findUserByEmail(email: String): User?
    fun existsUserByEmail(email: String): Boolean
}