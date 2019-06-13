package com.salon.service.comment.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.salon.repository.bean.comment.CommentBean;
import com.salon.repository.dao.comment.CommentDAO;
import com.salon.repository.entity.comment.Comment;
import com.salon.service.comment.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDAO commentDAO;

	@Override
	public CommentBean save(CommentBean bean) {
		
		Comment comment = commentDAO.save(toDomain(bean));
		return toBean(comment);
	}

	@Override
	public List<CommentBean> findAll() {
		List<Comment> comments = commentDAO.findAll();
		return toBean(comments);
	}

	@Override
	public CommentBean findById(Long id) {
		Optional<Comment> comment = commentDAO.findById(id);
		return toBean(comment.get());
	}

	@Override
	public CommentBean update(CommentBean bean) {
		Comment comment = commentDAO.saveAndFlush(toDomain(bean));
		return toBean(comment);
	}

	@Override
	public void delete(CommentBean bean) {
		commentDAO.delete(toDomain(bean));
		
	}

	@Override
	public List<CommentBean> findByExample(CommentBean bean) {
		List<Comment> comments = commentDAO.findAll(Example.of(toDomain(bean)));
		return toBean(comments);
	}

	@Override
	public CommentBean toBean(Comment domain) {
		CommentBean bean = new CommentBean();
		bean.setClient(domain.getClient());
		bean.setDate(domain.getDate());
		bean.setDescription(domain.getDescription());
		bean.setId(domain.getId());
		bean.setStatus(domain.getStatus());
		return bean;
	}

	@Override
	public Comment toDomain(CommentBean bean) {
		Comment comment = new Comment();
		comment.setClient(bean.getClient());
		comment.setDate(bean.getDate());
		comment.setDescription(bean.getDescription());
		comment.setId(bean.getId());
		comment.setStatus(bean.getStatus());
		return comment;
	}
	
	private List<CommentBean> toBean(List<Comment> comments){
		List<CommentBean> beans = new ArrayList<CommentBean>();
		for (Comment comment : comments) {
			beans.add(toBean(comment));
		}
		return beans;
		
	}
}
