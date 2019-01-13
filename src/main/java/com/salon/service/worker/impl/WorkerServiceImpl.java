package com.salon.service.worker.impl;
import com.salon.repository.bean.worker.WorkerBean;
import com.salon.repository.dao.worker.WorkerDAO;
import com.salon.repository.entity.worker.Worker;
import com.salon.service.role.RoleService;
import com.salon.service.worker.WorkerService;
import com.salon.utility.EnumStatus;
import com.salon.utility.EnumUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerServiceImpl.class);

    @Autowired
    private WorkerDAO workerDAO;

    @Autowired
    private RoleService roleService;

    @Override
    public WorkerBean save(WorkerBean bean) {
        LOGGER.debug("save Worker start");

        Worker worker = workerDAO.save(toDomain(bean));

        LOGGER.debug("save Worker finish");

        return toBean(worker);
    }

    @Override
    public List<WorkerBean> findAll() {
        LOGGER.debug("find All Worker start");

        List<Worker> workers = workerDAO.findAll();

        LOGGER.debug("find All Worker finish");

        return toDomain(workers);
    }

    @Override
    public WorkerBean findById(Long id) {
        LOGGER.debug("find By id start");

        Optional<Worker> optional = workerDAO.findById(id);

        LOGGER.debug("find By id finish");

        return toBean(optional.get());
    }


    @Override
    public WorkerBean update(WorkerBean bean) {
        LOGGER.debug("update Worker start");

        Worker worker = workerDAO.saveAndFlush(toDomain(bean));

        LOGGER.debug("update Worker finish");

        return toBean(worker);
    }

    @Override
    public void delete(WorkerBean bean) {
        LOGGER.debug("Worker delete");

        workerDAO.delete(toDomain(bean));
    }

    @Override
    public List<WorkerBean> findByExample(WorkerBean bean) {
        LOGGER.debug("find by example Worker start");

        List<Worker> workers = workerDAO.findAll(Example.of(toDomain(bean)));

        LOGGER.debug("find by example Worker finish");

        return toDomain(workers);
    }

    @Override
    public Worker toDomain(WorkerBean domain) {
        Worker worker = new Worker();
        worker.setId(domain.getId());

        if (Objects.isNull(domain.getRole())) {
            worker.setRole(roleService.toDomain(roleService.findByName(
                    EnumUser.WORKER.name())));
        } else {
            worker.setRole(domain.getRole());
        }

        if(Objects.isNull(domain.getStatus())){
            worker.setStatus(EnumStatus.NOACTIVE);
        }else {
            worker.setStatus(domain.getStatus());
        }

        worker.setProfile(domain.getProfile());
        worker.setSalon(domain.getSalon());
        worker.setCheckLists(domain.getCheckLists());
        worker.setSkillsList(domain.getSkillsList());

        return worker;
    }

    @Override
    public WorkerBean toBean(Worker bean) {
        WorkerBean workerBean = new WorkerBean();
        workerBean.setId(bean.getId());
        workerBean.setProfile(bean.getProfile());
        workerBean.setRole(bean.getRole());
        workerBean.setStatus(bean.getStatus());
        workerBean.setSalon(bean.getSalon());
        workerBean.setCheckLists(bean.getCheckLists());
        workerBean.setSkillsList(bean.getSkillsList());
        return workerBean;
    }

    List<WorkerBean> toDomain(List<Worker> workers) {
        List<WorkerBean> list = new ArrayList<>();
        for (Worker worker : workers) {
            list.add(toBean(worker));
        }
        return list;
    }

}
