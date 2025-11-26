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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hr.webapp.model.Department;
import com.hr.webapp.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired 
	private DepartmentService service;
	
	@GetMapping(value={"/departments", "/departments/{page}", "/departments/{column}/{order}/{page}"})
	public String showDepartments(Model model, @PathVariable(required=false) String column, @PathVariable(required=false) String order, @PathVariable(required=false) String page) {
		List<Department> departments;
		Integer intPage;
		if(column != null && order != null && page != null) {
			intPage = Integer.parseInt(page);
			departments = service.getDepartments(column, order, page);
		} else if(page != null) {
			departments = service.getDepartments(page);
			intPage = Integer.parseInt(page);
		} else {
			departments = service.getDepartments();
			intPage = - 1;
		}
		model.addAttribute("departments", departments);
		model.addAttribute("currentColumn", column);
		model.addAttribute("previousPage", intPage - 1 == - 1 ? 0 : intPage - 1);
		model.addAttribute("nextPage", intPage + 1);
		model.addAttribute("currentOrder", order);
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
		if(id != 0) {
			service.updateDepartment(id, department);
		} else {
			System.out.println("NAME : " + department.getName());
			if(!department.getName().isEmpty() && !department.getDescription().isEmpty()) {
				service.createDepartment(department);
			}
		}
		return new ModelAndView("redirect:/departments");
	}
	
	@GetMapping("/deleteDepartment/{id}")
	public ModelAndView deleteDepartment(@PathVariable("id") Long id, RedirectAttributes attributes) {
		try {
			service.deleteDepartment(id);
			attributes.addFlashAttribute("message", "Service supprimé avec succès.");
		} catch(HttpClientErrorException e) {
			attributes.addFlashAttribute("error", e.getResponseBodyAsString());
		} catch(Exception e) {
			attributes.addFlashAttribute("error", e.getClass().getName());
		}
		return new ModelAndView("redirect:/departments");
	}
}
