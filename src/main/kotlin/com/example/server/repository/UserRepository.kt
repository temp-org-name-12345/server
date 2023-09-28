package com.example.server.repository

import com.example.server.entity.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User, Int> {
    fun getUserByKeyHash(keyHash: String) : User?
}