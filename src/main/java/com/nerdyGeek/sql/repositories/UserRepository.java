package com.nerdyGeek.sql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nerdyGeek.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
