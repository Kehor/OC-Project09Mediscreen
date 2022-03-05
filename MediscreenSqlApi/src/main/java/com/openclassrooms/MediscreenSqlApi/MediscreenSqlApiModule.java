package com.openclassrooms.MediscreenSqlApi;


import com.openclassrooms.MediscreenSqlApi.microservice.NoSqlApiMicroservice;
import com.openclassrooms.MediscreenSqlApi.microservice.NoSqlApiMicroserviceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MediscreenSqlApiModule {

	@Bean
	public NoSqlApiMicroservice NoSqlApiMicroservice() {return new NoSqlApiMicroserviceImpl();}
	
}
