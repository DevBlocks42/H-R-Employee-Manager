package com.hr.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("/department/{id}")
	public String editDepartment(@PathVariable("id") Long id, Model model) {
		Department department = service.getDepartment(id);
		model.addAttribute("department", department);
		return "departments/edit";
	}
	
	@GetMapping("/department/add")
	public String createDepartment(Model model) {
		Department department = new Department();
		model.addAttribute("department", department);
		return "departments/add";
	}
	
	@PostMapping("/department")
	public ModelAndView saveDepartment(@ModelAttribute Department department) {
		Long id = (long) department.getId();
		service.updateDepartment(id, department);
		return new ModelAndView("redirect:/departments");
	}
	
	@GetMapping("/deleteDepartment/{id}")
	public ModelAndView deleteDepartment(@PathVariable("id") Long id) {
		service.deleteDepartment(id);
		return new ModelAndView("redirect:/departments");
	}
}
