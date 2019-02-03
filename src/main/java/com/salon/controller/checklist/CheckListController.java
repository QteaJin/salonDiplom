package com.salon.controller.checklist;

import com.salon.repository.bean.checklist.CheckListBean;
import com.salon.repository.bean.quickorder.QuickOrderBean;
import com.salon.service.checklist.CheckListService;
import com.salon.utility.EnumStatusCheckList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checklist")
public class CheckListController {

    @Autowired
    private CheckListService checkListService;

    public void setCheckListService(CheckListService checkListService) {
        this.checkListService = checkListService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CheckListBean findById(@PathVariable("id") long id) {
        return checkListService.findById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<CheckListBean> findAllCheckList() {
        return checkListService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public CheckListBean createCheckList(@RequestBody CheckListBean checkListBean) {
        return checkListService.save(checkListBean);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") long id) {
        checkListService.delete(checkListService.findById(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CheckListBean updateCheckList(@RequestBody CheckListBean checkListBean) {
        return checkListService.update(checkListBean);
    }

    @RequestMapping(value = "/quick", method = RequestMethod.POST)
    public QuickOrderBean createQuickOrder(@RequestBody QuickOrderBean quickOrderBean) {
        return checkListService.createQuickOrde(quickOrderBean);
    }

    @RequestMapping(value = "/worker/{workerId}/status/{status}", method = RequestMethod.GET)
    public List<CheckListBean> getCheckListByStatusAndDayAndMountsAndYear(@PathVariable("workerId") long workerId,
                                                                          @PathVariable("status") EnumStatusCheckList status,
                                                                          @RequestParam(value = "day", required = false) Integer day,
                                                                          @RequestParam(value = "mounts", required = false) Integer mounts,
                                                                          @RequestParam(value = "year", required = false) Integer year) {

        return checkListService.getCheckListByStatusAndDayAndMountsAndYear(workerId, status, day, mounts, year);
    }

}
