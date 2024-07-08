package com.example.simple;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public interface MyTestContainers {

    @Container
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:15");
}
