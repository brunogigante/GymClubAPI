package com.example.gymclubapi.controller.gym

import com.example.gymclubapi.entity.Gym
import com.example.gymclubapi.service.GymService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("gyms")
class GymController (
    private val gymService: GymService
) {

    @GetMapping
    fun findAll(): List<Gym>{
        return gymService.getGyms()
    }
}