package com.hr.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.api.model.Department;
import com.hr.api.service.DepartmentService;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService service;
	
	/******************************************* HTTP GET ******************************************/
	
	/**
	 * Récupère la liste des Department
	 * @param model
	 * @return
	 */
	@GetMapping("/departments")
	public Iterable<Department> showDepartments(Model model) {
		Iterable<Department> departments = service.getDepartments();
		return departments;
	}
	
	/**
	 * Retourne un Department via son id
	 * @param id
	 * @return
	 */
	@GetMapping("/department/{id}")
	public Optional<Department> getDepartment(@PathVariable("id") Long id) {
		Optional<Department> department = service.getDepartment(id);
		return department;
	}
	
	/******************************************* HTTP POST ******************************************/
	
	@PostMapping("/department")
	/**
	 * Ajoute un Department
	 * @param department
	 * @return
	 */
	public Department createDepartment(Department department) {
		return service.saveDepartment(department);
	}
	
	/******************************************* HTTP PUT ******************************************/
	
	/**
	 * Met à jour un Department
	 * @param department
	 * @return
	 */
	@PutMapping("/department/{id}")
	public Department saveDepartment(@PathVariable("id") Long id, Department department) {
		Optional<Department> dbDepartment = service.getDepartment(id);
		if(dbDepartment.isPresent()) {
			if(!dbDepartment.get().getName().equals(department.getName())) {
				dbDepartment.get().setName(department.getName());
			} else if(!dbDepartment.get().getDescription().equals(department.getDescription())) {
				dbDepartment.get().setDescription(department.getDescription());
			}
		}
		return service.saveDepartment(dbDepartment.get());
	}
	
	/******************************************* HTTP DELETE ******************************************/
	
	/**
	 * Supprimme un Department
	 * @param id
	 */
	@DeleteMapping("/department/{id}")
	public void deleteDepartment(@PathVariable("id") Long id) {
		service.deleteDepartment(id);
	}
}
