package com.hr.api.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.hr.api.utils.PropertiesReader;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="com.hr.api")
public class PropertiesReader {
	private String apiUrl;
	private int tableSize;
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

