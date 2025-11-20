package com.hr.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.webapp.model.AdminLogs;
import com.hr.webapp.repository.AdminLogsRepository;

@Service
public class AdminLogsService {

	@Autowired 
	private AdminLogsRepository repository;
	
	public List<AdminLogs> getLogs() {
		return repository.getLogs();
	}
	
	public AdminLogs getLog(Long id) {
		return repository.getLog(id);
	}
}
