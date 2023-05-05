package com.example.gymclubapi.repository

import com.example.gymclubapi.entity.TrainingSessionSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainingSessionSetRepository: JpaRepository<TrainingSessionSet, Long>