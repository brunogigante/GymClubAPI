package com.example.gymclubapi.repository

import com.example.gymclubapi.entity.TrainingSession
import org.springframework.data.domain.Example
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.FluentQuery
import org.springframework.stereotype.Repository
import java.util.function.Function

@Repository
interface TrainingSessionRepository: JpaRepository<TrainingSession, Long> {

}