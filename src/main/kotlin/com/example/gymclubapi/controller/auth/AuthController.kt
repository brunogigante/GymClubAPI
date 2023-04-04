package com.example.gymclubapi.controller.auth

import com.example.gymclubapi.entity.User
import com.example.gymclubapi.repository.UserRepository
import com.example.gymclubapi.security.JwtGenerator
import org.apache.coyote.Response
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtGenerator: JwtGenerator
) {

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<AuthResponseDto>{
        val email = loginDto.email
        val password = loginDto.password
        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email, password))
        SecurityContextHolder.getContext().authentication = authentication
        val token = jwtGenerator.generateToken(authentication)
        return ResponseEntity(AuthResponseDto(token), HttpStatus.OK)
    }

    @PostMapping("/register")
    fun register(@RequestBody registerDto: RegisterDto): ResponseEntity<String>{
        val firstName = registerDto.firstName
        val lastName = registerDto.lastName
        val email = registerDto.email
        val password = registerDto.password
        if(userRepository.existsUserByEmail(email)){
            throw IllegalStateException("Email ${email} is already taken!")
        }
        val user = User(firstName, lastName, email, passwordEncoder.encode(password))
        userRepository.save(user)
        return ResponseEntity("User registered successefully!", HttpStatus.OK)
    }

}