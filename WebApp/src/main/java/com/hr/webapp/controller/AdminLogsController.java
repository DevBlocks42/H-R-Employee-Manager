package com.hr.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hr.webapp.model.AdminLogs;
import com.hr.webapp.service.AdminLogsService;

@Controller
public class AdminLogsController {

	@Autowired 
	private AdminLogsService adminLogsService;
	
	@GetMapping("/logs/index")
	public String logsIndex(Model model) {
		List<AdminLogs> logs = adminLogsService.getLogs();
		model.addAttribute("logs", logs);
		return "logs/index";
	}
	
	@GetMapping("/logs/{id}")
	public String logsDetails(@PathVariable Long id, Model model) {
		AdminLogs log = adminLogsService.getLog(id);
		model.addAttribute("log", log);
		return "logs/details";
	}
}
