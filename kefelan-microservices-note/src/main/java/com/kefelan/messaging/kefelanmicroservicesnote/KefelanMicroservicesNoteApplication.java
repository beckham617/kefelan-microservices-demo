package com.kefelan.messaging.kefelanmicroservicesnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class KefelanMicroservicesNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(KefelanMicroservicesNoteApplication.class, args);
	}

}
