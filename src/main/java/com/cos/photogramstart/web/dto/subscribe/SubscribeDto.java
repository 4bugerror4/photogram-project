package com.cos.photogramstart.web.dto.subscribe;

import java.math.BigInteger;

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
public class SubscribeDto {
	
	private int userId;
	private String username;
	private String profileImageUrl;
	private BigInteger subscribeState;
	private BigInteger equalUserState;

}
