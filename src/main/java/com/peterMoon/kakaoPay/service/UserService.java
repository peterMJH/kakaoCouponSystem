package com.peterMoon.kakaoPay.service;

import java.util.List;

import com.peterMoon.kakaoPay.dto.UserDTO;
import com.peterMoon.kakaoPay.entity.User;

public interface UserService {
	String signIn(String username, String password);
	User signUp(UserDTO userDTO);
	List<User> getUsers();
}
