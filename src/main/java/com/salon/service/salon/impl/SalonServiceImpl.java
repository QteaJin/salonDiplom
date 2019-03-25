package com.salon.service.salon.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.salon.repository.bean.adress.AdressBean;
import com.salon.repository.bean.salon.SalonBean;
import com.salon.repository.bean.salon.SalonCreateEditBean;
import com.salon.repository.dao.adress.AdressDAO;
import com.salon.repository.dao.salon.SalonDAO;
import com.salon.repository.entity.address.Address;
import com.salon.repository.entity.salon.Salon;
import com.salon.service.adress.AdressService;
import com.salon.service.salon.SalonService;

@Service
public class SalonServiceImpl implements SalonService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SalonServiceImpl.class);

	@Autowired
	private SalonDAO salonDAO;
	
	@Autowired
	private AdressService adressService;
	

	public SalonDAO getSalonDAO() {
		return salonDAO;
	}

	@Override
	public SalonBean save(SalonBean bean) {
		LOGGER.debug("Salon save start");

		Salon salon = salonDAO.save(toDomain(bean));

		LOGGER.debug("Salon save finish");

		return toBean(salon);
	}

	@Override
	public List<SalonBean> findAll() {
		LOGGER.debug("find all Salons start");

		List<Salon> salons = salonDAO.findAll();

		LOGGER.debug("find all Salons finish");

		return toBean(salons);
	}

	@Override
	public SalonBean findById(Long id) {
		LOGGER.debug("find by id Salon start");

		Optional<Salon> salon = salonDAO.findById(id);
		if (salon.isPresent()) {
			return toBean(salon.get());
		}

		LOGGER.debug("find by id Salon finish");

		return new SalonBean();
	}

	@Override
	public SalonBean update(SalonBean bean) {
		LOGGER.debug("update Salon start");

		Salon salon = salonDAO.saveAndFlush(toDomain(bean));

		LOGGER.debug("update Salon finish");

		return toBean(salon);
	}

	@Override
	public void delete(SalonBean bean) {
		salonDAO.delete(toDomain(bean));

	}

	@Override
	public List<SalonBean> findByExample(SalonBean bean) {
		LOGGER.debug("find by example Salon start");

		List<Salon> salons = salonDAO.findAll(Example.of(toDomain(bean)));

		LOGGER.debug("find by example Salon finish");
		return toBean(salons);
	}

	@Override
	public SalonBean toBean(Salon domain) {
		SalonBean salonBean = new SalonBean();
		salonBean.setSalonId(domain.getSalonId());
		salonBean.setAddress(domain.getAddress());
		salonBean.setClientList(domain.getClientList());
		salonBean.setDescription(domain.getDescription());
		salonBean.setName(domain.getName());
		salonBean.setTimeList(domain.getTimeList());
		salonBean.setWorkerList(domain.getWorkerList());
		return salonBean;
	}

	@Override
	public Salon toDomain(SalonBean bean) {

		Salon salon = new Salon();
		salon.setSalonId(bean.getSalonId());
		salon.setAddress(bean.getAddress());
		salon.setClientList(bean.getClientList());
		salon.setDescription(bean.getDescription());
		salon.setName(bean.getName());
		salon.setTimeList(bean.getTimeList());
		salon.setWorkerList(bean.getWorkerList());

		return salon;
	}

	private List<SalonBean> toBean(List<Salon> salons) {
		List<SalonBean> salonBeans = new ArrayList<>();
		for (Salon salon : salons) {
			salonBeans.add(toBean(salon));
		}

		return salonBeans;

	}

	@Override
	public SalonCreateEditBean createSalon(SalonCreateEditBean bean) {
		
		AdressBean salonAdress = new AdressBean();
		salonAdress.setCountry(bean.getCountry());
		salonAdress.setCity(bean.getCity());
		salonAdress.setStreet(bean.getStreet());
		salonAdress = adressService.save(salonAdress);
		
		AdressBean adBean = adressService.findById(salonAdress.getAddressId());
		
		SalonBean salonBean = new SalonBean();
		salonBean.setName(bean.getName());
		salonBean.setDescription(bean.getDescription());
		salonBean.setAddress(adressService.toDomain(adBean));
		
		salonBean = save(salonBean);
		
		bean.setId(salonBean.getSalonId());
		
		
		return bean;
	}

	@Override
	public List<SalonCreateEditBean> findAllSalons() {
		List<SalonCreateEditBean> beans = new ArrayList<>();
		
		List<SalonBean> salonBeans = new ArrayList<SalonBean>();
		salonBeans = findAll();
		
		if (salonBeans.isEmpty()) {
			return beans;
		}
		for (SalonBean salonBean : salonBeans) {
			SalonCreateEditBean simpleBean = new SalonCreateEditBean();
			simpleBean.setId(salonBean.getSalonId());
			simpleBean.setName(salonBean.getName());
			simpleBean.setDescription(salonBean.getDescription());
			simpleBean.setCountry(salonBean.getAddress().getCountry());
			simpleBean.setCity(salonBean.getAddress().getCity());
			simpleBean.setStreet(salonBean.getAddress().getStreet());
			beans.add(simpleBean);
			
		}
		
		
		return beans;
	}

}
