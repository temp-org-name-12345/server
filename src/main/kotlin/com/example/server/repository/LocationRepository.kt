package com.example.server.repository

import com.example.server.entity.Location
import org.springframework.data.jpa.repository.JpaRepository

interface LocationRepository : JpaRepository<Location, Int> {
    fun findByAddressName(addrName: String) : Location?
    fun findByStoreName(storeName: String) : Location?
}