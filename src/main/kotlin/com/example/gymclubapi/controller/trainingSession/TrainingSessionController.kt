package com.example.gymclubapi.controller.trainingSession

import com.example.gymclubapi.entity.TrainingSession
import com.example.gymclubapi.service.TrainingSessionService
import com.example.gymclubapi.service.WorkoutExerciseSetService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("sessions")
class TrainingSessionController(private val trainingSessionService: TrainingSessionService){
    @GetMapping
    fun findAll(): List<TrainingSession>{
        return trainingSessionService.getTrainingSessions()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TrainingSession? {
        return trainingSessionService.getTrainingSession(id)
    }

    @PostMapping("/{workoutId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@PathVariable workoutId: Long): Long? {
        return trainingSessionService.addTrainingSession(workoutId)
    }

    @PostMapping("/{sessionId}/set")
    @ResponseStatus(HttpStatus.CREATED)
    fun createSet(@PathVariable sessionId:Long, @RequestBody trainingSessionSetDto: TrainingSessionSetDto): Long?{
        val repetitions = trainingSessionSetDto.repetitions
        val weight = trainingSessionSetDto.weight
        val workoutExerciseId = trainingSessionSetDto.workoutExerciseSetId
        return trainingSessionService.addTrainingSessionSet(repetitions, weight, sessionId, workoutExerciseId)
    }

    @GetMapping("/me")
    fun findUserSessions(): List<TrainingSession>{
        return trainingSessionService.getUserTrainingSessions()
    }
}