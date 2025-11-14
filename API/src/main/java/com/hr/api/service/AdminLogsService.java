package com.hr.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.api.model.AdminLogs;
import com.hr.api.repository.AdminLogsRepository;

@Service
public class AdminLogsService {
	
	@Autowired
	private AdminLogsRepository repository;
	
	/**
	 * Sauvegarde une instance d'AdminLogs 
	 * @param logs
	 * @return
	 */
	public AdminLogs saveAdminLogs(AdminLogs logs) {
		return repository.save(logs);
	}
	
	/**
	 * Retourne un objet AdminLogs via son id
	 * @param id
	 * @return
	 */
	public Optional<AdminLogs> getAdminLogs(long id) {
		return repository.findById(id);
	}
	
	/**
	 * Retourne une liste d'objets AdminLogs
	 * @return
	 */
	public Iterable<AdminLogs> getAllAdminLogs() {
		return repository.findAll();
	}
}
