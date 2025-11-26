package com.hr.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hr.api.model.AdminLogs;
import com.hr.api.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long>, PagingAndSortingRepository<Department, Long>{
	Page<Department> findAll(Pageable pageable);
}
