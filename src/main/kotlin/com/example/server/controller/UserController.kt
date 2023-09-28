package com.example.server.controller


import com.example.server.dto.UserDto
import com.example.server.entity.User
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
        userService.saveUser(userLogin)
        return userLogin
    }
}