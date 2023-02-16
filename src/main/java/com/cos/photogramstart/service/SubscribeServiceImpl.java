package com.cos.photogramstart.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.repository.SubscribeRepository;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {
	
	private final SubscribeRepository subscribeRepository;
	private final EntityManager em;

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

	@Transactional(readOnly = true)
	@Override
	public List<SubscribeDto> subscribeList(int principalId, int pageUserId) {

		// 쿼리 준비
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id AS userId, u.username, u.profileImageUrl, ");
		sb.append("if((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id), 1, 0) subscribeState, ");
		sb.append("if((? = u.id), 1, 0) equalUserState ");
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.toUserId ");
		sb.append("WHERE s.fromUserId = ?");
		
		// 1. 물음표 principalId
		// 2. 물음표 principalId
		// 3. 마지막 물음표 pageUserId
		
		// 쿼리 완성
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2, principalId)
				.setParameter(3, pageUserId);
		
		// 쿼리 실행 (QLRM 라이브러리 필요 = DTO에 DB 결과 맵핑 하기 위해)
		JpaResultMapper result = new JpaResultMapper();
		List<SubscribeDto> dtos = result.list(query, SubscribeDto.class);
		
		return dtos;
	}
	
	

}
