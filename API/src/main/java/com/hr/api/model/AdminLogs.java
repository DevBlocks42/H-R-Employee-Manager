package com.hr.api.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="admin_logs")
public class AdminLogs {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Admin admin;
	private String ip;
	private String userAgent;
	private String action;
	private LocalDateTime dateTime;
	private String data;
}
