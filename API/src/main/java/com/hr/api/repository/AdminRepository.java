package com.hr.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hr.api.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {
	Optional<Admin> findByUsername(String username);
}
