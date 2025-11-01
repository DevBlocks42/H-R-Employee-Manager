package com.hr.webapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;

public abstract class BaseRepository {
	
	@Autowired 
	protected HttpSession session;
	
	protected RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * Génère les headers d'authentification en puisant l'id de session dans l'objet HttpSession
	 * @return Headers
	 */
	protected HttpHeaders createLoggedRequestHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String sessionCookie = (String) session.getAttribute("API_SESSION_COOKIE");
        if (sessionCookie != null) {
            headers.add(HttpHeaders.COOKIE, sessionCookie);
        }
        return headers;
	}
	
	/**
	 * Récupère une entité depuis l'API
	 * @param <T>
	 * @param endpoint
	 * @param responseType
	 * @return ResponseEntity<T>
	 */
	protected <T> ResponseEntity<T> get(String endpoint, Class<T> responseType) {
		HttpHeaders headers = createLoggedRequestHeaders();
		HttpEntity<Void> entity = new HttpEntity<>(headers);
		return restTemplate.exchange(endpoint, HttpMethod.GET, entity, responseType);
	}
	
	/**
	 * 
	 * @param <T>
	 * @param url
	 * @param body
	 * @param responseType
	 * @return ResponseEntity<T>
	 */
	protected <T> ResponseEntity<T> post(String url, Object body, Class<T> responseType) {
        HttpHeaders headers = createLoggedRequestHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
    }
	
	/**
	 * 
	 * @param <T>
	 * @param url
	 * @param body
	 * @param responseType
	 * @return ResponseEntity<T>
	 */
	protected <T> ResponseEntity<T> put(String url, Object body, Class<T> responseType) {
        HttpHeaders headers = createLoggedRequestHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, entity, responseType);
    }
	
	/**
	 * 
	 * @param url
	 */
	protected void delete(String url) {
		HttpHeaders headers = createLoggedRequestHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
	}
}
