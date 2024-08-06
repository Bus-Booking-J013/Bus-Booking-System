package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
      Optional<User> findByEmail(String email);

}