package com.salon.service.worker;

import com.salon.repository.bean.client.WorkerCustumBean;
import com.salon.repository.bean.worker.WorkerBean;
import com.salon.repository.bean.worker.WorkerCreateUpdateBean;
import com.salon.repository.bean.worker.WorkerProfileSkillsBean;
import com.salon.repository.bean.worker.WorkerUpdateSkillBean;
import com.salon.repository.bean.worker.WorkingDays;
import com.salon.repository.entity.worker.Worker;
import com.salon.repository.entity.worktime.WorkTime;
import com.salon.service.CRUDService;

import java.util.List;

public interface WorkerService extends CRUDService<WorkerBean, Worker> {
	List<WorkerCustumBean> getWorkersBySalon(Long salonId);

	WorkerProfileSkillsBean getWorkerProfileSkillsById(Long workerId);

	List<WorkerBean> getWorkersByCity(String city);

	WorkerCreateUpdateBean getWorkerById(long workerId);

	WorkerCreateUpdateBean updateWorkerData(WorkerCreateUpdateBean workerBean);

	boolean updateSkillWorker(WorkerUpdateSkillBean workerBean);

	List<WorkTime> addWorkingDays(WorkingDays days);

	WorkingDays getWorkingDays(Long workerId);

	List<WorkTime> delWorkingDays(WorkingDays days);
}
