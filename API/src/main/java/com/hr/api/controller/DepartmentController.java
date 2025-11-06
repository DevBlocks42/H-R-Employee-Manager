package com.hr.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Department saveDepartment(@PathVariable("id") Long id, @RequestBody Department department) {
		Optional<Department> dbDepartment = service.getDepartment(id);
		if(dbDepartment.isPresent()) {
			department.setId(dbDepartment.get().getId());
			if(department.getName() != null) {
				dbDepartment.get().setName(department.getName());
			} if(department.getDescription() != null) {
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
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long id) {
		try {
			service.deleteDepartment(id);
			return ResponseEntity.ok("Service supprimé avec succès.");
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erreur : le service est déjà rattaché à un ou plusieurs employé(s).");
	    } catch(Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + e.getMessage());
	    }
	}
}
