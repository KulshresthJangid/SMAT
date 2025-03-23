package com.nerdyGeek.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerdyGeek.entities.User;
import com.nerdyGeek.sql.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
	
	public void registerUser(User user) {
		user.setPassword(passEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public User authenticateUser(String username, String password) {
		User user = userRepository.findByUsername(username);
		if(Objects.nonNull(user) && passEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}
}
