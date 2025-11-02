package com.hr.webapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.hr.webapp.model.Department;
import com.hr.webapp.utils.PropertiesReader;

import io.jsonwebtoken.lang.Arrays;

@Component
public class DepartmentRepository extends BaseRepository {

	@Autowired 
	private PropertiesReader properties;
	
	/******************************** HTTP GET ********************************/
	
	/**
	 * Récupère une liste de tous les Department
	 * @return
	 */
	public List<Department> getDepartments() {
		String apiUrl = properties.getApiUrl();
		String endpoint = apiUrl + "/departments";
		ResponseEntity<Department[]> response = get(endpoint, Department[].class);
		return Arrays.asList(response.getBody());
	}
	
	/**
	 * Récupère un Department via son id
	 * @param id
	 * @return
	 */
	public Department getDepartment(Long id) {
		String apiUrl = properties.getApiUrl();
		String endpoint = apiUrl + "/department/" + id;
		ResponseEntity<Department> response = get(endpoint, Department.class);
		return response.getBody();
	}
	
	/******************************** HTTP POST ********************************/
	
	/**
	 * Créer un Department
	 * @param department
	 * @return
	 */
	public Department createDepartment(Department department) {
		String apiUrl = properties.getApiUrl();
		String endpoint = apiUrl + "/department";
		ResponseEntity<Department> response = post(endpoint, department, Department.class);
		return response.getBody();
	}
	
	/******************************** HTTP PUT ********************************/
	
	/**
	 * Met à jour un Department
	 * @param department
	 * @return
	 */
	public Department updateDepartment(Long id, Department department) {
		String apiUrl = properties.getApiUrl();
		String endpoint = apiUrl + "/department/" + id;
		ResponseEntity<Department> response = put(endpoint, department, Department.class);
		return response.getBody();
	}
	
	/******************************** HTTP DELETE ********************************/
	
	/**
	 * Supprime un Department
	 * @param id
	 */
	public void deleteDepartment(Long id) {
		String apiUrl = properties.getApiUrl();
		String endpoint = apiUrl + "/department/" + id;
		delete(endpoint);
	}
}
