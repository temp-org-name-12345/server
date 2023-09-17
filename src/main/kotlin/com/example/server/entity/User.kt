package com.example.server.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.stereotype.Indexed
import java.time.LocalDateTime

@Table(name = "users")
class User(
    @Id
    var id: Int? = null,
    val email: String,
    val nickname: String,

    /*
    @CreatedBy
    val createdBy: String? = null,

    @CreatedDate
    val createdDateTime: LocalDateTime? = null
     */
)