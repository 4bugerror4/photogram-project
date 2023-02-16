package com.cos.photogramstart.service;

public interface LikesService {

	public void like(int imageId, int principalId);
	public void unLike(int imageId, int principalId);
	
}
