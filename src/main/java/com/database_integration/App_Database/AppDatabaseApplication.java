package com.database_integration.App_Database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;


@SpringBootApplication
public class AppDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppDatabaseApplication.class, args);
	}

	@Bean
	CommandLineRunner testDatabase(JdbcTemplate jdbcTemplate) {
		return args -> {
			try {
				String result = jdbcTemplate.queryForObject("SELECT VERSION()", String.class);
				System.out.println("✅ Successfully connected to MySQL! Version: " + result);
			} catch (Exception e) {
				System.err.println("❌ Failed to connect to MySQL: " + e.getMessage());
			}
		};
	}
}
