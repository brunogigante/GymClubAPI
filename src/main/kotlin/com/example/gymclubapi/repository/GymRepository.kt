package com.example.gymclubapi.repository

import com.example.gymclubapi.entity.Gym
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GymRepository: JpaRepository<Gym, Long>