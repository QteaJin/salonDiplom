package com.salon.controller.worker;

import com.salon.repository.bean.client.WorkerCustumBean;
import com.salon.repository.bean.worker.WorkerBean;
import com.salon.repository.bean.worker.WorkerCreateUpdateBean;
import com.salon.repository.bean.worker.WorkerProfileSkillsBean;
import com.salon.repository.bean.worker.WorkerUpdateSkillBean;
import com.salon.repository.bean.worker.WorkingDays;
import com.salon.repository.entity.worktime.WorkTime;
import com.salon.service.worker.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    private WorkerService workerService;

    @Autowired
    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WorkerBean findById(@PathVariable("id") long id) {return workerService.findById(id); }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<WorkerBean> findAllWorker() { return workerService.findAll(); }

    @RequestMapping(method = RequestMethod.POST)
    public WorkerBean createWorker(@RequestBody WorkerBean workerBean) { return workerService.save(workerBean); }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") long id) {
        workerService.delete(workerService.findById(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public WorkerBean updateWorker(@RequestBody WorkerBean workerBean) {
        return workerService.update(workerBean);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<WorkerCustumBean> getWorkerByCityAndSalon(@RequestParam("salonId") long salonId) {
        return workerService.getWorkersBySalon(salonId);
    }
    
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public WorkerProfileSkillsBean getWorkerProfileSkillsById(@PathVariable("id") long workerId) {
        return workerService.getWorkerProfileSkillsById(workerId);
    }
    
    @RequestMapping(value = "/All/{city}", method = RequestMethod.GET)
    public List<WorkerBean> getWorkersByCity(@PathVariable("city") String city) {
        return workerService.getWorkersByCity(city);
    }
    
    @RequestMapping(value = "/admin/{id}" , method = RequestMethod.GET)
    public WorkerCreateUpdateBean getWorkerById(@PathVariable("id") long workerId) {
        return workerService.getWorkerById(workerId);
    }
    @RequestMapping(value = "/admin/update" , method = RequestMethod.POST)
    public WorkerCreateUpdateBean updateWorker(@RequestBody WorkerCreateUpdateBean workerBean) {
    	return workerService.updateWorkerData(workerBean); }
    
    @RequestMapping(value = "/admin/skill" , method = RequestMethod.POST)
    public boolean updateSkillWorker(@RequestBody WorkerUpdateSkillBean workerBean) {
    	return workerService.updateSkillWorker(workerBean); }
    
    @RequestMapping(value = "/admin/addDays" , method = RequestMethod.POST)
    public List<WorkTime> addWorkingDays(@RequestBody WorkingDays days) {
    	return workerService.addWorkingDays(days); }
}
