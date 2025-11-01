package com.hr.webapp.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="com.hr.webapp")
public class PropertiesReader {
	private String apiUrl;
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
