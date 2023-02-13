package com.cos.photogramstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.photogramstart.domain.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}
