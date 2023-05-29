package com.example.pocsentry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class PocSentryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocSentryApplication.class, args);
	}

}
