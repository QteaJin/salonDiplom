package com.salon.repository.bean.salon;

import java.util.List;

import com.salon.repository.entity.address.Address;
import com.salon.repository.entity.client.Client;
import com.salon.repository.entity.worker.Worker;
import com.salon.repository.entity.worktime.WorkTime;

public class SalonBean {
	
	private Long salonId;
    private String name;
    private String description;
    private Address address;
    private List<WorkTime> timeList;
    private List<Worker> workerList;
    private List<Client> clientList;
	public Long getSalonId() {
		return salonId;
	}
	public void setSalonId(Long salonId) {
		this.salonId = salonId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<WorkTime> getTimeList() {
		return timeList;
	}
	public void setTimeList(List<WorkTime> timeList) {
		this.timeList = timeList;
	}
	public List<Worker> getWorkerList() {
		return workerList;
	}
	public void setWorkerList(List<Worker> workerList) {
		this.workerList = workerList;
	}
	public List<Client> getClientList() {
		return clientList;
	}
	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}
    
    

}
