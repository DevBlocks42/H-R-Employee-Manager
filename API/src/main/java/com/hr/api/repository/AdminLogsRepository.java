package com.hr.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.hr.api.model.AdminLogs;

public interface AdminLogsRepository extends CrudRepository<AdminLogs, Long> {

}
