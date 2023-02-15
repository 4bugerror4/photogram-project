package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

public interface ImageService {
	
	public void imageUplaod(ImageUploadDto dto, PrincipalDetails principal);

}
