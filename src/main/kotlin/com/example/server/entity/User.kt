package com.example.server.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "users")
class User(
    @Id
    var id: Int? = null,
    val email: String,
    val nickname: String,
    val profileUrl: String,
)