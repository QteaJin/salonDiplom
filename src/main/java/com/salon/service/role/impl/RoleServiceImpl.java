package com.salon.service.role.impl;

import com.salon.repository.bean.role.RoleBean;
import com.salon.repository.dao.role.RoleDAO;
import com.salon.repository.entity.role.Role;
import com.salon.service.role.RoleService;
import com.salon.utility.EnumStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDAO roleDAO;


    @Override
    public RoleBean save(RoleBean bean) {
        logger.debug("save Role ");
        Role role = roleDAO.save(toBean(bean));
        return toDomain(role);
    }

    @Override
    public List<RoleBean> findAll() {
        logger.debug("find All Role start");

        List<Role> roles = roleDAO.findAll();

        logger.debug("find All Role finish");
        return toDomain(roles);
    }

    @Override
    public RoleBean findById(Long id) {
        logger.debug("find by id Role start");

        Optional<Role> role = roleDAO.findById(id);

        logger.debug("find by id Role finish");
        return toDomain(role.get());
    }

    @Override
    public RoleBean update(RoleBean bean) {
        logger.debug("update Role start");

        Role role = roleDAO.saveAndFlush(toBean(bean));

        return toDomain(role);
    }

    @Override
    public void delete(RoleBean bean) {
        logger.debug("delete Role start");

        roleDAO.delete(toBean(bean));

        logger.debug("delete Role start");
    }

    @Override
    public List<RoleBean> findByExample(RoleBean bean) {
        logger.debug("find by example Role start");

        List<Role> roles = roleDAO.findAll(Example.of(toBean(bean)));

        logger.debug("find by example Role finish");
        return toDomain(roles);
    }

    @Override
    public RoleBean findByName(String name) {
        RoleBean role = new RoleBean();
        role.setName(name);
        List<RoleBean> roleBeans = findByExample(role);
        if(!roleBeans.isEmpty()){
            role=roleBeans.get(0);
        }
        return role;
    }

    @Override
    public Role toBean(RoleBean domain) {
        Role role = new Role();
        role.setRoleId(domain.getRoleId());
        role.setName(domain.getName());
        role.setDescription(domain.getDescription());

        if (domain.getEnumStatus() == null) {
            role.setEnumStatus(EnumStatus.ACTIVE);
        } else {
            role.setEnumStatus(domain.getEnumStatus());
        }

        return role;
    }

    @Override
    public RoleBean toDomain(Role bean) {
        RoleBean roleBean = new RoleBean();
        roleBean.setRoleId(bean.getRoleId());
        roleBean.setName(bean.getName());
        roleBean.setDescription(bean.getDescription());
        roleBean.setEnumStatus(bean.getEnumStatus());
        return roleBean;
    }

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public List<RoleBean> toDomain(List<Role> roles) {
        List<RoleBean> roleBeans = new ArrayList<>();
        for (Role role : roles) {
            roleBeans.add(toDomain(role));
        }
        return roleBeans;
    }

    public List<Role> toBean(List<RoleBean> beans) {
        List<Role> roleBeans = new ArrayList<>();
        for (RoleBean bean : beans) {
            roleBeans.add(toBean(bean));
        }
        return roleBeans;
    }
}
