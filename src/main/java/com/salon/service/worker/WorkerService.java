package com.salon.service.worker;

import com.salon.repository.bean.client.WorkerCustumBean;
import com.salon.repository.bean.worker.WorkerBean;
import com.salon.repository.bean.worker.WorkerProfileSkillsBean;
import com.salon.repository.entity.worker.Worker;
import com.salon.service.CRUDService;

import java.util.List;

public interface WorkerService extends CRUDService<WorkerBean,Worker> {
    List<WorkerCustumBean> getWorkersBySalon(Long salonId);
    WorkerProfileSkillsBean getWorkerProfileSkillsById(Long workerId);
}
