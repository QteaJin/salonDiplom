package com.salon.repository.bean.worker;

public class WorkerCreateUpdateBean {

	private Long salonId;
    private Long workerId;
    private String name;
    private String phone;
    private String email;
    private String login;
    private String password;
    private String description;
    private String shortDescription;
    
    
	public Long getWorkerId() {
		return workerId;
	}
	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getSalonId() {
		return salonId;
	}
	public void setSalonId(Long salonId) {
		this.salonId = salonId;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
    
    
	
}
