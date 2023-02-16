package com.cos.photogramstart.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

public interface ImageService {
	
	public void imageUplaod(ImageUploadDto dto, PrincipalDetails principal);
	Page<Image> imageStory(int principalId, Pageable pageable);
	List<Image> imagePapular();

}
