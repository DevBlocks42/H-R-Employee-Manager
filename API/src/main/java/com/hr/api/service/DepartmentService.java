package com.hr.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.api.model.Department;
import com.hr.api.repository.DepartmentRepository;
import com.hr.api.repository.EmployeeRepository;
import com.hr.api.utils.SortUtils;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;
	
	@Autowired 
	private EmployeeRepository employeeRepository;
	
	/**
	 * Recupère la liste des Department
	 * @return 
	 */
	public Iterable<Department> getDepartments() {
		return repository.findAll();
	}
	
	public Page<Department> getDepartments(String page) {
		Pageable pageable = PageRequest.of(Integer.parseInt(page), 5);
		return repository.findAll(pageable);
	}
	
	public Page<Department> getDepartments(String column, String order, String page) {
		Sort sort = SortUtils.prepareSort(column, order);
		Pageable pageable = PageRequest.of(Integer.parseInt(page), 5, sort);
		return repository.findAll(pageable);
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
	@Transactional
	public void deleteDepartment(Long id) {
		repository.deleteById(id);
	}
}
