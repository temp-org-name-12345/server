package com.example.server.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class LocationPhoto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false, updatable = false)
    val path: String,

    @ManyToOne
    @JoinColumn(name = "location_id")
    var location: Location
) {
    companion object {
        fun of(location: Location, path: String) : LocationPhoto {
            return LocationPhoto(
                path = path,
                location = location
            )
        }
    }

    fun fetchLocation(location: Location) {
        this.location = location
    }
}