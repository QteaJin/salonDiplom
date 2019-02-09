package com.salon.repository.bean.worker;

import java.util.ArrayList;
import java.util.List;

import com.salon.repository.entity.skills.Skills;


public class WorkerProfileSkillsBean {
	private Long workerProfileId;
	private String description;
	private List<Skills> listSkills = new ArrayList<Skills>();
	
	public Long getWorkerProfileId() {
		return workerProfileId;
	}
	public void setWorkerProfileId(Long workerProfileId) {
		this.workerProfileId = workerProfileId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Skills> getListSkills() {
		return listSkills;
	}
	public void setListSkills(List<Skills> listSkills) {
		this.listSkills = listSkills;
	}
	

}
