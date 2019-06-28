package com.salon.service.worker.impl;

import com.salon.repository.bean.adress.AdressBean;
import com.salon.repository.bean.client.WorkerCustumBean;
import com.salon.repository.bean.profile.ProfileBean;
import com.salon.repository.bean.salon.SalonBean;
import com.salon.repository.bean.skills.SkillsBean;
import com.salon.repository.bean.skills.SkillsBeanSimple;
import com.salon.repository.bean.worker.WorkerBean;
import com.salon.repository.bean.worker.WorkerCreateUpdateBean;
import com.salon.repository.bean.worker.WorkerProfileSkillsBean;
import com.salon.repository.bean.worker.WorkerUpdateSkillBean;
import com.salon.repository.bean.worker.WorkingDays;
import com.salon.repository.dao.worker.WorkerDAO;
import com.salon.repository.entity.skills.Skills;
import com.salon.repository.entity.worker.Worker;
import com.salon.repository.entity.worktime.WorkTime;
import com.salon.service.adress.AdressService;
import com.salon.service.auth.impl.AuthServiceImpl;
import com.salon.service.comparator.WorkTimeComparator;
import com.salon.service.salon.SalonService;
import com.salon.service.skills.SkillsService;
import com.salon.service.worker.WorkerService;
import com.salon.utility.EnumRole;
import com.salon.utility.EnumStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkerServiceImpl.class);

	@Autowired
	private WorkerDAO workerDAO;

	@Autowired
	private SalonService salonService;

	@Autowired
	private AdressService addressService;

	@Autowired
	private AuthServiceImpl authService;

	@Autowired
	private SkillsService skillsService;

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
	public List<WorkerCustumBean> getWorkersBySalon(Long salonId) {
		List<WorkerCustumBean> custumBeans = new ArrayList<WorkerCustumBean>();
		SalonBean salonBean = new SalonBean();
		salonBean.setSalonId(salonId);
		WorkerBean workerBean = new WorkerBean();
		workerBean.setSalon(salonService.toDomain(salonBean));
		List<WorkerBean> workerBeans = findByExample(workerBean);
		if (!workerBeans.isEmpty()) {
			for (WorkerBean workerBeanTemp : workerBeans) {
				WorkerCustumBean workerCustumBean = new WorkerCustumBean();
				workerCustumBean.setId(workerBeanTemp.getId());
				workerCustumBean.setName(workerBeanTemp.getProfile().getName());
				workerCustumBean.setDescripton(workerBeanTemp.getDescription());
				workerCustumBean.setNameImage(workerBeanTemp.getId() + "worker.jpg");
				custumBeans.add(workerCustumBean);
			}
		}

		return custumBeans;
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
			worker.setRole(EnumRole.WORKER);
		} else {
			worker.setRole(domain.getRole());
		}

		if (Objects.isNull(domain.getStatus())) {
			worker.setStatus(EnumStatus.NOACTIVE);
		} else {
			worker.setStatus(domain.getStatus());
		}

		worker.setProfile(domain.getProfile());
		worker.setSalon(domain.getSalon());
		worker.setCheckLists(domain.getCheckLists());
		worker.setSkillsList(domain.getSkillsList());
		worker.setDescription(domain.getDescription());
		worker.setSchedule(domain.getSchedule());

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
		workerBean.setDescription(bean.getDescription());
		workerBean.setSchedule(bean.getSchedule());
		return workerBean;
	}

	private List<WorkerBean> toDomain(List<Worker> workers) {
		List<WorkerBean> list = new ArrayList<>();
		for (Worker worker : workers) {
			list.add(toBean(worker));
		}
		return list;
	}

	@Override
	public WorkerProfileSkillsBean getWorkerProfileSkillsById(Long workerId) {
		Optional<Worker> worker = workerDAO.findById(workerId);
		WorkerBean workerBean = toBean(worker.get());
		WorkerProfileSkillsBean profileSkillsBean = new WorkerProfileSkillsBean();
		profileSkillsBean.setWorkerProfileId(workerId);
		profileSkillsBean.setDescription(workerBean.getProfile().getDescription());
		profileSkillsBean.setName(workerBean.getProfile().getName());
		profileSkillsBean.setWorkerId(workerBean.getId());
		if (!workerBean.getSkillsList().isEmpty()) {
			profileSkillsBean.setListSkills(workerBean.getSkillsList());
		}

		return profileSkillsBean;
	}

	@Override
	public List<WorkerBean> getWorkersByCity(String city) {
		List<WorkerBean> workerBeans = new ArrayList<WorkerBean>();
		AdressBean adressBean = new AdressBean();
		adressBean.setCity(city);
		SalonBean salonBean = new SalonBean();
		salonBean.setAddress(addressService.toDomain(adressBean));
		List<SalonBean> salonBeans = salonService.findByExample(salonBean);
		if (!salonBeans.isEmpty()) {
			List<Worker> workers = new ArrayList<Worker>();

			for (SalonBean bean : salonBeans) {
				workers.addAll(bean.getWorkerList());
			}
			for (Worker worker : workers) {
				workerBeans.add(toBean(worker));
			}

			return workerBeans;
		}

		return workerBeans;
	}

	@Override
	public WorkerCreateUpdateBean getWorkerById(long workerId) {

		WorkerCreateUpdateBean updateBean = new WorkerCreateUpdateBean();
		Optional<Worker> worker = workerDAO.findById(workerId);
		if (!worker.isPresent()) {
			return updateBean;
		}
		WorkerBean workerBean = toBean(worker.get());
		updateBean.setWorkerId(workerBean.getId());
		updateBean.setDescription(workerBean.getProfile().getDescription());
		updateBean.setEmail(workerBean.getProfile().getEmail());
		updateBean.setLogin(workerBean.getProfile().getLogin());
		updateBean.setName(workerBean.getProfile().getName());
		updateBean.setPassword(workerBean.getProfile().getPassword());
		updateBean.setPhone(workerBean.getProfile().getPhone());
		updateBean.setShortDescription(workerBean.getDescription());

		List<Skills> workerSkills = workerBean.getSkillsList();
		List<SkillsBeanSimple> beanSimples = new ArrayList<SkillsBeanSimple>();
		for (Skills skills : workerSkills) {
			SkillsBeanSimple beanSimple = new SkillsBeanSimple();
			beanSimple.setSkillsId(skills.getSkillsId());
			beanSimple.setName(skills.getName());
			beanSimples.add(beanSimple);
		}
		updateBean.setUsedSkills(beanSimples);

		List<SkillsBeanSimple> unusedBeanSimples = new ArrayList<SkillsBeanSimple>();

		List<SkillsBeanSimple> allBeanSimples = skillsService.getAllSkills();
		for (SkillsBeanSimple allBeanSimple : allBeanSimples) {
			boolean flag = true;
			for (SkillsBeanSimple usedBeanSimple : beanSimples) {
				if (allBeanSimple.getName().equals(usedBeanSimple.getName())) {
					flag = false;
				}
			}
			if (flag) {
				unusedBeanSimples.add(allBeanSimple);
			}

		}
		updateBean.setNotUsedSkills(unusedBeanSimples);

		return updateBean;
	}

	@Override
	public WorkerCreateUpdateBean updateWorkerData(WorkerCreateUpdateBean updateWorkerBean) {
		ProfileBean profileBean = new ProfileBean();
		profileBean.setEmail(updateWorkerBean.getEmail());
		profileBean.setLogin(updateWorkerBean.getLogin());
		boolean existProfile = authService.checkLogin(profileBean);
		if (existProfile) {
			return new WorkerCreateUpdateBean();
		}

		Optional<Worker> worker = workerDAO.findById(updateWorkerBean.getWorkerId());
		if (!worker.isPresent()) {
			return updateWorkerBean;
		}
		WorkerBean workerBean = toBean(worker.get());
		workerBean.getProfile().setDescription(updateWorkerBean.getDescription());
		workerBean.getProfile().setEmail(updateWorkerBean.getEmail());
		workerBean.getProfile().setLogin(updateWorkerBean.getLogin());
		workerBean.getProfile().setName(updateWorkerBean.getName());
		workerBean.getProfile().setPassword(updateWorkerBean.getPassword());
		workerBean.getProfile().setPhone(updateWorkerBean.getPhone());
		workerBean.setDescription(updateWorkerBean.getShortDescription());
		update(workerBean);
		return updateWorkerBean;
	}

	@Override
	public boolean updateSkillWorker(WorkerUpdateSkillBean workerBean) {
		Optional<Worker> worker = workerDAO.findById(workerBean.getWorkerId());
		WorkerBean workerBeanTemp = toBean(worker.get());
		SkillsBean skill = skillsService.findById(workerBean.getSkillsId());
		if (!worker.isPresent()) {
			LOGGER.debug("Wrong worker ID");
			return false;
		}
		List<Skills> skillsList = workerBeanTemp.getSkillsList();
		if (workerBean.getChange().equals("add")) {

			skillsList.add(skillsService.toDomain(skill));
		} else {

			int index = 0;
			for (Skills skills : skillsList) {
				if (skills.getSkillsId().equals(workerBean.getSkillsId())) {
					break;
				}
				index++;
			}
			skillsList.remove(index);
		}
		workerBeanTemp.setSkillsList(skillsList);
		update(workerBeanTemp);

		return true;
	}

	@Override
	public List<WorkTime> addWorkingDays(WorkingDays days) {
		if(days.getWorkerId() == null || days.getDatesList().isEmpty()) {
			new Exception("Income data is empty"); 
		}
		Optional<Worker> worker = workerDAO.findById(days.getWorkerId());
		WorkerBean workerBean = toBean(worker.get());
		List<WorkTime> workTimes = workerBean.getSchedule();
		List<Long> times = days.getDatesList();
		
		for (Long day : times) {
			WorkTime wt = new WorkTime();
			Timestamp timestamp = new Timestamp(day);
			
			wt.setDate(timestamp);
			workTimes.add(wt);
		}

		workerBean.setSchedule(workTimes);
		update(workerBean);

		return workTimes;
	}

	@Override
	public WorkingDays getWorkingDays(Long workerId) {
		WorkingDays workingDays = new WorkingDays();
		workingDays.setWorkerId(workerId);
		LocalDate date = LocalDate.now();
		
		Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
		Optional<Worker> worker = workerDAO.findById(workerId);
		WorkerBean workerBean = toBean(worker.get());
		List<WorkTime> workTimes = workerBean.getSchedule();
		List<Long> timeList = new ArrayList<>();
		for (WorkTime workTime : workTimes) {
			if(workTime.getDate().after(timestamp)) {
				timeList.add(workTime.getDate().getTime());
			}
		}
		Collections.sort(timeList);
		workingDays.setDatesList(timeList);
		
		return workingDays;
	}

	@Override
	public List<WorkTime> delWorkingDays(WorkingDays days) {
		if(days.getWorkerId() == null || days.getDatesList().isEmpty()) {
			new Exception("Income data is empty"); 
		}
		Optional<Worker> worker = workerDAO.findById(days.getWorkerId());
		WorkerBean workerBean = toBean(worker.get());
		List<WorkTime> workTimes = workerBean.getSchedule();
		List<Long> times = days.getDatesList();
		WorkTimeComparator comparator = new WorkTimeComparator();
		Collections.sort(workTimes, comparator);
		Collections.sort(times);
		int timeSize = times.size();
		int count = 0;
		Iterator <WorkTime> iterator = workTimes.iterator();
//		Timestamp ts = new Timestamp(times.get(0));
		while (iterator.hasNext()) {
			WorkTime workTime = (WorkTime) iterator.next();
//			if(ts.before(workTime.getDate()) ) {
//				continue;
//			}
			Timestamp tsTemp = new Timestamp(times.get(count));
			if(tsTemp.equals(workTime.getDate())) {
				iterator.remove();
				count++;
				if(count == timeSize) {
					break;
				}
			}
		}
		workerBean.setSchedule(workTimes);
		update(workerBean);
		return workTimes;
	}

}
