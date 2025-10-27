package com.hr.webapp.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="com.hr.webapp")
public class PropertiesReader {
	private String apiUrl;
}
