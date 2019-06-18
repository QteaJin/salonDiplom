package com.salon.controller.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salon.repository.bean.comment.CommentBean;
import com.salon.repository.bean.comment.SimpleCommentBean;
import com.salon.service.comment.CommentService;


@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
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
	public boolean addComment(@CookieValue("token") String token, @RequestBody SimpleCommentBean simpleCommentBean) {
		
		return commentService.addNewComment(token, simpleCommentBean);
	}
	
	@RequestMapping(value = "/allSortByDate", method = RequestMethod.GET)
	public List<CommentBean> getAllSortByDate(){
		
		return commentService.getAllByDate();
	}
	
	
	@RequestMapping(value = "/change/{id}", method = RequestMethod.GET)
	public boolean changeCommentStatus(@CookieValue("token") String token, @PathVariable("id") Long id) {
		
		return commentService.changeCommentStatus(token, id);
		
	}
	@RequestMapping(value = "/findActive", method = RequestMethod.GET)
	public List<CommentBean> findAllActive(){
		
		return commentService.findAllByDateAndActive();
			
	}
}
