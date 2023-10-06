package com.example.server.repository

import com.example.server.entity.LocationPhoto
import org.springframework.data.jpa.repository.JpaRepository

interface LocationPhotoRepository : JpaRepository<LocationPhoto, Int>