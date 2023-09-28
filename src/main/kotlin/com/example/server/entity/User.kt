package com.example.server.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(unique = true, nullable = false, updatable = false, length = 256)
    val email: String,

    @Column(unique = true, nullable = false, updatable = false, length = 128)
    val nickname: String,

    @Column(unique = true, nullable = false, updatable = false)
    val keyHash: String,

    @Column(nullable = true)
    val profileUrl: String,

    @OneToMany(
        mappedBy = "user",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val locations: List<Location> = listOf(),

    @OneToMany(
        mappedBy = "user",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val reviews: List<LocationReview> = listOf()
)