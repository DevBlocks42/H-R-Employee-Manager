package com.hr.api.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
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
	private int id;
	@ManyToOne
	@JoinColumn(name="admin_id", referencedColumnName="id", nullable=false, insertable=true)
	private Admin admin;
	@Column(name="admin_action")
	private String action;
	@Column(name="datetime")
	private Instant dateTime;
	private String data;
}
