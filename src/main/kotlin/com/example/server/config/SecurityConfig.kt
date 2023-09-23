package com.example.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

/* https://www.baeldung.com/spring-security-5-reactive */
@Configuration
@EnableWebFluxSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: ServerHttpSecurity) : SecurityWebFilterChain {
        return http
            .cors { it.disable() }
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }
            .authorizeExchange {
                it
                    .pathMatchers("/api/v1/**")
                    .permitAll()
                    .anyExchange()
                    .authenticated()
            }
            .build()
    }
}