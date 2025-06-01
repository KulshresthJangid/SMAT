package com.nerdyGeek.smat.services;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerdyGeek.smat.entities.UserEntity;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private DatabaseService databaseService;

    private BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

    public void registerUser(UserEntity user) {
        user.setPassword(passEncoder.encode(user.getPassword()));
        databaseService.save(user);
    }

    public UserEntity authenticateUser(String username, String password) {
        UserEntity user = databaseService.findByUsername(username);
        if (Objects.nonNull(user)
                && passEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String cred)
            throws UsernameNotFoundException {
        UserEntity user = databaseService.findByUsernameOrEmail(cred, cred);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + cred);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
