package com.hr.webapp.model;

import java.time.Instant;

import lombok.Data;

@Data
public class AdminLogs {

	private int id;
	private Admin admin;
	private String action;
	private Instant dateTime;
	private String data;
}
