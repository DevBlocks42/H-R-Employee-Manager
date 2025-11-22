package com.hr.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	
	public Iterable<AdminLogs> findAll() {
		return repository.findAll();
	}
	public Iterable<AdminLogs> findAll(String column, String order) {
		Sort sort = Sort.by(column);
		if(order.equals("ASC")) {
			sort = sort.ascending();
		} else {
			sort = sort.descending();
		}
		return repository.findAll(sort);
	}
	
	/**
	 * Retourne une liste d'objets AdminLogs
	 * @return
	 */
	public Iterable<AdminLogs> getAllAdminLogs() {
		return repository.findAll();
	}
}
