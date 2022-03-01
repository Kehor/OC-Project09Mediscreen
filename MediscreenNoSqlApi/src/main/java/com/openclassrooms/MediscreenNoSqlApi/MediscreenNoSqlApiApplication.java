package com.openclassrooms.MediscreenNoSqlApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MediscreenNoSqlApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenNoSqlApiApplication.class, args);
	}

}
