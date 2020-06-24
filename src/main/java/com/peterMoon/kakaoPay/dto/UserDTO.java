package com.peterMoon.kakaoPay.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserDTO {
	private String username;
	private String password;
	
	@Builder
	public UserDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
