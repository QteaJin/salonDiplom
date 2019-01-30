package com.salon.controller.worker;

import com.salon.repository.bean.client.WorkerCustumBean;
import com.salon.repository.bean.worker.WorkerBean;
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

    @RequestMapping(value = "/{city}/{salonId}", method = RequestMethod.GET)
    public List<WorkerCustumBean> getClientByCityAndSalon(@PathVariable("city") String city,
                                                                     @PathVariable("salonId") Long salonId) {
        return workerService.getClientByCityAndSalon(city, salonId);
    }
}
