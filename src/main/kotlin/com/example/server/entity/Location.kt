package com.example.server.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Location(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false, updatable = false)
    val lat: Double,

    @Column(nullable = false, updatable = false)
    val lng: Double,

    @Column(nullable = false, updatable = false, length = 256)
    val name: String,

    @Column(nullable = false, updatable = true)
    val count: Int,

    @Column(nullable = false, updatable = false)
    val visitDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false, updatable = false)
    val isSpecial: Boolean,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @OneToMany(
        mappedBy = "location",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val locationPhotos: List<LocationPhoto> = listOf(),

    @OneToMany(
        mappedBy = "location",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val locationReviews: List<LocationReview> = listOf()
)