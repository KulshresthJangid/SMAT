package com.nerdyGeek.smat.servicesImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerdyGeek.smat.entities.User;
import com.nerdyGeek.smat.sql.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
