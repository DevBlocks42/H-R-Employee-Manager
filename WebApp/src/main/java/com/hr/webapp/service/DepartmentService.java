package com.hr.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.webapp.model.Department;
import com.hr.webapp.model.Employee;
import com.hr.webapp.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository repository;
	
	public List<Department> getDepartments() {
		return repository.getDepartments();
	}
	public Department getDepartment(Long id) {
		return repository.getDepartment(id);
			
	}
	public Department createDepartment(Department department) {
		return repository.createDepartment(department);
	}
	public Department updateDepartment(Long id, Department department) {
		return repository.updateDepartment(id, department);
	}
	public void deleteDepartment(Long id) {
		repository.deleteDepartment(id);
	}
}
