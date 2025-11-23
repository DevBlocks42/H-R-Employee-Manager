package com.hr.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hr.api.model.AdminLogs;
import com.hr.api.service.AdminLogsService;

@RestController
public class AdminLogsController {
	
	@Autowired
	private AdminLogsService logsService;
	
	@GetMapping(value={"/logs/index", "/logs/index/{column}/{order}/{page}"})
	public Iterable<AdminLogs> showLogs(@PathVariable(required = false) String column, @PathVariable(required = false) String order, @PathVariable(required=false) String page) {
		if(column != null && order != null && page != null) {
			return logsService.findAll(column, order, page).getContent();
		} 
		return logsService.findAll();
	}
	
	@GetMapping("/logs/details/{id}")
	public Optional<AdminLogs> showLog(@PathVariable Long id) {
		return logsService.getAdminLogs(id);
	}
}
