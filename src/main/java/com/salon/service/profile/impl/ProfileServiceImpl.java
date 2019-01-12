package com.salon.service.profile.impl;

import com.salon.repository.bean.worker.WorkerBean;
import com.salon.repository.dao.profile.ProfileDAO;
import com.salon.repository.entity.profile.Profile;
import com.salon.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileDAO profileDAO;

    @Override
    public WorkerBean save(WorkerBean bean) {
        Profile profile = profileDAO.save(toBean(bean));

        return toDomain(profile);
    }

    @Override
    public List<WorkerBean> findAll() {
        List<Profile> profiles = profileDAO.findAll();
        return toDomain(profiles);
    }

    @Override
    public WorkerBean findById(Long id) {
        Optional<Profile> profile = profileDAO.findById(id);
        return toDomain(profile.get());
    }

    @Override
    public WorkerBean update(WorkerBean bean) {
        Profile profile = profileDAO.saveAndFlush(toBean(bean));
        return toDomain(profile);
    }

    @Override
    public void delete(WorkerBean bean) {
        profileDAO.delete(toBean(bean));
    }

    @Override
    public List<WorkerBean> findByExample(WorkerBean bean) {
        List<Profile> profiles = profileDAO.findAll(Example.of(toBean(bean)));
        return toDomain(profiles);
    }

    @Override
    public Profile toBean(WorkerBean domain) {
        Profile profile = new Profile();
        profile.setProfileId(domain.getId());
        profile.setName(domain.getName());
        profile.setPhone(domain.getPhone());
        profile.setEmail(domain.getEmail());
        profile.setLogin(domain.getLogin());
        profile.setPassword(domain.getPassword());
        return profile;
    }

    @Override
    public WorkerBean toDomain(Profile bean) {
        WorkerBean workerBean = new WorkerBean();
        workerBean.setId(bean.getProfileId());
        workerBean.setName(bean.getName());
        workerBean.setLogin(bean.getLogin());
        workerBean.setPassword(bean.getPassword());
        workerBean.setPhone(bean.getPhone());
        workerBean.setEmail(bean.getEmail());
        return workerBean;
    }

    private List<WorkerBean> toDomain(List<Profile> profiles) {
        List<WorkerBean> workerBeans = new ArrayList<>();
        for (Profile profile : profiles) {
            workerBeans.add(toDomain(profile));
        }
        return workerBeans;
    }
}
