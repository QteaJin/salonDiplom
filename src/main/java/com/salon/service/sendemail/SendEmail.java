package com.salon.service.sendemail;

import com.salon.repository.bean.email.Email;

public interface SendEmail {

	
	public boolean sendEmailToUser(Email email);
}
