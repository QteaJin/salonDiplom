package com.salon.repository.bean.worker;

public class WorkerUpdateSkillBean {

	private Long workerId;
	private Long skillsId;
	private String change;

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public Long getSkillsId() {
		return skillsId;
	}

	public void setSkillsId(Long skillsId) {
		this.skillsId = skillsId;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

}
