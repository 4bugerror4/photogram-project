package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.comment.Comment;

public interface CommentService {
	
	Comment writeReply(String content, int imageId, int principalId);
	void deleteReply(int commentId);

}
