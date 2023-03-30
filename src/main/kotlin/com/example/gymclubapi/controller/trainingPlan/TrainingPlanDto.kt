package com.example.gymclubapi.controller.trainingPlan

data class TrainingPlanDto(
    val name: String,
    val isPublic: Boolean,
    val trainingPlanId: Long?
)