package com.salon.repository.bean.catalog;

import java.sql.Timestamp;
import java.util.List;

import com.salon.repository.entity.checklist.CheckList;
import com.salon.repository.entity.skills.Skills;

public class CatalogBean {

	private Long catalogId;
    private String name;
    private String description;
    private Double price;
    private Timestamp timeLead;

    private Skills skills;
    private List<CheckList> checkLists;
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
	public Timestamp getTimeLead() {
		return timeLead;
	}
	public void setTimeLead(Timestamp timeLead) {
		this.timeLead = timeLead;
	}
	public Skills getSkills() {
		return skills;
	}
	public void setSkills(Skills skills) {
		this.skills = skills;
	}
	public List<CheckList> getCheckLists() {
		return checkLists;
	}
	public void setCheckLists(List<CheckList> checkLists) {
		this.checkLists = checkLists;
	}
    
    
}
