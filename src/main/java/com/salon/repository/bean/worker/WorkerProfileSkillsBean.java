package com.salon.repository.bean.worker;

import java.util.ArrayList;
import java.util.List;

import com.salon.repository.entity.skills.Skills;


public class WorkerProfileSkillsBean {
	private Long workerProfileId;
	private Long workerId;
	private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getWorkerId() {
		return workerId;
	}
	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}
	

}
