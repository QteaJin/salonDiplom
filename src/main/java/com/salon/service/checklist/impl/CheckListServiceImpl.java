package com.salon.service.checklist.impl;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.repository.bean.catalog.CatalogBean;
import com.salon.repository.bean.checklist.CheckListBean;
import com.salon.repository.bean.checklist.CheckListClientHistoryBean;
import com.salon.repository.bean.checklist.CheckListNewOrderBean;
import com.salon.repository.bean.checklist.OrderBean;
import com.salon.repository.bean.client.ClientBean;
import com.salon.repository.bean.email.Email;
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
import com.salon.service.sendemail.SendEmail;
import com.salon.service.worker.WorkerService;
import com.salon.utility.Const;
import com.salon.utility.EnumRole;
import com.salon.utility.EnumStatus;
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
	private SendEmail sendEmail;

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
	public List<CheckListBean> getCheckListByStatusAndDayAndMountsAndYear(Long workerId, EnumStatusCheckList status,
			Integer day, Integer mounts, Integer year) {

		List<CheckListBean> listBeans = new ArrayList<>();

		if (day == null && mounts == null && year == null) {
			WorkerBean workerBean = workerService.findById(workerId);
			if (workerBean == null) {
				LOGGER.debug("Worker NOT_FOUND");
				throw new ErrorInfoExeption("Worker NOT_FOUND", "WORKER.NOT_FOUND");
			}
			List<CheckList> beans = workerBean.getCheckLists();

			for (CheckList checkList : beans) {
				if (checkList.getStatus().equals(status)) {
					listBeans.add(toBean(checkList));
					System.out.println(checkList.getDateAppointment());
				}
			}
		} else {
			// UNIX TIME
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
			return new QuickOrderBean();
		}
		Email email = new Email();
		String subject = "Быстрая запись к мастеру - ";
		email.setFrom(Const.MyEmail.MY_EMAIL); // sending email to myself
		email.setTo(Const.MyEmail.MY_EMAIL);
		email.setHtml(false);
		email.setSubject(subject);
		StringBuilder builder = new StringBuilder();
		builder.append(orderBean.getName()).append('\n');
		if(!orderBean.getEmail().isEmpty()) {
			builder.append(orderBean.getEmail()).append('\n');
		}
		builder.append(orderBean.getPhone()).append('\n').append('\n');
		builder.append("Сообщение : ").append(orderBean.getDescription());
		
		email.setMessage(builder.toString());
		
		sendEmail.sendEmailToUser(email);
		
//        CheckListBean checkListBean = new CheckListBean();
//        checkListBean.setDateCreate(new Timestamp(System.currentTimeMillis()));
//        checkListBean.setStatus(EnumStatusCheckList.NEW);
//        checkListBean.setDescription("Name: " + orderBean.getName()
//                + " Phone: " + orderBean.getPhone()
//                + "Email: " + orderBean.getEmail()
//                + "Description: " + orderBean.getDescription());
//
//        CheckListBean listBean = save(checkListBean);
//        if (listBean != null && listBean.getSheckListId() != null) {
//            return orderBean;
//        }

		return orderBean;
	}

	@Override
	public List<CheckListBean> findByExample(CheckListBean bean) {
		LOGGER.debug("find by example CheckList start");

		List<CheckList> workers = checkListDAO.findAll(Example.of(toDomain(bean)));

		LOGGER.debug("find by example CheckList finish");

		return toBean(workers);
	}

	@Override
	public List<CheckListClientHistoryBean> getClientHistory(HttpServletRequest req, Integer year, Integer month,
			String status) {

		String token = tokenCrypt.getCookie(req);

		try {
			if (tokenCrypt.checkToken(token).getErrorMessage() != null) {
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

		// RecieveUserInfo userInfo = new RecieveUserInfo(); //Long clientId,
		Long clientId = bean.getUserId();

		List<CheckListClientHistoryBean> clientHistoryBeans = new ArrayList<CheckListClientHistoryBean>();
		Set<String> checkBean = new HashSet<String>();

		LocalDate start = LocalDate.of(year, month, 1);
		int days = start.lengthOfMonth();
		LocalDate end = LocalDate.of(year, month, days);
		Timestamp timestampStart = Timestamp.valueOf(start.atStartOfDay());
		Timestamp timestampEnd = Timestamp.valueOf(end.atStartOfDay().plusHours(23).plusMinutes(59));

		ClientBean clientBean = clientService.findById(clientId);
		if (clientBean == null) {
			LOGGER.debug("Client NOT_FOUND");
			throw new ErrorInfoExeption("Client NOT_FOUND", "CLIENT.NOT_FOUND");
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
				if (!checkList.getStatus().toString().equals(status.toUpperCase())
						&& !status.toUpperCase().equals("ALL")) {
					continue;
				}

				Double price = 0d;
				CheckListClientHistoryBean historyBean = new CheckListClientHistoryBean();
				historyBean.setClientId(clientId);
				historyBean.setCheckListId(checkList.getSheckListId());
				historyBean.setDateAppointment(checkList.getDateAppointment());
				historyBean.setSalon(checkList.getWorker().getSalon().getName());
				historyBean.setWorker(checkList.getWorker().getProfile().getName()); // + " - " +
																						// checkList.getWorker().getDescription()
				if (!checkList.getCatalogs().isEmpty()) {
					//historyBean.setCatalogs(checkList.getCatalogs());
					List<Catalog> catalogs = checkList.getCatalogs();
					List<Catalog> tempList = new ArrayList<>();
					for (Catalog temp : catalogs) {
						if(!checkBean.contains(temp.getName())) {
							checkBean.add(temp.getName());
							tempList.add(temp);
							price += temp.getPrice();
						}
					}
					historyBean.setCatalogs(tempList);
//					for (Catalog catalog : tempList) {
//						price += catalog.getPrice();
//					}
				}

				historyBean.setPrice(price);
				historyBean.setStatus(checkList.getStatus());
				//проверка
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
		List<CatalogBean> catalogs = new ArrayList<CatalogBean>(); // all catalogs that choose
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
			if (workTime.getDate().before(today)) {
				continue;
			}
			freeDates.put(workTime.getDate(), getFreeTime(workerBean, workTime.getDate(), timelead));
		}
		// LocalDate ld = today.toLocalDateTime().toLocalDate();

		return freeDates;
	}

	private List<Timestamp> getFreeTime(WorkerBean bean, Timestamp key, Long timelead) {
		List<Timestamp> freeHoursForOrder = new ArrayList<Timestamp>();
		LocalDateTime ld = key.toLocalDateTime();
		Timestamp startWorking = Timestamp.valueOf(ld.plusHours(Const.SalonShedule.START_WORK));
		Timestamp finishWorking = Timestamp.valueOf(ld.plusHours(Const.SalonShedule.END_WORK));

		List<CheckList> orders = new ArrayList<CheckList>(); // orders in current day
		List<CheckList> checkLists = bean.getCheckLists();
		for (CheckList checkList : checkLists) {
			if (checkList.getDateAppointment().before(startWorking)
					|| checkList.getDateAppointment().after(finishWorking)) {
				continue;
			}
			orders.add(checkList);
		}

		LocalDateTime startWork = ld.plusHours(Const.SalonShedule.START_WORK);
		LocalDateTime finishWork = ld.plusHours(Const.SalonShedule.END_WORK);
		Map<Timestamp, String> reservedAndFreeTime = new TreeMap<>();

		for (int i = 0; i < 21; i++) {

			Timestamp ts = Timestamp.valueOf(startWork.plusMinutes(30 * i));
			reservedAndFreeTime.put(ts, "free");
		}
		Set<Timestamp> timeScale = reservedAndFreeTime.keySet();
		for (Timestamp time : timeScale) {

			for (CheckList order : orders) {
				if (order.getDateAppointment().equals(time)) {
					Timestamp startOrder = order.getDateAppointment();
					Timestamp finishOrder = order.getTimeFinish();
					reservedAndFreeTime.put(time, "reserved");
					Long workingTime = finishOrder.getTime() - startOrder.getTime();
					int iteration = (int) (workingTime / Const.Millis.MILLIS_IN_HALF_HOUR);
					for (int i = 1; i < iteration; i++) {
						long timeMillis = time.getTime() + Const.Millis.MILLIS_IN_HALF_HOUR * i;
						reservedAndFreeTime.put(new Timestamp(timeMillis), "reserved");
					}
				}
			}
		}
		Timestamp startOrder = null;

		Long count = 0L;
		for (Map.Entry<Timestamp, String> entry : reservedAndFreeTime.entrySet()) {
			// System.out.println(entry.getKey() + "/" + entry.getValue());
			if (entry.getValue().equals("reserved")) {
				startOrder = null;
				count = 0L;
				continue;
			}

			count += 30;
			if (count.equals(timelead)) {

				startOrder = new Timestamp(entry.getKey().getTime() - timelead * Const.Millis.MILLIS_IN_MINUTE
						+ Const.Millis.MILLIS_IN_HALF_HOUR);
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

	@Override
	public String createNewOrder(CheckListNewOrderBean checkListNewOrderBean) {
		WorkerBean workerBean = workerService.findById(checkListNewOrderBean.getWorkerId());
		ClientBean clientBean = clientService.findById(checkListNewOrderBean.getClientId());

		List<CatalogBean> catalogs = new ArrayList<CatalogBean>(); // all catalogs that choose
		List<Long> catalogsId = checkListNewOrderBean.getCatalogList();
		Long timelead = 0L; // summ time to do order
		Double price = 0.0;
		for (Long catalogId : catalogsId) {
			CatalogBean catalogBean = new CatalogBean();
			catalogBean = catalogService.findById(catalogId);
			catalogs.add(catalogBean);
			timelead += catalogBean.getTimeLead();
			price += catalogBean.getPrice();
		}

		Long timeFinishL = checkListNewOrderBean.getDateAppointment().getTime()
				+ timelead * Const.Millis.MILLIS_IN_MINUTE;

		if (!checkIfAviableTime(workerBean, checkListNewOrderBean.getDateAppointment(), timelead)) {
			return "Date was reserved";
		}

		CheckListBean checkListBean = new CheckListBean();
		checkListBean.setCatalog(catalogService.toDomain(catalogs));
		checkListBean.setClient(clientService.toDomain(clientBean));
		checkListBean.setDateAppointment(checkListNewOrderBean.getDateAppointment());
		checkListBean.setDateCreate(new Timestamp(System.currentTimeMillis()));
		checkListBean.setPrice(price);
		checkListBean.setStatus(EnumStatusCheckList.NEW);
		checkListBean.setTimeFinish(new Timestamp(timeFinishL));
		checkListBean.setWorker(workerService.toDomain(workerBean));

		save(checkListBean);

		CheckListBean cb = new CheckListBean();
		cb.setDateAppointment(checkListNewOrderBean.getDateAppointment());
		List<CheckListBean> beans = findByExample(cb);
		for (CheckListBean checkListBean2 : beans) {
			List<Catalog> catalogsTemp = checkListBean2.getCatalog();
			for (Catalog catalTemp : catalogsTemp) {
				catalTemp.getCheckLists().add(toDomain(checkListBean2));
				catalogService.update(catalogService.toBean(catalTemp));
			}
		}

		return "ok";
	}

	private boolean checkIfAviableTime(WorkerBean workerBean, Timestamp dateAppointment, Long timelead) {

		LocalDateTime ld = dateAppointment.toLocalDateTime();
		Timestamp startWorking = Timestamp.valueOf(ld.plusHours(Const.SalonShedule.START_WORK));
		Timestamp finishWorking = Timestamp.valueOf(ld.plusHours(Const.SalonShedule.END_WORK));

		List<CheckList> orders = new ArrayList<CheckList>(); // orders in current day
		List<CheckList> checkLists = workerBean.getCheckLists();
		for (CheckList checkList : checkLists) {
			if (checkList.getDateAppointment().before(startWorking)
					|| checkList.getDateAppointment().after(finishWorking)) {
				continue;
			}
			orders.add(checkList);
		}
		LocalDateTime startWork = ld.plusHours(Const.SalonShedule.START_WORK);
		Map<Timestamp, String> reservedAndFreeTime = new TreeMap<>();

		for (int i = 0; i < 21; i++) {

			Timestamp ts = Timestamp.valueOf(startWork.plusMinutes(30 * i));
			reservedAndFreeTime.put(ts, "free");
		}
		Set<Timestamp> timeScale = reservedAndFreeTime.keySet(); // start to create reserved timeline
		for (Timestamp time : timeScale) {

			for (CheckList order : orders) {
				if (order.getDateAppointment().equals(time)) {
					Timestamp startOrder = order.getDateAppointment();
					Timestamp finishOrder = order.getTimeFinish();
					reservedAndFreeTime.put(time, "reserved");
					Long workingTime = finishOrder.getTime() - startOrder.getTime();
					for (int i = 1; i < workingTime / Const.Millis.MILLIS_IN_HALF_HOUR; i++) {
						long timeMillis = time.getTime() + Const.Millis.MILLIS_IN_HALF_HOUR * i;
						reservedAndFreeTime.put(new Timestamp(timeMillis), "reserved");
					}
				}
			}
		} // finish to create reserved timeline

		// List <Timestamp> newOrderTimeLine = new ArrayList<Timestamp>();

		for (int i = 0; i < timelead % 30; i++) {
			Long tsTemp = dateAppointment.getTime() + Const.Millis.MILLIS_IN_HALF_HOUR * i;
			Timestamp orderTs = new Timestamp(tsTemp);
			// newOrderTimeLine.add(new Timestamp (tsTemp));
			for (Map.Entry<Timestamp, String> entry : reservedAndFreeTime.entrySet()) {
				if (orderTs.equals(entry.getKey()) & entry.getValue().equals("reserved")) {
					return false;
				}
			}

		}

		return true;

	}

	@Override
	public Map<String, List<CheckListClientHistoryBean>> getAllOrdersByDate(OrderBean orderBean) {
		Map<String, List<CheckListClientHistoryBean>> ordersMap = new HashMap<String, List<CheckListClientHistoryBean>>();
		List<WorkerBean> workerBeans = workerService.findAll();

		for (WorkerBean workerBean : workerBeans) {
			if (workerBean.getRole().equals(EnumRole.ADMIN)) {
				continue;
			}
			boolean isWorking = false;
			Timestamp correctingTs = new Timestamp(orderBean.getDate().getTime() + Const.Millis.MILLIS_IN_HOUR * 3); // difference
																														// 3
																														// hour
																														// between
			List<WorkTime> workTimes = workerBean.getSchedule();
			for (WorkTime workTime : workTimes) {
				if (workTime.getDate().equals(correctingTs)) {
					isWorking = true;
					break;
				}
			}
			if (isWorking) {
				ordersMap.put(workerBean.getProfile().getName(), createShedulerForWorker(workerBean, orderBean));
			}

		}
		return ordersMap;
	}

	private List<CheckListClientHistoryBean> createShedulerForWorker(WorkerBean workerBean, OrderBean orderBean) {
		List<CheckListClientHistoryBean> historyBeans = new ArrayList<>();
		List<CheckList> orders = workerBean.getCheckLists();
		List<CheckList> ordersOnDate = new ArrayList<>();
		Timestamp end = new Timestamp(orderBean.getDate().getTime() + Const.Millis.MILLIS_IN_HOUR * 23);
		for (CheckList order : orders) {
			if (order.getDateAppointment().before(orderBean.getDate()) || order.getDateAppointment().after(end)) {
				continue;
			}
			ordersOnDate.add(order); // active orders by date that interested
		}

		Collections.sort(ordersOnDate, new CheckListComparatorByDate());
		Timestamp startWorkingTime = new Timestamp(orderBean.getDate().getTime() + Const.Millis.MILLIS_IN_HOUR * 10);

		for (int i = 0; i < 10; i++) {
			Timestamp start = new Timestamp(startWorkingTime.getTime() + Const.Millis.MILLIS_IN_HOUR * i);
			Timestamp finish = new Timestamp(startWorkingTime.getTime() + Const.Millis.MILLIS_IN_HOUR * (i + 1));
			CheckListClientHistoryBean bean = new CheckListClientHistoryBean();
			bean.setDateAppointment(start);
			bean.setFinish(finish);
			bean.setStatus(EnumStatusCheckList.FREE);
			historyBeans.add(bean);
		}

		for (CheckList orderOnDate : ordersOnDate) {

			CheckListClientHistoryBean bean = new CheckListClientHistoryBean();
			bean.setCatalogs(orderOnDate.getCatalogs());
			bean.setCheckListId(orderOnDate.getSheckListId());
			bean.setClientId(orderOnDate.getClient().getId());
			bean.setDateAppointment(orderOnDate.getDateAppointment());
			bean.setFinish(orderOnDate.getTimeFinish());
			bean.setName(orderOnDate.getClient().getProfile().getName());
			bean.setPrice(orderOnDate.getPrice());
			bean.setStatus(orderOnDate.getStatus());
			bean.setWorker(orderOnDate.getWorker().getProfile().getName());
			bean.setClientContacts(orderOnDate.getClient().getProfile().getPhone().concat("<br>").
										concat(orderOnDate.getClient().getProfile().getEmail()));
			

			int timelead = (int) ((orderOnDate.getTimeFinish().getTime() - orderOnDate.getDateAppointment().getTime())
					/ Const.Millis.MILLIS_IN_HOUR);
			for (int i = 0; i < historyBeans.size(); i++) {
				CheckListClientHistoryBean cHBean = historyBeans.get(i);
				if (cHBean.getDateAppointment().equals(orderOnDate.getDateAppointment())) {

					historyBeans.set(i, bean);
					for (int j = 1; j < timelead; j++) {

						historyBeans.remove(i + 1);

					}
				}
			}
		}

		return historyBeans;
	}

	@Override
	public boolean setStatusOrder(long orderId, EnumStatusCheckList status) {

		CheckListBean checkListBean = findById(orderId);
		checkListBean.setStatus(status);
		update(checkListBean);

		return true;
	}
}
