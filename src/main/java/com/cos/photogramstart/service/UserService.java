package com.cos.photogramstart.service;

import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

public interface UserService {
	
	User userUpdate(int id, User user);
	UserProfileDto userProfile(int pageUserId, int principalId);
	User userProfileImageUpdate(int principalId, MultipartFile profileImageFile);

}
