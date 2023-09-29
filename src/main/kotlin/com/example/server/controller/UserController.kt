package com.example.server.controller


import com.example.server.dto.UserDto
import com.example.server.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/user")
@RestController
class UserController(private val userService: UserService) {
    @GetMapping("/info")
    fun getUserByKeyHash(@RequestParam("keyHash") keyHash: String) : UserDto.UserLogin {
        return userService.getUserByKeyHash(keyHash)
    }

    @PostMapping("/save")
    fun saveUser(@RequestBody userLogin: UserDto.UserLogin) : UserDto.UserLogin {
        return userService.saveUser(userLogin)
    }

    @PostMapping("/thumbnail")
    fun getAppThumbnail() : List<String> {
        return listOf(
            "https://ddakdae-s3-bucket.s3.ap-northeast-2.amazonaws.com/user/admin/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7+2023-09-22+205154.png"
        )
    }
}