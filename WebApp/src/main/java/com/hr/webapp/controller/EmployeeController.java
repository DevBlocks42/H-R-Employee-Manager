package com.hr.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hr.webapp.model.Employee;
import com.hr.webapp.service.EmployeeService;

import lombok.Data;

@Data
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * Affiche la page d'accueil avec la liste des employés
	 * @param model
	 * @return String vue
	 */
	
	@GetMapping("/")
	public String home(Model model) {
		Iterable<Employee> employees = employeeService.getEmployees();
		model.addAttribute("employees", employees);
		return "home";
	}
	
	/**
	 * Affiche le formulaire d'ajout d'employé
	 * @param model
	 * @return String vue
	 */
	@GetMapping("/addEmployee")
	public String addEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "addEmployee";
	}
	
	/**
	 * Affiche le formulaire d'édition d'employé
	 * @param model
	 * @param id
	 * @return String vue
	 */
	@GetMapping("/editEmployee/{id}")
	public String editEmployee(Model model, @PathVariable("id") Long id) {
		Employee employee = employeeService.getEmployee(id);
		if(employee != null) {
			model.addAttribute("employee", employee);
		}
		return "addEmployee";
	}
	
	/**
	 * Supprime l'employé via son id
	 * @param id
	 * @return ModelAndView modèle associé à la vue
	 */
	@GetMapping("/deleteEmployee/{id}")
	public ModelAndView deleteEmployee(@PathVariable("id") Long id) {
		employeeService.deleteEmployee(id);
		return new ModelAndView("redirect:/");
	}
	
	/**
	 * Sauvegarde un employé existant ou en créer un nouveau
	 * @param employee
	 * @return ModelAndView modèle associé à la vue
	 */
	@PostMapping("/saveEmployee")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		if(employee.getId() == null) {
			if(!employee.getFirstName().isEmpty() && !employee.getLastName().isEmpty() && !employee.getMail().isEmpty() && !employee.getPassword().isEmpty()) {
				employeeService.createEmployee(employee);
			}
		} else {
			if(employee.getPassword().isEmpty() || employee.getPassword().isBlank()) {
				employee.setPassword(employeeService.getEmployee((long) employee.getId()).getPassword());
			}
			employeeService.updateEmployee((long) employee.getId(), employee);
		}
		return new ModelAndView("redirect:/");
	}
}
