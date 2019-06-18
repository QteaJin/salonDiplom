package com.salon.repository.dao.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.repository.entity.comment.Comment;

public interface CommentDAO extends JpaRepository<Comment, Long>{

	List<Comment> findAllByOrderByDate();
}
