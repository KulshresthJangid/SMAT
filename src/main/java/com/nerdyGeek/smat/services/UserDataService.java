package com.nerdyGeek.smat.services;

import org.springframework.stereotype.Service;

import com.nerdyGeek.smat.entities.UserEntity;
import com.nerdyGeek.smat.sql.repositories.UserRepository;

import lombok.Data;

@Data
@Service
public class UserDataService {

    private final UserRepository userRepo;

    public UserDataService(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    public UserEntity findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public UserEntity findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public UserEntity findByUsernameAndEmail(String username, String email) {
        return userRepo.findByUsernameAndEmail(username, email);
    }

    public UserEntity save(UserEntity user) {
        return userRepo.save(user);
    }

    public UserEntity findByUsernameOrEmail(String username, String email) {
        return userRepo.findByUsernameOrEmail(username, email).orElse(null);
    }
}
