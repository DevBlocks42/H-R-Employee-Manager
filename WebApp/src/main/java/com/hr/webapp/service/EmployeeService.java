package com.hr.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.webapp.model.Employee;
import com.hr.webapp.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired 
	private EmployeeRepository repository;
	
	/**
	 * Récupère la liste des Employee
	 * @return une liste d'Empoyee
	 */
	public List<Employee> getEmployees() {
		return repository.getEmployees();
	}
	
	/**
	 * Récupère un Employee via son id
	 * @param id
	 * @return Un Employee
	 */
	public Employee getEmployee(Long id) {
		return repository.getEmployee(id);
	}
	
	/**
	 * Ajoute un nouvel Employee
	 * @param employee
	 * @return le nouvel objet Employee
	 */
	public Employee createEmployee(Employee employee) {
		employee.setLastName(employee.getLastName().toUpperCase());
		return repository.createEmployee(employee);
	}
	
	/**
	 * Met à jour les données d'un Employee
	 * @param id
	 * @param employee
	 * @return l'objet Employee modifié
	 */
	public Employee updateEmployee(Long id, Employee employee) {
		employee.setLastName(employee.getLastName().toUpperCase());
		return repository.updateEmployee(id, employee);
	}
	
	/**
	 * Supprime un Employee
	 * @param id
	 */
	public void deleteEmployee(Long id) {
		repository.deleteEmployee(id);
	}
}
