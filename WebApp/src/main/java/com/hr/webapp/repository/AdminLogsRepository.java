package com.hr.webapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.hr.webapp.model.AdminLogs;
import com.hr.webapp.model.Department;
import com.hr.webapp.utils.PropertiesReader;

import io.jsonwebtoken.lang.Arrays;

@Component
public class AdminLogsRepository extends BaseRepository {
	
	@Autowired 
	private PropertiesReader properties;
	
	public List<AdminLogs> getLogs(String column, String order) {
		String apiUrl = properties.getApiUrl();
		String endpoint = "";
		if(column != null && order != null) {
			endpoint = apiUrl + "/logs/index/" + column + "/" + order;
		} else {
			endpoint = apiUrl + "/logs/index";
		}
		ResponseEntity<AdminLogs[]> response = get(endpoint, AdminLogs[].class);
		return Arrays.asList(response.getBody());
	}
	
	public AdminLogs getLog(Long id) {
		String apiUrl = properties.getApiUrl();
		String endpoint = apiUrl + "/logs/details/" + id;
		ResponseEntity<AdminLogs> response = get(endpoint, AdminLogs.class);
		return response.getBody();
	}

}
