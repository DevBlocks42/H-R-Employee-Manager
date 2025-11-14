package com.hr.api.controller;

import com.hr.api.model.Department;
import com.hr.api.model.Employee;
import com.hr.api.model.Admin;
import com.hr.api.model.AdminLogs;
import com.hr.api.service.AdminDetailsService;
import com.hr.api.service.AdminLogsService;
import com.hr.api.service.DepartmentService;
import com.hr.api.service.EmployeeService;
import com.hr.api.utils.AdminLogsUtils;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.security.Principal;
import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired 
    private DepartmentService departmentService;
    
    @Autowired
    private AdminDetailsService adminService;
    
    @Autowired 
    private AdminLogsService logsService;
   
    
    /******************************** HTTP GET ********************************/
    
    /**
     * Renvoie la liste des employés
     * @return list d'Employees
     * @throws IOException 
     */
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees(Principal principal, HttpServletRequest http) throws IOException {
    	AdminLogs logs = AdminLogsUtils.saveLogs(logsService, AdminLogsUtils.createLogsObject("GET", adminService, http, null));
        return employeeService.getEmployees();
    }
    /**
     * Renvoie un Employee
     * @param id
     * @return objet Employee
     * @throws IOException 
     */
    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployee(@PathVariable("id") Long id, HttpServletRequest http) throws IOException {
    	AdminLogs logs = AdminLogsUtils.saveLogs(logsService, AdminLogsUtils.createLogsObject("GET", adminService, http, null));
        return employeeService.getEmployee(id);
    } 
    
    /******************************** HTTP POST ********************************/
    
    /**
     * Ajoute un employé par référence
     * @param employee
     * @return L'Employee ajouté
     * @throws IOException 
     */
    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee, HttpServletRequest http) throws IOException {
    	Department department = null;
    	if(employee.getDepartment() != null) {
    		department = departmentService.getDepartment((long) employee.getDepartment().getId()).get();
    	}
    	if(department != null) {
    		employee.setDepartment(department);
    	}
    	AdminLogs logs = AdminLogsUtils.saveLogs(logsService, AdminLogsUtils.createLogsObject("POST", adminService, http, employee));
        return employeeService.saveEmployee(employee);
    }
    
    /******************************** HTTP PUT ********************************/
    
    /**
     * Met à jour un Employee existant par référence
     * @param id
     * @param employee
     * @return Le nouvel objet Employee 
     * @throws IOException 
     */
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee, HttpServletRequest http) throws IOException {
        Optional<Employee> retrievedEmployee = employeeService.getEmployee(id);
        if(retrievedEmployee.isPresent()) {
            Employee currentEmployee = retrievedEmployee.get();
            String firstName = employee.getFirstName();
            String lastName = employee.getLastName();
            String mail = employee.getMail();
            String password = employee.getPassword();
            Department department = departmentService.getDepartment((long) employee.getDepartment().getId()).get();
            if(firstName != null) {
                currentEmployee.setFirstName(firstName);
            } if (lastName != null) {
                currentEmployee.setLastName(lastName);
            } if (mail != null) {
                currentEmployee.setMail(mail);
            } if (password != null) {
                currentEmployee.setPassword(password);
            } if(department != null) {
            	currentEmployee.setDepartment(department);
            }
            employeeService.saveEmployee(currentEmployee);
            AdminLogs logs = AdminLogsUtils.saveLogs(logsService, AdminLogsUtils.createLogsObject("PUT", adminService, http, currentEmployee));
            return currentEmployee;
        } else {
            return null;
        }
    }
    
    /******************************** HTTP DELETE ********************************/
    
    /**
     * Supprime un employé
     * @param id de l'employé à supprimer
     * @throws IOException 
     */
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") Long id, HttpServletRequest http) throws IOException {
        employeeService.deleteEmployee(id);
        AdminLogs logs = AdminLogsUtils.saveLogs(logsService, AdminLogsUtils.createLogsObject("DELETE", adminService, http, id));
    }
}
