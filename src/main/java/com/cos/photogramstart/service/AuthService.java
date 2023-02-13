package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;

public interface AuthService {

	// 회원가입
	public User signup(User user);
	
	// 회원찾기
	public User findByUsername(String username);
}
