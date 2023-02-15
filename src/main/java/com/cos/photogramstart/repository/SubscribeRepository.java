package com.cos.photogramstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.photogramstart.domain.subscribe.Subscribe;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
	
	@Modifying
	@Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createdDate, modifiedDate) VALUES (:fromUserId, :toUserId, now(), now())", nativeQuery = true)
	void mSubscribe(int fromUserId, int toUserId);
	
	@Modifying
	@Query(value = "DELTE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
	void mUnSubscribe(int fromUserId, int toUserId);

}
