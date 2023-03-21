package com.example.gymclubapi.entity

import com.fasterxml.jackson.databind.ser.Serializers.Base
import jakarta.persistence.*
import java.sql.Timestamp

@Entity
class TrainingPlan(
    @Column val isPublic: Boolean,
    @ManyToOne(targetEntity = TrainingPlan::class)
    val parent: TrainingPlan
) : BaseEntity()