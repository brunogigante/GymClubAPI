package com.example.gymclubapi.service

import com.example.gymclubapi.entity.TrainingSession
import com.example.gymclubapi.entity.TrainingSessionSet
import com.example.gymclubapi.entity.User
import com.example.gymclubapi.exceptions.ResourceNotFoundException
import com.example.gymclubapi.repository.TrainingSessionRepository
import com.example.gymclubapi.repository.TrainingSessionSetRepository
import com.example.gymclubapi.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class TrainingSessionService(
    private val trainingSessionRepository: TrainingSessionRepository,
    private val trainingSessionSetRepository: TrainingSessionSetRepository,
    private val userRepository: UserRepository,
    private val workoutService: WorkoutService,
    private val workoutExerciseSetService: WorkoutExerciseSetService
) {
    fun getTrainingSessions(): List<TrainingSession> = trainingSessionRepository.findAll()

    fun getTrainingSession(id: Long): TrainingSession = trainingSessionRepository.findById(id).orElseThrow {
        ResourceNotFoundException("Training session with id $id doesn't exist!")
    }

    fun addTrainingSession(workoutId: Long): Long? {
        val creatorEmail = SecurityContextHolder.getContext().authentication.name
        val creator = userRepository.findUserByEmail(creatorEmail)
        val workout = workoutService.getWorkout(workoutId)
        val trainingSession = TrainingSession(creator, workout)
        return trainingSessionRepository.save(trainingSession).id
    }

    fun addTrainingSessionSet(repetitions: Int, weight: Int, trainingSessionId: Long, workoutExerciseSetId: Long): Long?{
        val userEmail = SecurityContextHolder.getContext().authentication.name
        val workoutExerciseSet = workoutExerciseSetService.getSet(workoutExerciseSetId)
        val trainingSession = getTrainingSession(trainingSessionId)
        val user = userRepository.findUserByEmail(userEmail)
        if(user.id != trainingSession.creator.id){
            throw IllegalStateException("You do not have permission to access this training session!")
        }
        val trainingSessionSet = TrainingSessionSet(repetitions, weight, trainingSession, workoutExerciseSet)
        return trainingSessionSetRepository.save(trainingSessionSet).sessionSet.id
    }

    fun getUserTrainingSessions(): List<TrainingSession>{
        val userEmail = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findUserByEmail(userEmail)
        return trainingSessionRepository.findTrainingSessionByCreator(user)
    }
}