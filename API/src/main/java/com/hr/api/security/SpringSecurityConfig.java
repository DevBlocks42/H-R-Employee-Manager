package com.hr.api.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.hr.api.service.AdminDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Autowired 
	private AdminDetailsService adminDetailService; 
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions(frame -> frame.disable()))
        .cors(cors -> cors.configurationSource(request -> {
            var c = new org.springframework.web.cors.CorsConfiguration();
            c.setAllowedOrigins(List.of("http://localhost:8081"));
            c.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
            c.setAllowCredentials(true);
            c.setAllowedHeaders(List.of("*"));
            return c;
        }))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/login").permitAll() 
            //DANGER
            .requestMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated()
        )
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
        .formLogin().disable();
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
		AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder.userDetailsService(adminDetailService).passwordEncoder(bCryptPasswordEncoder);
		return authBuilder.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}