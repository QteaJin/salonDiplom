package com.salon.service.worktime.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.salon.repository.bean.worktime.WorkTimeBean;
import com.salon.repository.dao.worktime.WorkTimeDAO;
import com.salon.repository.entity.worktime.WorkTime;
import com.salon.service.worktime.WorkTimeService;
import com.salon.utility.EnumStatus;

@Service
public class WorkTimeServiceImpl implements WorkTimeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkTimeServiceImpl.class);
	@Autowired
	private WorkTimeDAO workTimeDAO;

	public void setWorkTimeDAO(WorkTimeDAO workTimeDAO) {
		this.workTimeDAO = workTimeDAO;
	}

	@Override
	public WorkTimeBean save(WorkTimeBean bean) {
		LOGGER.debug("WorkTime save start");

		WorkTime workTime = workTimeDAO.save(toDomain(bean));

		LOGGER.debug("WorkTime save finish");

		return toBean(workTime);
	}

	@Override
	public List<WorkTimeBean> findAll() {
		LOGGER.debug("find all WorkTime start");

		List<WorkTime> workTimes = workTimeDAO.findAll();

		LOGGER.debug("find all WorkTime finish");

		return toBean(workTimes);
	}

	@Override
	public WorkTimeBean findById(Long id) {
		LOGGER.debug("find by id WorkTime start");

		Optional<WorkTime> worOptional = workTimeDAO.findById(id);

		LOGGER.debug("find by id WorkTime finish");

		return toBean(worOptional.get());
	}

	@Override
	public WorkTimeBean update(WorkTimeBean bean) {
		LOGGER.debug("update WorkTime start");

		WorkTime workTime = workTimeDAO.saveAndFlush(toDomain(bean));

		LOGGER.debug("update WorkTime finish");

		return toBean(workTime);
	}

	@Override
	public void delete(WorkTimeBean bean) {
		workTimeDAO.delete(toDomain(bean));

	}

	@Override
	public List<WorkTimeBean> findByExample(WorkTimeBean bean) {
		LOGGER.debug("find by example WorkTime start");

		List<WorkTime> workTimes = workTimeDAO.findAll(Example.of(toDomain(bean)));

		LOGGER.debug("find by example WorkTime finish");
		return toBean(workTimes);
	}

	@Override
	public WorkTimeBean toBean(WorkTime domain) {
		WorkTimeBean workTimeBean = new WorkTimeBean();
		workTimeBean.setId(domain.getId());
		workTimeBean.setDate(domain.getDate());
		workTimeBean.setStartWorking(domain.getStartWorking());
		workTimeBean.setFinishWorking(domain.getFinishWorking());
		workTimeBean.setStatus(domain.getStatus());

		return workTimeBean;
	}

	@Override
	public WorkTime toDomain(WorkTimeBean bean) {
		WorkTime workTime = new WorkTime();
		workTime.setId(bean.getId());
		workTime.setDate(bean.getDate());
		workTime.setStartWorking(bean.getStartWorking());
		workTime.setFinishWorking(bean.getFinishWorking());
		if(Objects.isNull(bean.getStatus())){
			workTime.setStatus(EnumStatus.NOACTIVE);
        }else {
        	workTime.setStatus(bean.getStatus());
        }

		return workTime;
	}

	private List<WorkTimeBean> toBean(List<WorkTime> workTimes) {
		List<WorkTimeBean> workTimeBeans = new ArrayList<>();
		for (WorkTime workTime : workTimes) {
			workTimeBeans.add(toBean(workTime));
		}
		return workTimeBeans;
	}

}
