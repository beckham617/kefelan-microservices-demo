package com.kefelan.messaging.kefelanmicroservicesauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KefelanMicroservicesAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(KefelanMicroservicesAuthApplication.class, args);
	}

}
