package com.cos.photogramstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.photogramstart.domain.likes.Likes;

public interface LikesRepository extends JpaRepository<Likes, Integer>{

	@Modifying
	@Query(value = "INSERT INTO likes(imageId, userId, createdDate, modifiedDate) VALUES (:imageId, :principalId, now(), now())", nativeQuery = true)
	int mLikes(int imageId, int principalId);
	
	@Modifying
	@Query(value = "DELETE FROM likes WHERE imageId = :imageId AND userId = :principalId", nativeQuery = true)
	int mUnLikes(int imageId, int principalId);
}
