package com.salon.repository.bean.comment;

import java.sql.Timestamp;

import com.salon.repository.entity.client.Client;
import com.salon.utility.EnumStatus;

public class CommentBean {
	
	private Long id;
	private String description;
	private Client client;
	private Timestamp date;
	private EnumStatus status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public EnumStatus getStatus() {
		return status;
	}
	public void setStatus(EnumStatus status) {
		this.status = status;
	}
	
	

}
