package com.salon.controller.salon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salon.repository.bean.salon.SalonBean;
import com.salon.repository.bean.salon.SalonCreateEditBean;
import com.salon.service.salon.SalonService;

@RestController
@RequestMapping("/salon")
public class SalonController {
	@Autowired
	private SalonService salonService;

	
	public void setSalonService(SalonService salonService) {
		this.salonService = salonService;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SalonBean findById(@PathVariable("id") long id) {
		return salonService.findById(id);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<SalonBean> findAllSalon(){
		return salonService.findAll();
	}
	@RequestMapping(method = RequestMethod.POST)
	public SalonBean createSalon (@RequestBody SalonBean salonBean) {
		return salonService.save(salonBean);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable("id") long id) {
		salonService.delete(salonService.findById(id));
	}
	@RequestMapping(method = RequestMethod.PUT)
	public SalonBean updateSalon(@RequestBody SalonBean salonBean) {
		return salonService.update(salonBean);
	}
	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	public SalonCreateEditBean createNewSalon(@RequestBody SalonCreateEditBean createEditBean) {
		
		return salonService.createSalon(createEditBean);
		
	}
	@RequestMapping(value = "/admin/all", method = RequestMethod.GET)
	public List<SalonCreateEditBean> findAllSalons(){
		return salonService.findAllSalons();
	}
}
