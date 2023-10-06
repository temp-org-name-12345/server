package com.example.server.dto

import com.example.server.entity.User

class UserDto {
    data class UserLogin(
        val id: Int?,
        val email: String,
        val nickname: String,
        val profileUrl: String,
        val keyHash: String
    ) {
        companion object {
            fun of(user: User) : UserLogin {
                return UserLogin(
                    id = user.id,
                    email = user.email,
                    nickname = user.nickname,
                    profileUrl = user.profileUrl,
                    keyHash = user.keyHash
                )
            }
        }

        fun toEntity() : User {
            return User(
                email = this.email,
                nickname = this.nickname,
                profileUrl = this.profileUrl,
                keyHash = this.keyHash
            )
        }
    }

    data class AddLocationReq(
        val lat: Double,
        val lng: Double,
        val year: Int,
        val month: Int,
        val day: Int,
        val isSpecial: Boolean,
        val addressName: String,
        val storeName: String,
        val userId: Int,
    )

    data class ImageUploadRes(
        val imageUrls: List<String>,
        val data: AddLocationReq
    )
}