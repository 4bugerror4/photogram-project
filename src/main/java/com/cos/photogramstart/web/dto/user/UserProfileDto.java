package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {
	
	private boolean pageOwnerState; // 페이지 주인
	private User user;
	private int imageCount;
	private boolean subscribeState; // 구독 상태
	private int subscribeCount; // 구독한 유저의 수

}
