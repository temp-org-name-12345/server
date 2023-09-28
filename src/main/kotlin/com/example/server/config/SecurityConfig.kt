package com.example.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

/* https://www.baeldung.com/spring-security-5-reactive */
@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        return http
            .cors { it.disable() }
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/v1/user/**").permitAll()
                    .anyRequest().authenticated()
            }
            .build()
    }
}