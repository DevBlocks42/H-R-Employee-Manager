package com.hr.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.api.model.Department;
import com.hr.api.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;
	
	/**
	 * Recupère la liste des Department
	 * @return 
	 */
	public Iterable<Department> getDepartments() {
		return repository.findAll();
	}
	
	/**
	 * Retourne un Department par son id
	 * @param id
	 * @return
	 */
	public Optional<Department> getDepartment(Long id) {
		return repository.findById(id);
	}
	
	/**
	 * Créer un Department
	 * @param department
	 * @return le Department créé
	 */
	public Department saveDepartment(Department department) {
		return repository.save(department);
	}
	
	/**
	 * Supprime un Department via son id
	 * @param id
	 */
	public void deleteDepartment(Long id) {
		repository.deleteById(id);
	}
}
