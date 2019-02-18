package com.salon.service.checklist;

import java.util.Comparator;

import com.salon.repository.entity.checklist.CheckList;

public class CheckListComparatorByDate implements Comparator<CheckList>{

	@Override
	public int compare(CheckList o1, CheckList o2) {
		
		return o1.getDateAppointment().compareTo(o2.getDateAppointment());
	}

}
