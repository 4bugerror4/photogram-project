package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.repository.CommentRepository;
import com.cos.photogramstart.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;

	@Transactional
	@Override
	public Comment writeReply(String content, int imageId, int principalId) {
		
		Image image = new Image();
		image.setId(imageId);
		
		User user = userRepository.findById(principalId).orElseThrow(() -> {
			throw new CustomApiException("해당 아이디는 찾지 못했습니다.");
		});
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setImage(image);
		comment.setUser(user);
		
		Comment commentEntity = commentRepository.save(comment);
		
		return commentEntity;
	}

	@Transactional
	@Override
	public void deleteReply(int commentId) {
		
		try {
			commentRepository.deleteById(commentId);
		} catch (Exception e) {
			throw new CustomApiException(e.getMessage());
		}
		
	}
	
}
