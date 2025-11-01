package com.hr.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hr.webapp.model.Admin;
import com.hr.webapp.model.Employee;
import com.hr.webapp.service.AdminService;
import com.hr.webapp.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

	@Autowired
	private AdminService adminService;
	
	@Autowired 
	private EmployeeService employeeService;
	
	@GetMapping("/login")
	public String login(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "login";
	}
	
	@PostMapping("/login")
	public String forwardLogin(@ModelAttribute("admin") Admin admin, Model model, HttpSession session) {
		boolean loginState = adminService.login(admin, session);
		if(loginState) {
			List<Employee> employees = employeeService.getEmployees();
			model.addAttribute("message", "Authentification réussie " + admin.getUsername());
			model.addAttribute("employees", employees);
			return "home";
		} else {
			model.addAttribute("error", "Authentification échouée " + admin.getUsername());
		}
		return "login";
	}

}
