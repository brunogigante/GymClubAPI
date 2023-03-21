package com.example.gymclubapi.repository


import com.example.gymclubapi.entity.Exercise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExerciseRepository: JpaRepository<Exercise, Long> {
}