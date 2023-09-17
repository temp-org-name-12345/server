package com.example.server.controller

import com.example.server.entity.User
import com.example.server.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userRepository: UserRepository) {
    @GetMapping
    fun getAllUserInfo() : Flow<User> {
        return userRepository.findAll()
    }

    @PostMapping
    suspend fun saveUser(@RequestBody user: User) : User {
        return userRepository.save(user)
    }
}