package com.salon.controller.worktime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salon.repository.bean.worktime.WorkTimeBean;
import com.salon.service.worktime.WorkTimeService;

@RestController
@RequestMapping("/worktime")
public class WorkTimeController {

	private WorkTimeService workTimeService;

	@Autowired
	public void setWorkTimeService(WorkTimeService workTimeService) {
		this.workTimeService = workTimeService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WorkTimeBean findById(@PathVariable("id") long id) {
		return workTimeService.findById(id);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<WorkTimeBean> findAllWorkTime() {
		return workTimeService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public WorkTimeBean createWorkTime(@RequestBody WorkTimeBean workTimeBean) {
		return workTimeService.save(workTimeBean);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable("id") long id) {
		workTimeService.delete(workTimeService.findById(id));
	}

	@RequestMapping(method = RequestMethod.PUT)
	public WorkTimeBean updateWorkTime(@RequestBody WorkTimeBean workTimeBean) {
		return workTimeService.update(workTimeBean);
	}
}
