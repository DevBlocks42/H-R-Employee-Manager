package com.hr.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hr.webapp.model.Department;
import com.hr.webapp.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired 
	private DepartmentService service;
	
	@GetMapping("/departments")
	public String showDepartments(Model model) {
		List<Department> departments = service.getDepartments();
		model.addAttribute("departments", departments);
		return "departments/index";
	}
}
