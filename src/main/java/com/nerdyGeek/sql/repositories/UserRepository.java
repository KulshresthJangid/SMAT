package com.nerdyGeek.sql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nerdyGeek.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
