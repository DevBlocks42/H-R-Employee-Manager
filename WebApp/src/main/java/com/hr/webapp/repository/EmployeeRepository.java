package com.hr.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import com.hr.webapp.model.Employee;
import com.hr.webapp.utils.PropertiesReader;

@Component
public class EmployeeRepository {
	@Autowired
	private PropertiesReader props;
	
	/******************************** HTTP GET ********************************/
	
	/**
	 * Retourne la liste de tous les Employee
	 * @return un Iterable d'objet Employee
	 */
	public Iterable<Employee> getEmployees() {
		String apiUrl = props.getApiUrl();
		String endpoint = apiUrl + "/employees";
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Iterable<Employee>> response = rest.exchange(
				endpoint,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Iterable<Employee>>() {}
		);
		return response.getBody();
		
	}
	/**
	 * Retourne un Employee via son id
	 * @param id
	 * @return Un Employee 
	 */
	public Employee getEmployee(Long id) {
		String apiUrl = props.getApiUrl();
		String endpoint = apiUrl + "/employee/" + id;
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Employee> response = rest.exchange(
				endpoint,
				HttpMethod.GET,
				null,
				Employee.class
		);
		return response.getBody();
	}
	
	/******************************** HTTP POST ********************************/
	
	/**
	 * Créer un Employee par référence
	 * @return l'objet Employee créé
	 */
	public Employee createEmployee(Employee employee) {
		String apiUrl = props.getApiUrl();
		String endpoint = apiUrl + "/employee";
		RestTemplate rest = new RestTemplate();
		HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
		ResponseEntity<Employee> response = rest.exchange(
				endpoint,
				HttpMethod.POST,
				request,
				Employee.class
		);
		return response.getBody();
	}
	
	/******************************** HTTP PUT ********************************/
	
	/**
	 * Met à jour un Empoyee existant
	 * @param id
	 * @return
	 */
	public Employee updateEmployee(Long id, Employee employee) {
		String apiUrl = props.getApiUrl();
		String endpoint = apiUrl + "/employee/" + id;
		RestTemplate rest = new RestTemplate();
		HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
		ResponseEntity<Employee> response = rest.exchange(
				endpoint,
				HttpMethod.PUT,
				request,
				Employee.class
		);
		return response.getBody();
	}
	
	/******************************** HTTP DELETE ********************************/
	
	public void deleteEmployee(Long id) {
		String apiUrl = props.getApiUrl();
		String endpoint = apiUrl + "/employee/" + id;
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Void> response = rest.exchange(
				endpoint,
				HttpMethod.DELETE,
				null,
				Void.class
		);
	}
}