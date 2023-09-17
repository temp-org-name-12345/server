package com.example.server.config

import com.example.server.entity.User
import com.example.server.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.ReactiveAuditorAware
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing


@Configuration
@EnableR2dbcAuditing
class AuditingConfig {
    @Bean
    fun auditorProvider(userRepository: UserRepository) : ReactiveAuditorAware<User> {
        return ReactiveAuditorAware { userRepository.findById(1) }
    }
}