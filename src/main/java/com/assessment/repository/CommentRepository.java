package com.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
