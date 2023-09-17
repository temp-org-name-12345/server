package com.example.server.controller

import com.example.server.entity.User
import com.example.server.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userRepository: UserRepository) {
    @GetMapping
    fun getAllUserInfo() : Flow<User> {
        return userRepository.findAll().asFlow()
    }
}