package com.peterMoon.kakaoPay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peterMoon.kakaoPay.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String user);
	Boolean existsByUsername(String user);
}

