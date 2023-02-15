package com.cos.photogramstart.service;

public interface SubscribeService {
	
	public void subscribeUser(int fromUserId, int toUserId);
	public void unSubscribeUser(int fromUserId, int toUserId);

}
