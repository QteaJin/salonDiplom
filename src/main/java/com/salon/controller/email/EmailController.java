package com.salon.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salon.repository.bean.email.Email;
import com.salon.service.sendemail.SendEmail;
import com.salon.utility.Const;

@RestController()
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	SendEmail sendEmail;

	 @RequestMapping(value = "/admin", method = RequestMethod.POST)
	 public boolean sendEmailToAdmin(@RequestBody Email email) {
		 String subject = "Сообщение от клиента - ";
		 email.setFrom(Const.MyEmail.MY_EMAIL); //sending email to myself
		 email.setTo(Const.MyEmail.MY_EMAIL);
		 email.setHtml(false);
		 if(email.getSubject().isEmpty()) {
			 email.setSubject(subject);
		 }else {
			 email.setSubject(subject.concat(email.getSubject())); 
		 }
		 
		 sendEmail.sendEmailToUser(email);
		 
		 return true;
	 }
	
}
