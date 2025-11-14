package com.hr.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hr.api.model.Admin;
import com.hr.api.repository.AdminRepository;

@Service
public class AdminDetailsService implements UserDetailsService {

	@Autowired 
	private AdminRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> admin = repository.findByUsername(username);
		return new User(admin.get().getUsername(), admin.get().getPassword(), getGrantedAuthorities("ADMIN"));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}
	
	public Optional<Admin> getAdminByUsername(String username) {
		return repository.findByUsername(username);
	}

}
