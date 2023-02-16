package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.repository.SubscribeRepository;
import com.cos.photogramstart.repository.UserRepository;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final SubscribeRepository subscribeRepository;
	private final BCryptPasswordEncoder encoder;
	
	@Value("${file.path}")
	private String uploadFolder;

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
		dto.setPageOwnerState(pageUserId == principalId); // true 로그인 한 페이지, false 로그인 안한 페이지
		dto.setImageCount(userEntity.getImages().size());
		
		int subscribeStats = subscribeRepository.mSubscribeState(principalId, pageUserId);
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
		
		dto.setSubscribeState(subscribeStats == 1);
		dto.setSubscribeCount(subscribeCount);
		
		userEntity.getImages().forEach((image) -> {
			image.setLikeCount(image.getLikes().size());
		});
		
		return dto;
	}

	@Transactional
	@Override
	public User userProfileImageUpdate(int principalId, MultipartFile profileImageFile) {

		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename(); // 실제 파일명
		System.out.println("이미지 파일이름 : " + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		try {
			Files.write(imageFilePath, profileImageFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		User user = userRepository.findById(principalId).orElseThrow(() -> {
			throw new CustomApiException("유저를 찾을 수 없습니다.");
		});
		
		user.setProfileImageUrl(imageFileName);
		
		return user;
	}

}
