package com.salon.repository.bean.checklist;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CheckListNewOrderBean {

	private Long workerId;
	private Long clientId;
	private Timestamp dateAppointment;
	private List<Long> catalogList = new ArrayList<Long>();
	
	public Long getWorkerId() {
		return workerId;
	}
	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}
	public List<Long> getCatalogList() {
		return catalogList;
	}
	public void setCatalogList(List<Long> catalogList) {
		this.catalogList = catalogList;
	}
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
	
	
}
