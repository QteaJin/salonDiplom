package com.salon.controller.adress;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salon.repository.bean.adress.AdressBean;
import com.salon.service.adress.AdressService;

@RestController
@RequestMapping("/adress")
public class AdressController {

	private AdressService adressService;

	@Autowired
	public void setAdressService(AdressService adressService) {
		this.adressService = adressService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public AdressBean findById(@PathVariable("id") long id) {
		return adressService.findById(id);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<AdressBean> findAllAdresses() {
		return adressService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public AdressBean createAdress(@RequestBody AdressBean adressBean) {
		return adressService.save(adressBean);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable("id") long id) {
		adressService.delete(adressService.findById(id));
	}

	@RequestMapping(method = RequestMethod.PUT)
	public AdressBean updateAdress(@RequestBody AdressBean adressBean) {
		return adressService.update(adressBean);
	}

}
