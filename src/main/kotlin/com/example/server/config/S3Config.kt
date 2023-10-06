package com.example.server.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config(
    @Value(value = "\${cloud.aws.credentials.accessKey}")
    private val accessKey: String,

    @Value(value = "\${cloud.aws.credentials.secretKey}")
    private val secretKey: String,

    @Value(value = "\${cloud.aws.region.static}")
    private val region: String
) {
    @Bean
    fun amazonS3Client() : AmazonS3Client {
        val awsCredentials = BasicAWSCredentials(accessKey, secretKey)
        val credentialProvider = AWSStaticCredentialsProvider(awsCredentials)

        return AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .enablePathStyleAccess()
            .withCredentials(credentialProvider)
            .build() as AmazonS3Client
    }
}