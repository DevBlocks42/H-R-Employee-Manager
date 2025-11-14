package com.hr.api.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    private String mail;
    private String password;
    
    @ManyToOne
    @JoinTable(
    			name = "employee_department", 
    			joinColumns = {
    					@JoinColumn(name = "employee_id", referencedColumnName = "id")
    		})
    private Department department;
    
    public String toString() {
    	return "id: " + id + "; firstName: " + firstName + "; lastName: " + lastName + "; mail: " + mail + ";";
    }
}
