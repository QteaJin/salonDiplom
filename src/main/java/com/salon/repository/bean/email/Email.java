package com.salon.repository.bean.email;



import com.fasterxml.jackson.annotation.JsonProperty;


public class Email {
	private String from;
	private String to;
	private String subject;
	private String message;
	@JsonProperty
	private boolean isHtml;
	
	public Email() {
		
	}

	public Email(String from, String to, String subject, String message, boolean isHtml) {
		
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.message = message;
		this.isHtml = isHtml;
	}

	public boolean isHtml() {
		return isHtml;
	}

	public void setHtml(boolean isHtml) {
		this.isHtml = isHtml;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
