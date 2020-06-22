package com.peterMoon.kakaoPay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peterMoon.kakaoPay.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String user);
}

