package com.hr.webapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import com.hr.webapp.model.Employee;
import com.hr.webapp.utils.PropertiesReader;

import io.jsonwebtoken.lang.Arrays;

@Component
public class EmployeeRepository extends BaseRepository {
	@Autowired
	private PropertiesReader props;
	
	/******************************** HTTP GET ********************************/
	
	/**
	 * Retourne la liste de tous les Employee
	 * @return une liste d'objet Employee
	 */
	public List<Employee> getEmployees(String column, String order) {
		String apiUrl = props.getApiUrl();
		String endpoint = "";
		if(column != null && order != null) {
			endpoint = apiUrl + "/employees/" + column + "/" + order;
		} else {
			endpoint = apiUrl + "/employees";
		}
		ResponseEntity<Employee[]> response = get(endpoint, Employee[].class);
		return Arrays.asList(response.getBody());
		
	}
	/**
	 * Retourne un Employee via son id
	 * @param id
	 * @return Un Employee 
	 */
	public Employee getEmployee(Long id) {
		String apiUrl = props.getApiUrl();
		String endpoint = apiUrl + "/employee/" + id;
		ResponseEntity<Employee> response = get(endpoint, Employee.class);
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
		ResponseEntity<Employee> response = post(endpoint, employee, Employee.class);
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
		ResponseEntity<Employee> response = put(endpoint, employee, Employee.class);
		return response.getBody();
	}
	
	/******************************** HTTP DELETE ********************************/
	
	/**
	 * Supprime un Employee
	 * @param id
	 */
	public void deleteEmployee(Long id) {
		String apiUrl = props.getApiUrl();
		String endpoint = apiUrl + "/employee/" + id;
		delete(endpoint);
	}
}