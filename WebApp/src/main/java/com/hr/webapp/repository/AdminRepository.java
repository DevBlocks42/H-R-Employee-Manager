package com.hr.webapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hr.webapp.model.Admin;
import com.hr.webapp.utils.PropertiesReader;

import jakarta.servlet.http.HttpSession;

@Component
public class AdminRepository {

	@Autowired 
	private PropertiesReader properties;
	
	@Autowired 
	private HttpSession session;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	
	/**
	 * Transfère les identifiants à l'API
	 * @param admin
	 * @return 
	 */
	public ResponseEntity<String> authenticate(Admin admin) {
		String apiUrl = properties.getApiUrl();
		String endpoint = apiUrl + "/api/auth/login";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Admin> request = new HttpEntity<>(admin, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(endpoint, request, String.class);
		List<String> cookies = response.getHeaders().get(HttpHeaders.SET_COOKIE);
        if (cookies != null && !cookies.isEmpty()) {
        	session.setAttribute("API_SESSION_COOKIE", cookies.get(0));
        }
		return response;
	}
	
}
