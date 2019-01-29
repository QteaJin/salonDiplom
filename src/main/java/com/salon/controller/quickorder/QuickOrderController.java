package com.salon.controller.quickorder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salon.repository.bean.quickorder.QuickOrderBean;
import com.salon.service.quickorder.impl.QuickOrderServiceImpl;


@RestController
@RequestMapping("/quickOrder")
public class QuickOrderController {
	
	@Autowired
	QuickOrderServiceImpl quickOrderService;

	@RequestMapping(method = RequestMethod.POST)
	public QuickOrderBean createQuickOrder (@RequestBody QuickOrderBean orderBean) {
		
		quickOrderService.createNewOrder(orderBean);
		
		return orderBean;
		
	}
}
