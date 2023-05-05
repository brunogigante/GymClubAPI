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
    fun getTrainingSessions(page: Pageable): Page<TrainingSession> = trainingSessionRepository.findAll(page)

    fun getTrainingSession(id: Long): TrainingSession = trainingSessionRepository.findById(id).orElseThrow {
        ResourceNotFoundException("Training session with id $id doesn't exist!")
    }

    fun getCreator(id: Long): User = userRepository.findById(id).orElseThrow {
        ResourceNotFoundException("User with id $id doesn't exist!")
    }

    fun addTrainingSession(creatorId: Long, workoutId: Long): Long? {
        val creator = getCreator(creatorId)
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
}