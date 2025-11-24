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
	
	@GetMapping(value={"/logs/index/{page}", "/logs/index/{column}/{order}/{page}"})
	public String logsIndex(Model model, @PathVariable(required = false) String column, @PathVariable(required = false) String order, @PathVariable(required = false) String page) {
		List<AdminLogs> logs = adminLogsService.getLogs(column, order, page);
		Integer intPage = Integer.parseInt(page);
		model.addAttribute("logs", logs);
		model.addAttribute("currentColumn", column);
		model.addAttribute("currentOrder", order);
		model.addAttribute("previousPage", intPage - 1 == - 1 ? 0 : intPage - 1);
		model.addAttribute("currentPage", page);
		model.addAttribute("nextPage", intPage + 1);
		return "logs/index";
	}
	
	@GetMapping("/log/{id}")
	public String logsDetails(@PathVariable Long id, Model model) {
		AdminLogs log = adminLogsService.getLog(id);
		model.addAttribute("log", log);
		return "logs/details";
	}
}
