package com.example.gymclubapi.controller.trainingSession

import com.example.gymclubapi.entity.TrainingSession
import com.example.gymclubapi.service.TrainingSessionService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("sessions")
class TrainingSessionController(private val trainingSessionService: TrainingSessionService) {

    @GetMapping
    fun findAll(page: Pageable): Page<TrainingSession>{
        return trainingSessionService.getTrainingSessions(page)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TrainingSession? {
        return trainingSessionService.getTrainingSession(id)
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@PathVariable id: Long): Long? {
        return trainingSessionService.addTrainingSession(id)
    }
}