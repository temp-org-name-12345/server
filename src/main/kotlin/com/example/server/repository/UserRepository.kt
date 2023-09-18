package com.example.server.repository

import com.example.server.entity.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CoroutineCrudRepository<User, Int> {
    suspend fun existsByEmail(email: String) : Boolean
}