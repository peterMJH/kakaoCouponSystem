package com.peterMoon.kakaoPay.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.peterMoon.kakaoPay.entity.User;
import com.peterMoon.kakaoPay.repository.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void findByUsername() {
		String username = "peter";
		
		userRepository.save(User.builder()
					.username(username)
					.password(passwordEncoder.encode("a12315"))
					.build());
		
		Optional<User> user = userRepository.findByUsername(username);
		assertNotNull(user);
        assertTrue(user.isPresent());
        assertEquals(user.get().getUsername(), username);
	}
}
