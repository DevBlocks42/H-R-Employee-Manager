package com.hr.api.repository;

import com.hr.api.model.AdminLogs;
import com.hr.api.model.Employee;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>, PagingAndSortingRepository<Employee, Long>{
	boolean existsByDepartmentId(Long departmentId);
	List<Employee> findAll(Sort sort);
}
