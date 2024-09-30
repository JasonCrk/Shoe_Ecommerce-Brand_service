package com.shoe_ecommerce.brand;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	MySQLContainer<?> mysqlContainer() {
		return new MySQLContainer<>(DockerImageName.parse("mysql:9.0"));
	}

	@Bean
	@ServiceConnection
	RabbitMQContainer rabbitContainer() {
		return new RabbitMQContainer(DockerImageName.parse("rabbitmq:3.13.7-alpine"));
	}

	@Bean
	@ServiceConnection(name = "azure-storage/azurite")
	GenericContainer<?> azuriteContainer() {
		return new GenericContainer<>(
				DockerImageName.parse("mcr.microsoft.com/azure-storage/azurite:latest"))
				.withExposedPorts(10000, 10001, 10002);
	}
}
