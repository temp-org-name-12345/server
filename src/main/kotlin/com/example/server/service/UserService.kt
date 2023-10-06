package com.example.server.service

import com.example.server.dto.UserDto
import com.example.server.entity.Location
import com.example.server.entity.LocationPhoto
import com.example.server.error.exception.UserNotFoundException
import com.example.server.repository.LocationPhotoRepository
import com.example.server.repository.LocationRepository
import com.example.server.repository.UserRepository
import com.example.server.util.S3Uploader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val locationRepository: LocationRepository,
    private val locationPhotoRepository: LocationPhotoRepository,
    private val s3Uploader: S3Uploader
) {
    @Transactional(readOnly = true)
    fun getUserByKeyHash(keyHash: String) : UserDto.UserLogin {
        val user = userRepository.getUserByKeyHash(keyHash) ?: throw UserNotFoundException(keyHash)
        return UserDto.UserLogin.of(user)
    }

    fun saveUser(userLogin: UserDto.UserLogin) : UserDto.UserLogin {
        val user = userRepository.save(userLogin.toEntity())
        return UserDto.UserLogin.of(user)
    }

    /**
     * 1. user 정보 가져오기
     * 2. S3에 업로드
     * 3. Location, LocationPhoto Entity Update
     */
    fun uploadNewLocationInfo(images: List<MultipartFile>, req: UserDto.AddLocationReq) : UserDto.ImageUploadRes {
        val userId = req.userId

        val user = userRepository.findById(userId).orElseThrow { throw UserNotFoundException(userId) }
        val location = Location.of(req, user)
        val imageUrls = images.map { s3Uploader.uploadFileToS3(it, req.userId) }

        val locationPhotos: List<LocationPhoto> = imageUrls.map {  path ->
            LocationPhoto.of(location, path)
        }

        location.addLocationPhoto(locationPhotos)

        locationRepository.save(location)
        // locationPhotoRepository.saveAll(locationPhotos) ** CASCADING **

        return UserDto.ImageUploadRes(
            imageUrls = imageUrls,
            data = req
        )
    }
}