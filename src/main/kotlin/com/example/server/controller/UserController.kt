package com.example.server.controller

import com.example.server.entity.User
import com.example.server.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userRepository: UserRepository) {
    @PostMapping
    suspend fun saveUser(@RequestBody user: User) : User {
        return userRepository.save(user)
    }

    @GetMapping("/api/v1/check")
    suspend fun checkUser(@RequestParam("email") email: String) : Boolean {
        return userRepository.existsByEmail(email)
    }

    @GetMapping
    suspend fun testEndPoint() : String {
        return "Tesk OK"
    }

    @GetMapping("/api/v1/test2")
    suspend fun testEndPoint2() : String {
        return "Test 2 OK"
    }
}