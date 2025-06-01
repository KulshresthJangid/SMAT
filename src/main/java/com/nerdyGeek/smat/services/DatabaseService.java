package com.nerdyGeek.smat.services;

import com.nerdyGeek.smat.entities.OrganizationsEntity;
import com.nerdyGeek.smat.sql.repositories.OrganizationsRepository;
import com.nimbusds.openid.connect.sdk.assurance.evidences.Organization;
import org.springframework.stereotype.Service;

import com.nerdyGeek.smat.entities.UserEntity;
import com.nerdyGeek.smat.sql.repositories.UserRepository;

import lombok.Data;

import javax.swing.text.html.Option;
import java.util.Optional;

@Data
@Service
public class DatabaseService {

    private final UserRepository userRepo;
    private final OrganizationsRepository organizationsRepository;

    public DatabaseService(UserRepository userRepository, OrganizationsRepository organizationsRepository) {
        this.userRepo = userRepository;
        this.organizationsRepository = organizationsRepository;
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

    public OrganizationsEntity save(OrganizationsEntity organization) {
        return organizationsRepository.save(organization);
    }

    public UserEntity findByUsernameOrEmail(String username, String email) {
        return userRepo.findByUsernameOrEmail(username, email).orElse(null);
    }

    public Optional<OrganizationsEntity> findByEmailAndPhoneNumber(String email, String phoneNumber) {
        return this.organizationsRepository.findByEmailAndPhoneNumber(email, phoneNumber);
    }

    public Optional<OrganizationsEntity> findById(Long id) {
        return this.organizationsRepository.findById(id);
    }

}
