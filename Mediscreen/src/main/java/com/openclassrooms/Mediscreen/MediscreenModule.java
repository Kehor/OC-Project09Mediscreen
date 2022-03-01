package com.openclassrooms.Mediscreen;

import com.openclassrooms.Mediscreen.microservice.NoSqlApiMicroservice;
import com.openclassrooms.Mediscreen.microservice.NoSqlApiMicroserviceImpl;
import com.openclassrooms.Mediscreen.microservice.SqlApiMicroservice;
import com.openclassrooms.Mediscreen.microservice.SqlApiMicroserviceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MediscreenModule {

	@Bean
	public SqlApiMicroservice getSqlApiMicroservice() {return new SqlApiMicroserviceImpl();}

	@Bean
	public NoSqlApiMicroservice getNoSqlApiMicroservice() {return new NoSqlApiMicroserviceImpl();}
	
}
