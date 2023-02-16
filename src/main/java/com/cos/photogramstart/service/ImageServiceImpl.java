package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Transactional(readOnly = true)
	@Override
	public Page<Image> imageStory(int principalId, Pageable pageable) {

		Page<Image> images = imageRepository.mStory(principalId, pageable);
		
		// images에 좋아요 상태 담기
		images.forEach((image) -> {
			
			image.setLikeCount(image.getLikes().size());
			
			image.getLikes().forEach((like) -> {
				if (like.getUser().getId() == principalId) {
					image.setLikeState(true);
				}
			});
		});
		
		return images;
	}

	@Override
	public List<Image> imagePapular() {

		return imageRepository.mPopular();
	}

}
