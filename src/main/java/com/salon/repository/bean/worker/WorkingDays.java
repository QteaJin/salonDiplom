package com.salon.repository.bean.worker;

import java.util.ArrayList;
import java.util.List;

public class WorkingDays {

	private Long workerId;
	private List<Long> datesList = new ArrayList<Long>();

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public List<Long> getDatesList() {
		return datesList;
	}

	public void setDatesList(List<Long> datesList) {
		this.datesList = datesList;
	}

}
