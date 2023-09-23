package com.example.server.entity

import jakarta.persistence.Entity
import org.springframework.data.relational.core.mapping.Table

@Entity
@Table(name = "users")
class User(
    @jakarta.persistence.Id
    var id: Int? = null,
    val email: String,
    val nickname: String,
    val profileUrl: String,
)