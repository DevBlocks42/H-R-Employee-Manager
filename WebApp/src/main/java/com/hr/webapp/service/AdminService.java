package com.hr.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hr.webapp.model.Admin;
import com.hr.webapp.repository.AdminRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository repository;
	
	public boolean login(Admin admin, HttpSession session) {
		ResponseEntity<String> response = repository.authenticate(admin);
		if (response.getStatusCode().is2xxSuccessful()) {
			return true;
		}
		return false;
	}
	
}
