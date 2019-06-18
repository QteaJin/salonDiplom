package com.salon.service.comment;

import java.util.List;

import com.salon.repository.bean.comment.CommentBean;
import com.salon.repository.bean.comment.SimpleCommentBean;
import com.salon.repository.entity.comment.Comment;
import com.salon.service.CRUDService;

public interface CommentService extends CRUDService<CommentBean, Comment>{

	boolean addNewComment(String token, SimpleCommentBean simpleCommentBean);

	List<CommentBean> getAllByDate();

	boolean changeCommentStatus(String token, Long id);
	
	List<CommentBean> findAllByDateAndActive();
}
