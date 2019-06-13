package com.salon.repository.entity.comment;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.salon.repository.entity.client.Client;
import com.salon.utility.EnumStatus;

@Entity
@Table(name = "comment")
public class Comment implements Serializable{

	private Long id;
	private String description;
	private Client client;
	private Timestamp date;
	private EnumStatus status;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false, unique = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "date_create")
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
    @ManyToOne()
    @JoinColumn(name = "client_id")
    @JsonManagedReference
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
	public EnumStatus getStatus() {
		return status;
	}
	public void setStatus(EnumStatus status) {
		this.status = status;
	}
	
	
}
