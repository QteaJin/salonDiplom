package com.salon.service.checklist.impl;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.repository.bean.catalog.CatalogBean;
import com.salon.repository.bean.checklist.CheckListBean;
import com.salon.repository.bean.checklist.CheckListClientHistoryBean;
import com.salon.repository.bean.checklist.CheckListNewOrderBean;
import com.salon.repository.bean.client.ClientBean;
import com.salon.repository.bean.quickorder.QuickOrderBean;
import com.salon.repository.bean.worker.WorkerBean;
import com.salon.repository.dao.checklist.CheckListDAO;
import com.salon.repository.entity.catalog.Catalog;
import com.salon.repository.entity.checklist.CheckList;
import com.salon.repository.entity.worktime.WorkTime;
import com.salon.service.RecieveUserInfo;
import com.salon.service.catalog.CatalogService;
import com.salon.service.checklist.CheckListComparatorByDate;
import com.salon.service.checklist.CheckListService;
import com.salon.service.client.ClientService;
import com.salon.service.crypto.TokenCryptImpl;
import com.salon.service.exception.ErrorInfoExeption;
import com.salon.service.worker.WorkerService;
import com.salon.utility.Const;
import com.salon.utility.EnumStatusCheckList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Service
public class CheckListServiceImpl implements CheckListService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckListServiceImpl.class);
    
    @Autowired
    private TokenCryptImpl tokenCrypt;
    
    @Autowired
    private CheckListDAO checkListDAO;

    @Autowired
    private WorkerService workerService;
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private CatalogService catalogService;

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
    public List<CheckListClientHistoryBean> getClientHistory(HttpServletRequest req, Integer year, Integer month, String status) {
    	
    	String token = tokenCrypt.getCookie(req);
    	
    	try {
			if(tokenCrypt.checkToken(token).getErrorMessage() != null) {
				List<CheckListClientHistoryBean> clientHistoryBeans = new ArrayList<CheckListClientHistoryBean>();
				CheckListClientHistoryBean historyBean = new CheckListClientHistoryBean();
				historyBean.setToken("error");
				clientHistoryBeans.add(historyBean);
				return clientHistoryBeans;
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	AuthBean bean = tokenCrypt.checkToken(token);
    	    	
    	//RecieveUserInfo userInfo = new RecieveUserInfo();   //Long clientId,
    	Long clientId = bean.getUserId();
    	
    	
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
			if (checkListBeans.isEmpty()) {
				LOGGER.debug("NO ORDERS of Client");
				return clientHistoryBeans;
			}
			checkListBeans.sort(new CheckListComparatorByDate());
			
			
			for (CheckList checkList : checkListBeans) {
				if (checkList.getDateAppointment().after(timestampEnd)) {
					break;
				}
				if (checkList.getDateAppointment().after(timestampStart)) {
					if(!checkList.getStatus().toString().equals(status.toUpperCase()) && !status.toUpperCase().equals("ALL") ) {continue;}						
					
					Double price = 0d;
					CheckListClientHistoryBean historyBean = new CheckListClientHistoryBean();
					historyBean.setClientId(clientId);
					historyBean.setCheckListId(checkList.getSheckListId());
					historyBean.setDateAppointment(checkList.getDateAppointment());
					historyBean.setSalon(checkList.getWorker().getSalon().getName());
					historyBean.setWorker(checkList.getWorker().getProfile().getName() + " - " + checkList.getWorker().getDescription() );
					if(!checkList.getCatalogs().isEmpty()) {
						historyBean.setCatalogs(checkList.getCatalogs());
						for (Catalog catalog : checkList.getCatalogs()) {
							price += catalog.getPrice();
						}
					}
					
					historyBean.setPrice(price);
					historyBean.setStatus(checkList.getStatus());
					clientHistoryBeans.add(historyBean);
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

	@Override
	public Map<Timestamp, List<Timestamp>> getFreeDatesForOrder(CheckListNewOrderBean checkListNewOrderBean) {
		Map<Timestamp, List<Timestamp>> freeDates = new TreeMap<>();
		List<Long> catalogsId = checkListNewOrderBean.getCatalogList();
		WorkerBean workerBean = workerService.findById(checkListNewOrderBean.getWorkerId());
		List <CatalogBean> catalogs = new ArrayList<CatalogBean>(); //all catalogs that choose
		List<Timestamp> freeHours = new ArrayList<Timestamp>();
		Long timelead = 0L; // summ time to do order
		for (Long catalogId : catalogsId) {
			CatalogBean catalogBean = new CatalogBean();
			catalogBean = catalogService.findById(catalogId);
			catalogs.add(catalogBean);
			timelead += catalogBean.getTimeLead();
		}
		List<WorkTime> shedule = workerBean.getSchedule(); // get sheduler of worker
		LocalDate now = LocalDate.now();
		Timestamp today = Timestamp.valueOf(now.atStartOfDay()); 
		for (WorkTime workTime : shedule) {
			if(workTime.getDate().before(today)) {
				continue;
			}
			freeDates.put(workTime.getDate(), getFreeTime(workerBean, workTime.getDate(), timelead));
		}
		//LocalDate ld = today.toLocalDateTime().toLocalDate();
		
		return freeDates;
	}
    
	private List<Timestamp> getFreeTime (WorkerBean bean, Timestamp key, Long timelead) {
		List<Timestamp> freeHoursForOrder = new ArrayList<Timestamp>();
		LocalDateTime ld = key.toLocalDateTime();
		Timestamp startWorking = Timestamp.valueOf(ld.plusHours(Const.SalonShedule.START_WORK));
		Timestamp finishWorking =Timestamp.valueOf(ld.plusHours(Const.SalonShedule.END_WORK)); 
		
		List<CheckList> orders = new ArrayList<CheckList>(); // orders in current day
		List <CheckList> checkLists = bean.getCheckLists();
		for (CheckList checkList : checkLists) {
			if (checkList.getDateAppointment().before(startWorking) || checkList.getDateAppointment().after(finishWorking) ) {
				continue;
			}
			orders.add(checkList);
		}
		
		LocalDateTime startWork = ld.plusHours(Const.SalonShedule.START_WORK);
		LocalDateTime finishWork = ld.plusHours(Const.SalonShedule.END_WORK);
		Map <Timestamp , String > reservedAndFreeTime = new TreeMap<>();
		
		for (int i = 0; i < 21; i++) {
		
			Timestamp ts = Timestamp.valueOf(startWork.plusMinutes(30*i));	
			reservedAndFreeTime.put(ts, "free");
		}
		Set<Timestamp> timeScale = reservedAndFreeTime.keySet();
		for (Timestamp time : timeScale) {
			
			for (CheckList order : orders) {
				if(order.getDateAppointment().equals(time)) {
					Timestamp startOrder = order.getDateAppointment();
					Timestamp finishOrder = order.getTimeFinish();
					reservedAndFreeTime.put(time, "reserved");
					Long workingTime = finishOrder.getTime() - startOrder.getTime();
					for (int i = 1; i < workingTime%Const.Millis.MILLIS_IN_HALF_HOUR; i++) {
						long timeMillis = time.getTime() + Const.Millis.MILLIS_IN_HALF_HOUR*i;
						reservedAndFreeTime.put(new Timestamp(timeMillis), "reserved");
					}
				}
			}
		}
		Timestamp startOrder = null;
		
		Long count = 0L;
		for (Map.Entry<Timestamp, String> entry : reservedAndFreeTime.entrySet()) {
		    //System.out.println(entry.getKey() + "/" + entry.getValue());
		    if(entry.getValue().equals("reserved")) {
		    	startOrder = null;
		    	count = 0L;
		    	continue;
		    }

		    count+=30;
		    if(count.equals(timelead)) {
		    	
		    	startOrder = new Timestamp(entry.getKey().getTime() - timelead*Const.Millis.MILLIS_IN_MINUTE + Const.Millis.MILLIS_IN_HALF_HOUR);
		    	freeHoursForOrder.add(startOrder);
		    	count = 0L;
		    }
		}
//		for (Timestamp timestamp : freeHoursForOrder) {
//			System.err.print(timestamp + ", ");
//			
//		}
//		System.out.println();
		return freeHoursForOrder;
		
	}

}
