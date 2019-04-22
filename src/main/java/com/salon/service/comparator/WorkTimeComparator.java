package com.salon.service.comparator;

import java.util.Comparator;

import com.salon.repository.entity.worktime.WorkTime;

public class WorkTimeComparator implements Comparator<WorkTime>{

	@Override
	public int compare(WorkTime o1, WorkTime o2) {
		
		return o1.getDate().compareTo(o2.getDate());
	}

}
