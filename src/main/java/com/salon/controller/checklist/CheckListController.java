package com.salon.controller.checklist;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.repository.bean.checklist.CheckListBean;
import com.salon.repository.bean.checklist.CheckListClientHistoryBean;
import com.salon.repository.bean.checklist.CheckListNewOrderBean;
import com.salon.repository.bean.checklist.OrderBean;
import com.salon.repository.bean.quickorder.QuickOrderBean;
import com.salon.service.checklist.CheckListService;
import com.salon.service.crypto.TokenCryptImpl;
import com.salon.utility.EnumRole;
import com.salon.utility.EnumStatusCheckList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/checklist")
public class CheckListController {
	
	@Autowired
	private TokenCryptImpl tokenCrypt;
	
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
    
    @RequestMapping(value = "/client/history", method = RequestMethod.GET)
    public List<CheckListClientHistoryBean> getClientHistory(HttpServletRequest req,
    														@RequestParam(value = "year") Integer year,
    														@RequestParam(value = "month") Integer month,
    														@RequestParam(value = "status", required = false) String status) {
    	

    	
	return checkListService.getClientHistory(req, year, month, status);
    	
    }
    
    @RequestMapping(value = "/cancel/{orderId}", method = RequestMethod.GET)
    public String cancelOrderByClient(@PathVariable("orderId") Long orderId) {
    	
    	CheckListBean checkListBean = checkListService.findById(orderId);
    	checkListBean.setStatus(EnumStatusCheckList.CANCELED);
    	//checkListService.update(checkListBean);
    	checkListService.delete(checkListBean);
    	return orderId+"";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Map<Timestamp, List<Timestamp>> freeDatesForOrder(@RequestBody CheckListNewOrderBean checkListNewOrderBean){
		
    	return checkListService.getFreeDatesForOrder(checkListNewOrderBean);
    	
    }
    
    @RequestMapping(value = "/new/create", method = RequestMethod.POST)
    public String createNewOrder (@CookieValue("token") String token, @RequestBody CheckListNewOrderBean checkListNewOrderBean) {
    	AuthBean authBean = tokenCrypt.checkToken(token);
    	if(authBean.getErrorMessage() != null) {
    		return "token error";
    	}
    	
    	if(authBean.getEnumRole().equals(EnumRole.CLIENT)) {
    		checkListNewOrderBean.setClientId(authBean.getUserId());
    	}
    	
    	return checkListService.createNewOrder(checkListNewOrderBean);
    	
    }
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public Map <String, List <CheckListClientHistoryBean>> getAllOrdersByWorkers (@CookieValue("token") String token,
    																			@RequestBody OrderBean orderBean){
    	AuthBean authBean = tokenCrypt.checkToken(token);
    	   	if(authBean.getErrorMessage() != null) {
    		return null;
    	}
    	   	if(!authBean.getEnumRole().equals(EnumRole.ADMIN)) {
    		return null;
    	}
    	
				
    	return checkListService.getAllOrdersByDate(orderBean);
    	
    };
    
    @RequestMapping(value = "/admin/{orderId}/status/{status}", method = RequestMethod.GET)
    public boolean setStatusOrder(@PathVariable("orderId") long orderId,
                                              @PathVariable("status") EnumStatusCheckList status,
                                              @CookieValue("token") String token){
    	AuthBean authBean = tokenCrypt.checkToken(token);
	   	if(authBean.getErrorMessage() != null) {
		return false;
	}
	   	if(!authBean.getEnumRole().equals(EnumRole.ADMIN)) {
		return false;
	}
	
    	return checkListService.setStatusOrder(orderId,status);
    
    }

}
