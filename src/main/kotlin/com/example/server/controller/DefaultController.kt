package com.example.server.controller

import com.example.server.service.DefaultService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/default")
@RestController
class DefaultController(
    private val defaultService: DefaultService
) {
    @GetMapping("/thumbnail")
    fun getAppThumbnail() : ResponseEntity<String> {
        val res = "https://ddakdae-s3-bucket.s3.ap-northeast-2.amazonaws.com/user/1/1000004774.jpg"
        return ResponseEntity.ok(res)
    }

    @GetMapping("/preview")
    fun getAppPreview() : List<String> {
        return listOf(
            "https://ddakdae-s3-bucket.s3.ap-northeast-2.amazonaws.com/user/1/1000004771.jpg",
            "https://ddakdae-s3-bucket.s3.ap-northeast-2.amazonaws.com/user/1/1000004775.jpg",
            "https://ddakdae-s3-bucket.s3.ap-northeast-2.amazonaws.com/user/1/1000004777.jpg",
        )
    }
}