package com.openclassrooms.MediscreenSqlApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration
public class MediscreenSqlApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenSqlApiApplication.class, args);
	}

}
