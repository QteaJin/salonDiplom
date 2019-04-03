package com.salon.repository.bean.catalog;

public class CatalogBeanCreateAdmin {

	private Long catalogId;
    private String name;
    private String description;
    private Double price;
    private Long timeLead;
    private Long skillId;
    private String token;
    
	public Long getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getTimeLead() {
		return timeLead;
	}
	public void setTimeLead(Long timeLead) {
		this.timeLead = timeLead;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
    
}
