package com.kefelan.messaging.kefelanmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class KefelanMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(KefelanMicroservicesApplication.class, args);
	}

}
