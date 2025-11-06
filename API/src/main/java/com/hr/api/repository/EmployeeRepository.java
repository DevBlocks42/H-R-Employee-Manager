package com.hr.api.repository;

import com.hr.api.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	boolean existsByDepartmentId(Long departmentId);
}
