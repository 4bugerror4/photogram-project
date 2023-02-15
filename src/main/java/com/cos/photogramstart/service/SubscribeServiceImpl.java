package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.repository.SubscribeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {
	
	private final SubscribeRepository subscribeRepository;

	// 구독 하기
	@Transactional
	@Override
	public void subscribeUser(int fromUserId, int toUserId) {
		try {
			subscribeRepository.mSubscribe(fromUserId, toUserId);
		} catch (Exception e) {
			throw new CustomApiException("이미 구독한 유저입니다.");
		}
		
	}

	//구독 취소
	@Transactional
	@Override
	public void unSubscribeUser(int fromUserId, int toUserId) {
		
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}

}
