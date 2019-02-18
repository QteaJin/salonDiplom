package com.salon.service.checklist.impl;

import com.salon.repository.bean.checklist.CheckListBean;
import com.salon.repository.bean.checklist.CheckListClientHistoryBean;
import com.salon.repository.bean.client.ClientBean;
import com.salon.repository.bean.quickorder.QuickOrderBean;
import com.salon.repository.bean.worker.WorkerBean;
import com.salon.repository.dao.checklist.CheckListDAO;
import com.salon.repository.entity.catalog.Catalog;
import com.salon.repository.entity.checklist.CheckList;
import com.salon.service.checklist.CheckListComparatorByDate;
import com.salon.service.checklist.CheckListService;
import com.salon.service.client.ClientService;
import com.salon.service.exception.ErrorInfoExeption;
import com.salon.service.worker.WorkerService;
import com.salon.utility.EnumStatusCheckList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;

@Service
public class CheckListServiceImpl implements CheckListService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckListServiceImpl.class);

    @Autowired
    private CheckListDAO checkListDAO;

    @Autowired
    private WorkerService workerService;
    
    @Autowired
    private ClientService clientService;

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
    public List<CheckListBean> getCheckListByStatusAndDayAndMountsAndYear(Long workerId,
                                                                          EnumStatusCheckList status,
                                                                          Integer day, Integer mounts,
                                                                          Integer year) {

        List<CheckListBean> listBeans = new ArrayList<>();

        if (day == null && mounts == null && year == null) {
            WorkerBean workerBean = workerService.findById(workerId);
            if (workerBean == null) {
                LOGGER.debug("Worker NOT_FOUND");
                throw new ErrorInfoExeption("Worker NOT_FOUND",
                        "WORKER.NOT_FOUND");
            }
            List<CheckList> beans = workerBean.getCheckLists();

            for (CheckList checkList : beans) {
                if (checkList.getStatus().equals(status)) {
                    listBeans.add(toBean(checkList));
                    System.out.println(checkList.getDateAppointment());
                }
            }
        } else {
           //UNIX TIME
        }
        return listBeans;
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
        checkListBean.setDateCreate(new Timestamp(System.currentTimeMillis()));
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
    public List<CheckListClientHistoryBean> getClientHistory(Long clientId, Integer year, Integer month, String status) {
    	List<CheckListClientHistoryBean> clientHistoryBeans = new ArrayList<CheckListClientHistoryBean>();
    	    
    	    LocalDate start = LocalDate.of(year, month, 1);
    	    int days = start.lengthOfMonth();
    	    LocalDate end = LocalDate.of(year, month, days);
    	    Timestamp timestampStart = Timestamp.valueOf(start.atStartOfDay());
    	    Timestamp timestampEnd = Timestamp.valueOf(end.atStartOfDay().plusHours(23).plusMinutes(59));
        
 
			ClientBean clientBean = clientService.findById(clientId);
			 if (clientBean == null) {
	                LOGGER.debug("Client NOT_FOUND");
	                throw new ErrorInfoExeption("Client NOT_FOUND",
	                        "CLIENT.NOT_FOUND");
	            }
			List<CheckList> checkListBeans = clientBean.getCheckList();
			checkListBeans.sort(new CheckListComparatorByDate());
			
			for (CheckList checkList : checkListBeans) {
				if (checkList.getDateAppointment().after(timestampEnd)) {
					break;
				}
				if (checkList.getDateAppointment().after(timestampStart)) {
					Double price = 0d;
					CheckListClientHistoryBean historyBean = new CheckListClientHistoryBean();
					historyBean.setClientId(clientId);
					historyBean.setDateAppointment(checkList.getDateAppointment());
					historyBean.setSalon(checkList.getWorker().getSalon().getName());
					historyBean.setWorker(checkList.getWorker().getDescription());
					historyBean.setCatalogs(checkList.getCatalogs());
					for (Catalog catalog : checkList.getCatalogs()) {
						price += catalog.getPrice();
					}
					historyBean.setPrice(price);
					historyBean.setStatus(checkList.getStatus());
				}
				
					
			}
		
    	
    	return clientHistoryBeans;
    }


    @Override
    public CheckListBean toBean(CheckList domain) {
        CheckListBean checkListBean = new CheckListBean();
        checkListBean.setSheckListId(domain.getSheckListId());
        checkListBean.setCatalog(domain.getCatalogs());
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
        checkList.setCatalogs(bean.getCatalog());
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
