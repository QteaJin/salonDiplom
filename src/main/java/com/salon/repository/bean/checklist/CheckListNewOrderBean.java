package com.salon.repository.bean.checklist;

import java.util.ArrayList;
import java.util.List;

public class CheckListNewOrderBean {

	private Long workerId;
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
	
	
}
