package com.hr.api.controller;

import java.io.IOException;
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

import com.hr.api.model.AdminLogs;
import com.hr.api.model.Department;
import com.hr.api.service.AdminDetailsService;
import com.hr.api.service.AdminLogsService;
import com.hr.api.service.DepartmentService;
import com.hr.api.utils.AdminLogsUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService service;
	
	@Autowired 
	private AdminLogsService logsService;
	
	@Autowired 
	private AdminDetailsService adminService;
	
	/******************************************* HTTP GET ******************************************/
	
	/**
	 * Récupère la liste des Department
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@GetMapping("/departments")
	public Iterable<Department> showDepartments(Model model, HttpServletRequest http) throws IOException {
		Iterable<Department> departments = service.getDepartments();
		AdminLogs logs = AdminLogsUtils.saveLogs(logsService, AdminLogsUtils.createLogsObject("GET", adminService, http, null));
		return departments;
	}
	
	/**
	 * Retourne un Department via son id
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	@GetMapping("/department/{id}")
	public Optional<Department> getDepartment(@PathVariable("id") Long id, HttpServletRequest http) throws IOException {
		Optional<Department> department = service.getDepartment(id);
		AdminLogs logs = AdminLogsUtils.saveLogs(logsService, AdminLogsUtils.createLogsObject("GET", adminService, http, department.get()));
		return department;
	}
	
	/******************************************* HTTP POST 
	 * @throws IOException ******************************************/
	
	@PostMapping("/department")
	/**
	 * Ajoute un Department
	 * @param department
	 * @return
	 */
	public Department createDepartment(@RequestBody Department department, HttpServletRequest http) throws IOException {
		AdminLogs logs = AdminLogsUtils.saveLogs(logsService, AdminLogsUtils.createLogsObject("POST", adminService, http, department));
		return service.saveDepartment(department);
	}
	
	/******************************************* HTTP PUT ******************************************/
	
	/**
	 * Met à jour un Department
	 * @param department
	 * @return
	 * @throws IOException 
	 */
	@PutMapping("/department/{id}")
	public Department saveDepartment(@PathVariable("id") Long id, @RequestBody Department department, HttpServletRequest http) throws IOException {
		Optional<Department> dbDepartment = service.getDepartment(id);
		if(dbDepartment.isPresent()) {
			department.setId(dbDepartment.get().getId());
			if(department.getName() != null) {
				dbDepartment.get().setName(department.getName());
			} if(department.getDescription() != null) {
				dbDepartment.get().setDescription(department.getDescription());
			}
		} else {
			AdminLogs logs = AdminLogsUtils.saveLogs(logsService, AdminLogsUtils.createLogsObject("PUT", adminService, http, department));
			return service.saveDepartment(department);
		}
		AdminLogs logs = AdminLogsUtils.saveLogs(logsService, AdminLogsUtils.createLogsObject("PUT", adminService, http, dbDepartment.get()));
		return service.saveDepartment(dbDepartment.get());
	}
	
	/******************************************* HTTP DELETE ******************************************/
	
	/**
	 * Supprimme un Department
	 * @param id
	 */
	@DeleteMapping("/department/{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long id, HttpServletRequest http) {
		try {
			service.deleteDepartment(id);
			AdminLogs logs = AdminLogsUtils.saveLogs(logsService, AdminLogsUtils.createLogsObject("DELETE", adminService, http, id));
			return ResponseEntity.ok("Service supprimé avec succès.");
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erreur : le service est déjà rattaché à un ou plusieurs employé(s).");
	    } catch(Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + e.getMessage());
	    }
	}
}
