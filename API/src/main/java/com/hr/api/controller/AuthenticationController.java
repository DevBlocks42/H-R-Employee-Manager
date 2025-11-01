package com.hr.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.api.model.Admin;

import jakarta.servlet.http.HttpSession;

@RestController
public class AuthenticationController {
	
	private final AuthenticationManager authManager;

    public AuthenticationController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }
    
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody Admin admin, HttpSession session) {
    	try {
            var authRequest = new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword());
            var authentication = authManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            return ResponseEntity.ok("Authenticated");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
