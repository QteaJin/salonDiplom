package com.salon.service.worktime;

import java.util.List;

import com.salon.repository.bean.worktime.WorkTimeBean;
import com.salon.repository.entity.worktime.WorkTime;
import com.salon.service.CRUDService;

public interface WorkTimeService extends CRUDService<WorkTimeBean, WorkTime>{
	
	List<WorkTime> ListToDomain (List<WorkTimeBean> beans);
	List<WorkTimeBean> toBean(List<WorkTime> workTimes);

}
