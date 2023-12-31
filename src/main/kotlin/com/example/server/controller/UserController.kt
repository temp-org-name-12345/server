package com.example.server.controller


import com.example.server.dto.UserDto
import com.example.server.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile



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



    @PostMapping("/upload/new")
    fun uploadNewLocationInfo(
        @RequestPart(value = "images", required = false) images: List<MultipartFile>,
        @RequestPart(value = "req") req: UserDto.AddLocationReq
    ) : ResponseEntity<UserDto.ImageUploadRes> {
        val result = userService.uploadNewLocationInfo(images, req)
        return ResponseEntity.ok(result)
    }
}