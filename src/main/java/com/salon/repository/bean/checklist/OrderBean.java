package com.salon.repository.bean.checklist;

import java.sql.Timestamp;

import com.salon.utility.EnumStatusCheckList;

public class OrderBean {
	private Timestamp date;
	private EnumStatusCheckList status;

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public EnumStatusCheckList getStatus() {
		return status;
	}

	public void setStatus(EnumStatusCheckList status) {
		this.status = status;
	}

}
