package com.salon.controller.comment;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salon.repository.bean.client.ClientBean;
import com.salon.repository.bean.comment.CommentBean;
import com.salon.repository.bean.comment.SimpleCommentBean;
import com.salon.repository.entity.comment.Comment;
import com.salon.service.client.ClientService;
import com.salon.service.comment.CommentService;
import com.salon.utility.EnumStatus;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CommentBean findById(@PathVariable("id") long id) {
		return commentService.findById(id);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<CommentBean> findAllComments() {
		return commentService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public CommentBean createComment(@RequestBody CommentBean commentBean) {
		return commentService.save(commentBean);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable("id") long id) {
		commentService.delete(commentService.findById(id));
	}

	@RequestMapping(method = RequestMethod.PUT)
	public CommentBean updateAdress(@RequestBody CommentBean commentBean) {
		return commentService.update(commentBean);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public CommentBean testRequest(@CookieValue("token") String token, @RequestBody SimpleCommentBean simpleCommentBean) {
		ClientBean clientBean = clientService.findById(5L);
		CommentBean commentBean = new CommentBean();
		List<Comment> comments = clientBean.getComments();
		if(comments.size()==0) {
			comments = new ArrayList<Comment>();
		}
		commentBean.setDescription(simpleCommentBean.getDescription());
		commentBean.setDate(new Timestamp (System.currentTimeMillis()));
		commentBean.setClient(clientService.toDomain(clientBean));
		commentBean.setStatus(EnumStatus.ACTIVE);
		commentService.save(commentBean);
		comments.add(commentService.toDomain(commentBean));
		clientBean.setComments(comments);
		clientService.update(clientBean);
		
		return commentBean;
	}
}
