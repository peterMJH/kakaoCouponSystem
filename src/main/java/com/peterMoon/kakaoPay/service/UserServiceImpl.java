package com.peterMoon.kakaoPay.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.peterMoon.kakaoPay.config.JwtTokenProvider;
import com.peterMoon.kakaoPay.dto.UserDTO;
import com.peterMoon.kakaoPay.entity.User;
import com.peterMoon.kakaoPay.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String signIn(String username, String password) {
		Optional<User> user = userRepository.findByUsername(username);
		if(!passwordEncoder.matches(password, user.get().getPassword())) {
			throw new BadCredentialsException("Password does not match");
		} else {
			return jwtTokenProvider.createToken(user.get().getUsername(), null);
		}
	}
	
	@Override
	public User signUp(UserDTO userDTO) {
		if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new EntityExistsException("duplicated username");
        }
		
		User user = User.builder()
				.username(userDTO.getUsername())
				.password(passwordEncoder.encode(userDTO.getPassword()))
				.build();
		
		return userRepository.save(user);
	}
	
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}
}
