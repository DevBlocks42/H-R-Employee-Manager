package com.hr.api.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hr.api.model.AdminLogs;

public interface AdminLogsRepository extends CrudRepository<AdminLogs, Long>, PagingAndSortingRepository<AdminLogs, Long> {
	List<AdminLogs> findAll(Sort sort);
}
