package com.example.simple;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.JdbcClient;

@SpringBootApplication
public class SimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleApplication.class, args);
	}

	@Bean
	ApplicationRunner demo(JdbcClient jdbcClient) {
		return args -> {
			System.out.println(">>>>>>>>>>>>>>>>>> START");
            jdbcClient
					.sql("select * from customer")
					.query(Customer.class)
					.stream()
					.forEach(System.out::println);
        };
	}

}
