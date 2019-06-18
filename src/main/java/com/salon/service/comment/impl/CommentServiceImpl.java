package com.salon.service.comment.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.repository.bean.client.ClientBean;
import com.salon.repository.bean.comment.CommentBean;
import com.salon.repository.bean.comment.SimpleCommentBean;
import com.salon.repository.dao.comment.CommentDAO;
import com.salon.repository.entity.comment.Comment;
import com.salon.service.client.ClientService;
import com.salon.service.comment.CommentService;
import com.salon.service.crypto.TokenCrypt;
import com.salon.utility.EnumRole;
import com.salon.utility.EnumStatus;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private TokenCrypt tokenCrypt; 
	
	@Autowired
	private ClientService clientService;

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

	@Override
	public boolean addNewComment(String token, SimpleCommentBean simpleCommentBean) {
		boolean flag = false;
		AuthBean authBean = tokenCrypt.checkToken(token);
		if(authBean.getErrorMessage() == null && authBean.getEnumRole().equals(EnumRole.CLIENT)) {
			ClientBean clientBean = clientService.findById(authBean.getUserId());
			CommentBean commentBean = new CommentBean();
			List<Comment> comments = clientBean.getComments();
			if(comments.size()==0) {
				comments = new ArrayList<Comment>();
			}
			commentBean.setDescription(simpleCommentBean.getDescription());
			commentBean.setDate(new Timestamp (System.currentTimeMillis()));
			commentBean.setClient(clientService.toDomain(clientBean));
			commentBean.setStatus(EnumStatus.ACTIVE);
			save(commentBean);
			comments.add(toDomain(commentBean));
			clientBean.setComments(comments);
			clientService.update(clientBean);
			flag = true;
		}
		return flag;
	}

	@Override
	public List<CommentBean> getAllByDate() {
		List<Comment> comments = commentDAO.findAllByOrderByDate();
		return toBean(comments);
	}

	@Override
	public boolean changeCommentStatus(String token, Long id) {
		boolean flag = false;
		AuthBean authBean = tokenCrypt.checkToken(token);
		if(authBean.getErrorMessage() == null && authBean.getEnumRole().equals(EnumRole.ADMIN)) {
			CommentBean commentBean = findById(id);
			if(commentBean.getStatus().equals(EnumStatus.ACTIVE)) {
				commentBean.setStatus(EnumStatus.NOACTIVE);
			}else {
				commentBean.setStatus(EnumStatus.ACTIVE);
			}
			update(commentBean);
			flag = true;
		}
		return flag;
	}

	@Override
	public List<CommentBean> findAllByDateAndActive() {
		List<CommentBean> commenBeans = getAllByDate();
		List<CommentBean> selectedBeans = new ArrayList<CommentBean>();
		for (CommentBean commentBean : commenBeans) {
			if(commentBean.getStatus().equals(EnumStatus.NOACTIVE)) {
				continue;
			}
			selectedBeans.add(commentBean);
		}
		return selectedBeans;
	}
}
