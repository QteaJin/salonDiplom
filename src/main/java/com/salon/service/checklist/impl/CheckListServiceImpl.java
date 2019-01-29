package com.salon.service.checklist.impl;

import com.salon.repository.bean.checklist.CheckListBean;
import com.salon.repository.bean.quickorder.QuickOrderBean;
import com.salon.repository.dao.checklist.CheckListDAO;
import com.salon.repository.entity.checklist.CheckList;
import com.salon.service.checklist.CheckListService;
import com.salon.utility.EnumStatusCheckList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CheckListServiceImpl implements CheckListService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckListServiceImpl.class);

    @Autowired
    private CheckListDAO checkListDAO;

    @Override
    public CheckListBean save(CheckListBean bean) {
        LOGGER.debug("save CheckList start");

        CheckList checkList = checkListDAO.save(toDomain(bean));

        LOGGER.debug("save CheckList finish");

        return toBean(checkList);
    }

    @Override
    public List<CheckListBean> findAll() {
        LOGGER.debug("find All CheckList start");

        List<CheckList> checkList = checkListDAO.findAll();

        LOGGER.debug("find All CheckList finish");

        return toBean(checkList);
    }

    @Override
    public CheckListBean findById(Long id) {
        LOGGER.debug("find By id start");

        Optional<CheckList> optional = checkListDAO.findById(id);

        LOGGER.debug("find By id finish");

        return toBean(optional.get());
    }

    @Override
    public CheckListBean update(CheckListBean bean) {
        LOGGER.debug("update CheckList start");

        CheckList checkList = checkListDAO.saveAndFlush(toDomain(bean));

        LOGGER.debug("update CheckList finish");

        return toBean(checkList);
    }

    @Override
    public void delete(CheckListBean bean) {
        LOGGER.debug("CheckList delete");

        checkListDAO.delete(toDomain(bean));
    }

    @Override
    public QuickOrderBean createQuickOrde(QuickOrderBean orderBean) {
        if (orderBean == null) {
            //ошибка
        }

        CheckListBean checkListBean = new CheckListBean();
        checkListBean.setDateCreate(new Date(System.currentTimeMillis()));
        checkListBean.setStatus(EnumStatusCheckList.NEW);
        checkListBean.setDescription("Name: " + orderBean.getName()
                + " Phone: " + orderBean.getPhone()
                + "Email: " + orderBean.getEmail()
                + "Description: " + orderBean.getDescription());

        CheckListBean listBean = save(checkListBean);
        if (listBean != null && listBean.getSheckListId() != null) {
            return orderBean;
        }

        return new QuickOrderBean();
    }

    @Override
    public List<CheckListBean> findByExample(CheckListBean bean) {
        LOGGER.debug("find by example CheckList start");

        List<CheckList> workers = checkListDAO.findAll(Example.of(toDomain(bean)));

        LOGGER.debug("find by example CheckList finish");

        return toBean(workers);
    }


    @Override
    public CheckListBean toBean(CheckList domain) {
        CheckListBean checkListBean = new CheckListBean();
        checkListBean.setSheckListId(domain.getSheckListId());
        checkListBean.setCatalog(domain.getCatalog());
        checkListBean.setClient(domain.getClient());
        checkListBean.setDateAppointment(domain.getDateAppointment());
        checkListBean.setDateCreate(domain.getDateCreate());
        checkListBean.setDescription(domain.getDescription());
        checkListBean.setPrice(domain.getPrice());
        checkListBean.setWorker(domain.getWorker());
        checkListBean.setTimeFinish(domain.getTimeFinish());
        checkListBean.setStatus(domain.getStatus());
        return checkListBean;
    }

    @Override
    public CheckList toDomain(CheckListBean bean) {
        CheckList checkList = new CheckList();
        checkList.setSheckListId(bean.getSheckListId());
        checkList.setCatalog(bean.getCatalog());
        checkList.setClient(bean.getClient());
        checkList.setDateAppointment(bean.getDateAppointment());
        checkList.setDateCreate(bean.getDateCreate());
        checkList.setDescription(bean.getDescription());
        checkList.setPrice(bean.getPrice());
        checkList.setWorker(bean.getWorker());
        checkList.setTimeFinish(bean.getTimeFinish());
        checkList.setStatus(bean.getStatus());

        return checkList;
    }

    private List<CheckListBean> toBean(List<CheckList> checkLists) {
        List<CheckListBean> beans = new ArrayList<>();
        for (CheckList checkList : checkLists) {
            beans.add(toBean(checkList));
        }
        return beans;
    }
}
