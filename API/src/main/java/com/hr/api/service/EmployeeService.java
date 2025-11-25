package com.hr.api.service;

import com.hr.api.model.Employee;
import com.hr.api.repository.EmployeeRepository;
import com.hr.api.utils.SortUtils;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Employee> getEmployees(String page) {
    	Pageable pageable = PageRequest.of(Integer.parseInt(page), 5);
        return repository.findAll(pageable);
    }
    
    public Iterable<Employee> getEmployees() {
        return repository.findAll();
    }
    
    public Page<Employee> getEmployees(String column, String order, String page) {
    	Sort sort = SortUtils.prepareSort(column, order);
		Pageable pageable = PageRequest.of(Integer.parseInt(page), 5, sort);
		return repository.findAll(pageable);
    }
    
    /**
     * Supprimme un employé via son id
     * @param id 
     */
    @Transactional
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
