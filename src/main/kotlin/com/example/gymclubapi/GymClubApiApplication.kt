package com.example.gymclubapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class GymClubApiApplication

fun main(args: Array<String>) {
    runApplication<GymClubApiApplication>(*args)
}
