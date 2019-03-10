package com.kefelan.messaging.kefelanmicroserviceszuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.kefelan.messaging.kefelanmicroserviceszuul.filter.pre.AccessFilter;

@SpringBootApplication
@EnableZuulProxy
//@EnableOAuth2Sso
//@EnableResourceServer
@EnableEurekaClient
public class KefelanMicroservicesZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(KefelanMicroservicesZuulApplication.class, args);
	}

	@Bean
	public AccessFilter accessFilter(){
		return new AccessFilter();
	}
}
