package com.example.server.entity

import com.example.server.dto.UserDto
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(indexes = [
    Index(unique = true, columnList = "addressName"),
    Index(unique = true, columnList = "storeName")
])
class Location(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false, updatable = false)
    val lat: Double,

    @Column(nullable = false, updatable = false)
    val lng: Double,

    @Column(nullable = false, updatable = false)
    val addressName: String,

    @Column(nullable = false, updatable = false)
    val storeName: String,

    @Column(nullable = false, updatable = true)
    val count: Int,

    @Column(nullable = false, updatable = false)
    val year: Int,

    @Column(nullable = false, updatable = false)
    val month: Int,

    @Column(nullable = false, updatable = false)
    val day: Int,

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
    val locationPhotos: MutableList<LocationPhoto> = mutableListOf(),

    @OneToMany(
        mappedBy = "location",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val locationReviews: MutableList<LocationReview> = mutableListOf()
) {

    companion object {
        fun of(req: UserDto.AddLocationReq, user: User) : Location {
            return Location(
                lat = req.lat,
                lng = req.lng,
                year = req.year,
                month = req.month,
                day = req.day,
                isSpecial = req.isSpecial,
                addressName = req.addressName,
                storeName = req.storeName,
                count = 0,
                user = user
            )
        }
    }

    val date: LocalDate get() = LocalDate.of(year, month, day)

    fun addLocationPhoto(locationPhotos: List<LocationPhoto>) {
        locationPhotos.forEach { it.fetchLocation(this) }
        this.locationPhotos.addAll(locationPhotos)
    }
}