package com.hr.api.controller;

import com.hr.api.model.Employee;
import com.hr.api.service.EmployeeService;
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
    
    /******************************** HTTP GET ********************************/
    
    /**
     * Renvoie la liste des employés
     * @return list d'Employees
     */
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }
    /**
     * Renvoie un Employee
     * @param id
     * @return objet Employee
     */
    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployee(id);
    } 
    
    /******************************** HTTP POST ********************************/
    
    /**
     * Ajoute un employé par référence
     * @param employee
     * @return L'Employee ajouté
     */
    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
    
    /******************************** HTTP PUT ********************************/
    
    /**
     * Met à jour un Employee existant par référence
     * @param id
     * @param employee
     * @return Le nouvel objet Employee 
     */
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Optional<Employee> retrievedEmployee = employeeService.getEmployee(id);
        if(retrievedEmployee.isPresent()) {
            Employee currentEmployee = retrievedEmployee.get();
            String firstName = employee.getFirstName();
            String lastName = employee.getLastName();
            String mail = employee.getMail();
            String password = employee.getPassword();
            if(firstName != null) {
                currentEmployee.setFirstName(firstName);
            } if (lastName != null) {
                currentEmployee.setLastName(lastName);
            } if (mail != null) {
                currentEmployee.setMail(mail);
            } if (password != null) {
                currentEmployee.setPassword(password);
            }
            employeeService.saveEmployee(currentEmployee);
            return currentEmployee;
        } else {
            return null;
        }
    }
    
    /******************************** HTTP DELETE ********************************/
    
    /**
     * Supprime un employé
     * @param id de l'employé à supprimer
     */
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }
}
