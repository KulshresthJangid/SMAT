package com.nerdyGeek.smat.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerdyGeek.smat.dto.LoginUserDTO;
import com.nerdyGeek.smat.dto.RegisterUserDTO;
import com.nerdyGeek.smat.entities.UserEntity;
import com.nerdyGeek.smat.sql.repositories.UserRepository;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final DatabaseService databaseService;

    public AuthenticationService(UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, DatabaseService databaseService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.databaseService = databaseService;
    }

    public UserEntity signup(RegisterUserDTO input) {
        String password = passwordEncoder.encode(input.getPassword());
        UserEntity user = new UserEntity();
        user.setPassword(password);
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        return databaseService.save(user);
    }

    public UserEntity authenticate(LoginUserDTO input) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        input.getUsernameOrEmail(), input.getPassword()));

        return databaseService.findByUsernameOrEmail(input.getUsername(),
                input.getEmail());
    }
}
