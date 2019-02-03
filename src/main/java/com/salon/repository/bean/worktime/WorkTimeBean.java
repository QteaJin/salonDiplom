package com.salon.repository.bean.worktime;

import java.sql.Timestamp;

import com.salon.utility.EnumStatus;

public class WorkTimeBean {
	private Long id;
	private Timestamp startWorking;
	private Timestamp finishWorking;
	private EnumStatus status;
	private Timestamp date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getStartWorking() {
		return startWorking;
	}

	public void setStartWorking(Timestamp startWorking) {
		this.startWorking = startWorking;
	}

	public Timestamp getFinishWorking() {
		return finishWorking;
	}

	public void setFinishWorking(Timestamp finishWorking) {
		this.finishWorking = finishWorking;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}
