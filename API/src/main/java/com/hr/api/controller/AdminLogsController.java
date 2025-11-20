package com.hr.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hr.api.model.AdminLogs;
import com.hr.api.service.AdminLogsService;

@RestController
public class AdminLogsController {
	
	@Autowired
	private AdminLogsService logsService;
	
	@GetMapping("/logs/index")
	public Iterable<AdminLogs> showLogs() {
		return logsService.getAllAdminLogs();
	}
	
	@GetMapping("/logs/details/{id}")
	public Optional<AdminLogs> showLog(@PathVariable Long id) {
		return logsService.getAdminLogs(id);
	}
}
