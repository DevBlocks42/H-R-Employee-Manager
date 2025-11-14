package com.hr.api.utils;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.hr.api.model.Admin;
import com.hr.api.model.AdminLogs;
import com.hr.api.service.AdminDetailsService;
import com.hr.api.service.AdminLogsService;

import jakarta.servlet.http.HttpServletRequest;

public class AdminLogsUtils {

	public static <T> AdminLogs createLogsObject(String action, AdminDetailsService service, HttpServletRequest http, T entity) throws IOException {
		Optional<Admin> admin = service.getAdminByUsername(http.getRemoteUser());
		AdminLogs logs = new AdminLogs();
		logs.setAction(action);
		logs.setAdmin(admin.get());
		if(entity != null) {
			logs.setData(http.getRequestURI() + "; " + entity.toString());
		} else {
			logs.setData(http.getRequestURI());
		}
		logs.setDateTime(Instant.now());
		return logs;
	}
	
	public static AdminLogs saveLogs(AdminLogsService service, AdminLogs logs) {
		return service.saveAdminLogs(logs);
	}
}
