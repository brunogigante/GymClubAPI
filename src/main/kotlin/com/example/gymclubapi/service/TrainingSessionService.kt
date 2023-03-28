package com.example.gymclubapi.service

import com.example.gymclubapi.entity.TrainingSession
import com.example.gymclubapi.entity.User
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.TrainingSessionRepository
import com.example.gymclubapi.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class TrainingSessionService(
    private val trainingSessionRepository: TrainingSessionRepository,
    private val userRepository: UserRepository
) {
    fun getTrainingSessions(page: Pageable): Page<TrainingSession> = trainingSessionRepository.findAll(page)

    fun getTrainingSession(id: Long): TrainingSession? = trainingSessionRepository.findById(id).orElseThrow {
        ResourceNotFoundException("Training session with id $id doesn't exist!")
    }

    fun getCreator(id: Long): User = userRepository.findById(id).orElseThrow {
        ResourceNotFoundException("User with id $id doesn't exist!")
    }

    fun addTrainingSession(id: Long): Long? {
        val creator = getCreator(id)
        val trainingSession = TrainingSession(creator)
        return trainingSessionRepository.save(trainingSession).id
    }
}