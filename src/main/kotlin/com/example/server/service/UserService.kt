package com.example.server.service

import com.example.server.dto.UserDto
import com.example.server.entity.User
import com.example.server.error.exception.UserNotFoundException
import com.example.server.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(private val userRepository: UserRepository) {
    @Transactional(readOnly = true)
    fun getUserByKeyHash(keyHash: String) : UserDto.UserLogin {
        val user = userRepository.getUserByKeyHash(keyHash) ?: throw UserNotFoundException(keyHash)
        return UserDto.UserLogin.of(user)
    }

    fun saveUser(userLogin: UserDto.UserLogin) : UserDto.UserLogin {
        val user = userRepository.save(userLogin.toEntity())
        return UserDto.UserLogin.of(user)
    }
}