package com.nerdyGeek.smat.sql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nerdyGeek.smat.entities.UserEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    UserEntity findByUsernameAndEmail(String username, String email);

    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
