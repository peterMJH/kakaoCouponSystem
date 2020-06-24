package com.peterMoon.kakaoPay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peterMoon.kakaoPay.config.JwtTokenProvider;
import com.peterMoon.kakaoPay.dto.UserDTO;
import com.peterMoon.kakaoPay.entity.User;
import com.peterMoon.kakaoPay.repository.UserRepository;
import com.peterMoon.kakaoPay.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/sign-in")
	public String signIn(@RequestParam String username, @RequestParam String password) {
		return userService.signIn(username, password);
	}
	
	@PostMapping(value = "/sign-up")
	public User signUp(@RequestBody UserDTO userDTO) {
		return userService.signUp(userDTO);
	}
	
	@GetMapping(value = "/users")
	public List<User> list() {
		return userService.getUsers();
	}
}
