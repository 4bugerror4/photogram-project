package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;

	@Transactional
	@Override
	public User signup(User user) {
		
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");
		User userEntity = userRepository.save(user);
		
		return userEntity;
		
	}

	@Transactional(readOnly = true)
	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}
	
}
