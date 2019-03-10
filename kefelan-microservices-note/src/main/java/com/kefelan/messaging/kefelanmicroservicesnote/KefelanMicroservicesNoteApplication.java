package com.kefelan.messaging.kefelanmicroservicesnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
public class KefelanMicroservicesNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(KefelanMicroservicesNoteApplication.class, args);
	}

}
