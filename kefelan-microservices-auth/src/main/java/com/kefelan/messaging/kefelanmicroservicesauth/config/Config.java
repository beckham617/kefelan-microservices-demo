package com.kefelan.messaging.kefelanmicroservicesauth.config;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		//return new RestTemplate();
		RestTemplate template = new RestTemplate();
        template.setInterceptors(Collections.singletonList(headerInterceptor(null)));
        return template;
	}
	
	/**
     * Get token info and set it into header
     * 
     * @param value
     * @return
     */
    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public ClientHttpRequestInterceptor headerInterceptor(
            @Value("#{request.getHeader('Authorization')}") final String value) {
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {
                request.getHeaders().add("Authorization", value);
                return execution.execute(request, body);
            }
        };

    }
}
