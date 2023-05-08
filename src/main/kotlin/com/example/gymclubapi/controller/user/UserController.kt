package com.example.gymclubapi.controller.user

import com.example.gymclubapi.entity.User
import com.example.gymclubapi.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(private val userService: UserService) {
    @GetMapping
    fun findAll(): List<User> {
        return userService.getUsers()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): User? {
        return userService.getUser(id)
    }

    @GetMapping("/authenticated")
    fun findAuthenticated(): User {
        return userService.getLoggedUser()
    }
}