package com.example.server.util

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import com.example.server.error.exception.ImageRemovalException
import com.example.server.error.exception.MultipartFileNullException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream

/* https://sennieworld.tistory.com/122 */
@Component
class S3Uploader(
    private val s3Client: AmazonS3Client,

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String
) {
    companion object {
        private val dirPath = System.getProperty("user.dir") + "/src/main/resources/images"
    }

    fun uploadFileToS3(multipartFile: MultipartFile, userId: Int) : String {
        val uploadFile : File = convert(multipartFile) ?: throw MultipartFileNullException()

        // S3에 저장할 파일 이름
        val fileName = "user/$userId/${uploadFile.name}"

        // S3 업로드 후 로컬 파일 삭제
        val uploadUrl = putS3(uploadFile, fileName)

        if (removeLocalImages()) return uploadUrl
        else throw ImageRemovalException()
    }

    fun putS3(uploadFile: File, fileName: String) : String {
        s3Client.putObject(
            PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        )

        return s3Client.getUrl(bucket, fileName).toString()
    }

    /* 로컬에 파일 업로드 & 변환 */
    private fun convert(file: MultipartFile) : File? {
        val dir = File(dirPath)

        if (!dir.exists()) dir.mkdirs()

        val filePath = dirPath + "/${file.originalFilename}"
        val convertFile = File(filePath)

        if (convertFile.createNewFile()) {
            val fos = FileOutputStream(convertFile)
            fos.write(file.bytes)
            fos.close()

            return convertFile
        }

        return null
    }

    private fun removeLocalImages() : Boolean {
        val dir = File(dirPath)

        if (dir.exists()) {
            dir.listFiles()?.forEach { if (it.exists()) it.delete() }
        }

        dir.delete()

        return !dir.exists()
    }
}