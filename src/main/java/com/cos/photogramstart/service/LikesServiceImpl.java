package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.repository.LikesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {
	
	private final LikesRepository likesRepository;

	@Transactional
	@Override
	public void like(int imageId, int principalId) {
		likesRepository.mLikes(imageId, principalId);
	}

	@Transactional
	@Override
	public void unLike(int imageId, int principalId) {
		likesRepository.mUnLikes(imageId, principalId);
	}

}
