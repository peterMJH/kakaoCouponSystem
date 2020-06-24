package com.peterMoon.kakaoPay.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Builder 
	public User(String username, String password) {
		this.username = username; 
		this.password = password; 
	}

}
