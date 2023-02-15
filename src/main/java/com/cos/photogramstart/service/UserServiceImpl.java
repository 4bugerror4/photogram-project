package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.repository.UserRepository;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;

	@Transactional
	@Override
	public User userUpdate(int id, User user) {
		
		User userEntity = userRepository.findById(id).orElseThrow(() -> new CustomValidationApiException("해당 아이디는 존재하지 않습니다."));
		
		userEntity.setName(user.getName());
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		
		return userEntity;
	}

	@Transactional(readOnly = true)
	@Override
	public UserProfileDto userProfile(int pageUserId, int principalId) {
		
		UserProfileDto dto = new UserProfileDto();
		
		User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
			throw new CustomException("해당 프로필 페이지는 없는 페이지 입니다.");
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerStatus(pageUserId == principalId); // true 로그인 한 페이지, false 로그인 안한 페이지
		dto.setImageCount(userEntity.getImages().size());
		
		return dto;
	}

}
