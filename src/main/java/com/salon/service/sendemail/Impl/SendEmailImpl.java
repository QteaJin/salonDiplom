package com.salon.service.sendemail.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.salon.repository.bean.email.Email;
import com.salon.service.sendemail.SendEmail;
import com.salon.utility.Const;

@Service
public class SendEmailImpl implements SendEmail {
	public final static String path = "D://test.html"; // this is for test - sending email with html body

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public boolean sendEmailToUser(Email email) {

		MimeMessage message = emailSender.createMimeMessage();
		emailSender.send(createHelper(message, email, email.isHtml()));
		return true;
	}

	public MimeMessage createHelper(MimeMessage message, Email email, boolean isHtml) { // create eMail and check is it
																						// with html body

		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom(new InternetAddress(Const.MyEmail.MY_EMAIL, email.getFrom()));
			helper.setTo(email.getTo());
			helper.setSubject(email.getSubject());
			if (isHtml) {
				String htmlMessage = createHtmlMessage(email);
				helper.setText(htmlMessage, isHtml);
			} else {
				helper.setText(email.getMessage());
			}

		} catch (UnsupportedEncodingException | MessagingException e) {

			e.printStackTrace();
		}

		return message;

	}

	public String createHtmlMessage(Email email) { // reading from file html
		File file = new File(path);

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			String result = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
			fileInputStream.close();
			return result;
		} catch (IOException e) {

			e.printStackTrace();
		}

		return "<H1>Problem with creating HTML file!</H1>";

	}
}
