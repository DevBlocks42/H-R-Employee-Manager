package com.hr.webapp.model;

import java.util.List;

import lombok.Data;

@Data
public class Employee {
	private Integer id;
	private String firstName;
	private String lastName;
	private String mail;
	private String password;
	private Department department;
}
