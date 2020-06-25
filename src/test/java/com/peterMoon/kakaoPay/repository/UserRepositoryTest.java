package com.peterMoon.kakaoPay.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.peterMoon.kakaoPay.entity.User;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void findByUsername() {
		String username = "peter";
		
		userRepository.save(User.builder()
					.username(username)
					.password("a12315")
					.build());
		
		Optional<User> user = userRepository.findByUsername(username);
		assertNotNull(user);
        assertTrue(user.isPresent());
        assertEquals(user.get().getUsername(), username);
	}
}
