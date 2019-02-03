package com.salon.service.profile.impl;

import com.salon.repository.bean.profile.ProfileBean;
import com.salon.repository.dao.profile.ProfileDAO;
import com.salon.repository.entity.profile.Profile;
import com.salon.service.client.impl.ClientServiceImpl;
import com.salon.service.profile.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProfileServiceImpl implements ProfileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ProfileDAO profileDAO;


    @Override
    public ProfileBean save(ProfileBean bean) {
        Profile profile = profileDAO.save(toDomain(bean));
        return toBean(profile);
    }

    @Override
    public List<ProfileBean> findAll() {
        List<Profile> profiles = profileDAO.findAll();
        return toDomain(profiles);
    }

    @Override
    public ProfileBean findById(Long id) {
        Optional<Profile> profile = profileDAO.findById(id);
        return toBean(profile.get());
    }

    @Override
    public ProfileBean update(ProfileBean bean) {
        Profile profile = profileDAO.saveAndFlush(toDomain(bean));
        return toBean(profile);
    }

    @Override
    public void delete(ProfileBean bean) {
        profileDAO.delete(toDomain(bean));
    }

    @Override
    public List<ProfileBean> findByExample(ProfileBean bean) {
        List<Profile> profiles = profileDAO.findAll(Example.of(toDomain(bean)));
        return toDomain(profiles);
    }



    @Override
    public Profile toDomain(ProfileBean domain) {
        Profile profile = new Profile();
        profile.setProfileId(domain.getId());
        profile.setName(domain.getName());
        profile.setPhone(domain.getPhone());
        profile.setEmail(domain.getEmail());
        profile.setLogin(domain.getLogin());
        profile.setPassword(domain.getPassword());
        profile.setWorker(domain.getWorker());
        profile.setClient(domain.getClient());
        return profile;
    }

    @Override
    public ProfileBean toBean(Profile bean) {
        ProfileBean profileBean = new ProfileBean();
        profileBean.setId(bean.getProfileId());
        profileBean.setName(bean.getName());
        profileBean.setEmail(bean.getEmail());
        profileBean.setPhone(bean.getPhone());
        profileBean.setLogin(bean.getLogin());
        profileBean.setPassword(bean.getPassword());
        profileBean.setWorker(bean.getWorker());
        profileBean.setClient(bean.getClient());
        return profileBean;
    }

    List<ProfileBean> toDomain(List<Profile> profiles) {
        List<ProfileBean> beanList = new ArrayList<>();
        for (Profile profile : profiles) {
            beanList.add(toBean(profile));
        }
        return beanList;
    }
}
