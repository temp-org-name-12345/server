package com.example.server.error

enum class ErrorCode(
    val code: String,
    val message: String,
    val status: Int = 400
) {
    USER_NOT_FOUND("USER_NOT_FOUND", "해당 유저를 찾을 수 없습니다"),
    IMAGE_REMOVE_FAIL("IMAGE_REMOVE_FAIL", "이미지 삭제 과정 중 에러가 발생하였습니다"),
    MULTIPART_FILE_IS_NULL("MULTIPART_FILE_NULL", "전달된 파일이 NULL 값")
}