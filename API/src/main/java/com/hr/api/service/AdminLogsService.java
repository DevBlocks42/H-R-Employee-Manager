package com.hr.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hr.api.model.AdminLogs;
import com.hr.api.repository.AdminLogsRepository;
import com.hr.api.utils.PropertiesReader;
import com.hr.api.utils.SortUtils;

@Service
public class AdminLogsService {
	
	@Autowired
	private AdminLogsRepository repository;
	
	@Autowired 
	private PropertiesReader properties;
	
	
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
	
	public Page<AdminLogs> findAll(String page) {
		Pageable pageable = PageRequest.of(Integer.parseInt(page), properties.getTableSize());
		return repository.findAll(pageable);
	}
	
	public Page<AdminLogs> findAll(String column, String order, String page) {
		Sort sort = SortUtils.prepareSort(column, order);
		Pageable pageable = PageRequest.of(Integer.parseInt(page), properties.getTableSize(), sort);
		return repository.findAll(pageable);
	}
	
	/**
	 * Retourne une liste d'objets AdminLogs
	 * @return
	 */
	public Iterable<AdminLogs> getAllAdminLogs() {
		return repository.findAll();
	}
}
