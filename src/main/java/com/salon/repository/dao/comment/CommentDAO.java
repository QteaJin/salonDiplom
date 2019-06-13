package com.salon.repository.dao.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.repository.entity.comment.Comment;

public interface CommentDAO extends JpaRepository<Comment, Long>{

}
