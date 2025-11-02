package com.hr.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hr.api.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long>{

}
