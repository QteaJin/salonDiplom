package com.salon.repository.bean.checklist;

import java.sql.Timestamp;
import java.util.List;

import com.salon.repository.entity.catalog.Catalog;
import com.salon.repository.entity.salon.Salon;
import com.salon.repository.entity.worker.Worker;
import com.salon.utility.EnumStatusCheckList;

public class CheckListClientHistoryBean {

	private Long clientId;
	private String name;
	private Long checkListId;
	private Timestamp dateAppointment;
	private Timestamp finish;
	private String salon;
	private String worker;
	private List<Catalog> catalogs;
	private Double price;
	private EnumStatusCheckList status;
	private String token;
	
	
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Timestamp getDateAppointment() {
		return dateAppointment;
	}
	public void setDateAppointment(Timestamp dateAppointment) {
		this.dateAppointment = dateAppointment;
	}
	public String getSalon() {
		return salon;
	}
	public void setSalon(String salon) {
		this.salon = salon;
	}
	public String getWorker() {
		return worker;
	}
	public void setWorker(String worker) {
		this.worker = worker;
	}
	public List<Catalog> getCatalogs() {
		return catalogs;
	}
	public void setCatalogs(List<Catalog> catalogs) {
		this.catalogs = catalogs;
	}
	public EnumStatusCheckList getStatus() {
		return status;
	}
	public void setStatus(EnumStatusCheckList status) {
		this.status = status;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getCheckListId() {
		return checkListId;
	}
	public void setCheckListId(Long checkListId) {
		this.checkListId = checkListId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getFinish() {
		return finish;
	}
	public void setFinish(Timestamp finish) {
		this.finish = finish;
	}
	
	
	
}
