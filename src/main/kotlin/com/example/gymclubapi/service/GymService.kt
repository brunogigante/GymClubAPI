package com.example.gymclubapi.service

import com.example.gymclubapi.entity.Gym
import com.example.gymclubapi.repository.GymRepository
import org.springframework.stereotype.Component

@Component
class GymService(
    private val gymRepository: GymRepository
) {
    fun getGyms(): List<Gym> = gymRepository.findAll()
}