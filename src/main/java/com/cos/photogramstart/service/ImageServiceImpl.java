package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.repository.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
	
	private final ImageRepository imageRepository;
	
	@Value("${file.path}")
	private String uploadFolder;

	@Transactional
	@Override
	public void imageUplaod(ImageUploadDto dto, PrincipalDetails principal) {
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + dto.getFile().getOriginalFilename(); // 실제 파일명
		System.out.println("이미지 파일이름 : " + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		try {
			Files.write(imageFilePath, dto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image 테이블 저장
		Image image = dto.toEntity(imageFileName, principal.getUser());
		imageRepository.save(image);
		
	}

}
