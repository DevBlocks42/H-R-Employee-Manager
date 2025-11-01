package com.hr.api.service;

import com.hr.api.model.Employee;
import com.hr.api.repository.EmployeeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository repository; 
    
    /**
     * Récupère un Employe via son id
     * @param id
     * @return Employee or null
     */
    
    public Optional<Employee> getEmployee(Long id) {
        return repository.findById(id);
    }
    
    /**
     * Récupère la liste des employés
     * @return Liste d'Employees
     */
    public Iterable<Employee> getEmployees() {
        return repository.findAll();
    }
    
    /**
     * Supprimme un employé via son id
     * @param id 
     */
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
    
    /**
     * Sauvegarde un employé par référence
     * @param employee
     * @return Employee employee
     */
    public Employee saveEmployee(Employee employee) {
    	PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return repository.save(employee);
    }
}
