package com.example.server.util

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.*

/* https://sennieworld.tistory.com/122 */
@Component
class S3Uploader(
    private val s3Client: AmazonS3Client,

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String
) {
    fun uploadFileToS3(multipartFile: MultipartFile, userId: Int, filePath: String = "user") : String {
        val uploadFile : File = convert(multipartFile, userId) ?: throw IllegalArgumentException()

        // S3에 저장할 파일 이름
        val fileName = filePath + "/" + userId

        // S3 업로드 후 로컬 파일 삭제
        val uploadUrl = putS3(uploadFile, fileName)
        // removeLocalFile(uploadFile)
        return uploadUrl
    }

    fun putS3(uploadFile: File, fileName: String) : String {
        s3Client.putObject(
            PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        )

        return s3Client.getUrl(bucket, fileName).toString()
    }

    /* 로컬에 파일 업로드 & 변환 */
    private fun convert(file: MultipartFile, userId: Int) : File? {
        val dirPath = System.getProperty("user.dir") + "/" + userId + "/" + file.originalFilename
        val convertFile = File(dirPath)

        if (convertFile.createNewFile()) {
            val fos = FileOutputStream(convertFile)
            fos.write(file.bytes)
            fos.close()

            return convertFile
        }

        return null
    }
}