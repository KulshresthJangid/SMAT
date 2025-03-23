package com.nerdyGeek.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerdyGeek.dto.LoginUserDTO;
import com.nerdyGeek.dto.RegisterUserDTO;
import com.nerdyGeek.entities.User;
import com.nerdyGeek.sql.repositories.UserRepository;

@Service
public class AuthenticationService {
	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User signup(RegisterUserDTO input) {
		String password = passwordEncoder.encode(input.getPassword());
		User user = new User();
		user.setPassword(password);
		user.setUsername(password);

		return userRepository.save(user);
	}

	public User authenticate(LoginUserDTO input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));

		return userRepository.findByUsername(input.getUsername());
	}
}
