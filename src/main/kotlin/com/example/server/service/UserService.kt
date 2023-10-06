package com.example.server.service

import com.example.server.controller.AddLocationReq
import com.example.server.dto.UserDto
import com.example.server.error.exception.UserNotFoundException
import com.example.server.repository.UserRepository
import com.example.server.util.S3Uploader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

data class ImageUploadRes(
    val imageUrls: List<String>,
    val data: AddLocationReq
)

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
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

    fun uploadLocationInfo(images: List<MultipartFile>, req: AddLocationReq) : ImageUploadRes {
        val imageUrls = images.map { s3Uploader.uploadFileToS3(it, req.userId ?: 0) }
        return ImageUploadRes(
            imageUrls = imageUrls,
            data = req
        )
    }
}