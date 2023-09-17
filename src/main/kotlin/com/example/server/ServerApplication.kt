package com.example.server

import com.example.server.entity.User
import com.example.server.repository.UserRepository
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@SpringBootApplication
@EnableR2dbcRepositories
class ServerApplication {
	/*
	@Bean
	fun initializer(connectionFactory: ConnectionFactory) : ConnectionFactoryInitializer {
		val initializer = ConnectionFactoryInitializer()
		initializer.setConnectionFactory(connectionFactory)
		initializer.setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))

		return initializer
	}
	*/

	/*
	@Bean
	fun dummy(repository: UserRepository) : CommandLineRunner {
		return CommandLineRunner { args ->
			val user = User(
				email = "h970126@gmail.com",
				nickname = "seungsu"
			)

			repository.save(user)
				.block()
		}
	}
	*/
}

fun main(args: Array<String>) {
	runApplication<ServerApplication>(*args)
}
