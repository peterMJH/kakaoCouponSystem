package com.peterMoon.kakaoPay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LoginController {
	
	@Autowired
	private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value = "/sign-in")
	public String logIn(@RequestParam String username, @RequestParam String password) {
		User user = userRepository.findByUsername(username);
		if(!passwordEncoder.matches(password, user.getPassword())) {
			return "login Failure";
		} else {
			return jwtTokenProvider.createToken(user.getUsername(), null);
		}
	}
	
	@PostMapping(value = "/sign-up")
	public User signUp(@RequestBody UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		userRepository.save(user);
		return user;
	}
	
	@GetMapping(value = "/users")
	public List<User> list() {
		return userRepository.findAll();
	}
}
