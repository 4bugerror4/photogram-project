package com.cos.photogramstart.service;

import java.util.List;

import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

public interface SubscribeService {
	
	public void subscribeUser(int fromUserId, int toUserId);
	public void unSubscribeUser(int fromUserId, int toUserId);
	public List<SubscribeDto> subscribeList(int principalId, int pageUserId);

}
