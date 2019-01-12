package com.salon.service.worker.impl;

import com.salon.repository.bean.role.RoleBean;
import com.salon.repository.bean.worker.WorkerBean;
import com.salon.repository.dao.worker.WorkerDAO;
import com.salon.repository.entity.profile.Profile;
import com.salon.repository.entity.worker.Worker;
import com.salon.service.profile.ProfileService;
import com.salon.service.role.RoleService;
import com.salon.service.worker.WorkerService;
import com.salon.utility.EnumStatus;
import com.salon.utility.EnumUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerDAO workerDAO;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private RoleService roleService;

    @Override
    public WorkerBean save(WorkerBean bean) {
        WorkerBean workerBean = profileService.save(bean);

        Worker worker = new Worker();
        worker.setProfile(profileService.toBean(workerBean));

        RoleBean roleBean = roleService.findByName(EnumUser.WORKER.name());
        worker.setRole(roleService.toBean(roleBean));

        worker.setStatus(EnumStatus.NOACTIVE);

        worker = workerDAO.save(worker);
        return toDomain(worker);
    }

    @Override
    public List<WorkerBean> findAll() {
        List<Worker> workers = workerDAO.findAll();
        return getWorkerBean(workers);
    }

    @Override
    public WorkerBean findById(Long id) {
        Optional<Worker> optional = workerDAO.findById(id);
        return toDomain(optional.get());
    }

    @Override
    public WorkerBean update(WorkerBean bean) {
        return profileService.update(bean);
    }

    @Override
    public void delete(WorkerBean bean) {
        List<WorkerBean> workerBeans = profileService.findByExample(bean);
        WorkerBean workerBean=workerBeans.get(0);
        profileService.delete(workerBean);
        Worker worker=new Worker();

    }

    @Override
    public List<WorkerBean> findByExample(WorkerBean bean) {
        return null;
    }

    @Override
    public Worker toBean(WorkerBean domain) {
        Worker worker = new Worker();

        Profile profile = new Profile();
        profile.setProfileId(domain.getId());
        profile.setName(profile.getName());
        profile.setEmail(profile.getEmail());
        profile.setPhone(profile.getPhone());
        profile.setLogin(profile.getLogin());
        profile.setPassword(profile.getPassword());

        worker.setProfile(profile);
        return worker;
    }

    @Override
    public WorkerBean toDomain(Worker bean) {
        WorkerBean workerBean = new WorkerBean();

        Profile profil = bean.getProfile();
        workerBean.setId(profil.getProfileId());
        workerBean.setName(profil.getName());
        workerBean.setEmail(profil.getEmail());
        workerBean.setPhone(profil.getPhone());
        workerBean.setLogin(profil.getLogin());
        workerBean.setPassword(profil.getPassword());
        return workerBean;
    }

    private List<WorkerBean> getWorkerBean(List<Worker> workers) {
        List<WorkerBean> workerBeans = new ArrayList<>();
        for (Worker worker : workers) {
            workerBeans.add(profileService.toDomain(worker.getProfile()));
        }
        return workerBeans;
    }

}
