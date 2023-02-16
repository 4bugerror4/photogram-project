package com.cos.photogramstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.photogramstart.domain.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
