package com.salon.service.adress.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.salon.repository.bean.adress.AdressBean;
import com.salon.repository.dao.adress.AdressDAO;
import com.salon.repository.entity.address.Address;
import com.salon.service.adress.AdressService;


@Service
public class AdressServiceImpl implements AdressService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdressServiceImpl.class);
	private AdressDAO adressDAO;

	@Autowired
	public void setAdressDAO(AdressDAO adressDAO) {
		this.adressDAO = adressDAO;
	}

	@Override
	public AdressBean save(AdressBean bean) {
		LOGGER.debug("Adress save start");

		Address adress = adressDAO.save(toDomain(bean));

		LOGGER.debug("Adress save finish");

		return toBean(adress);

	}

	@Override
	public List<AdressBean> findAll() {
		LOGGER.debug("find all Adress start");

		List<Address> adress = adressDAO.findAll();

		LOGGER.debug("find all Adress finish");

		return toBean(adress);
	}

	@Override
	public AdressBean findById(Long id) {
		LOGGER.debug("find by id Adress start");

		Optional<Address> adress = adressDAO.findById(id);

		LOGGER.debug("find by id Adress finish");

		return toBean(adress.get());
	}

	@Override
	public AdressBean update(AdressBean bean) {
		LOGGER.debug("update Adress start");

		Address adress = adressDAO.saveAndFlush(toDomain(bean));

		LOGGER.debug("update Adress finish");

		return toBean(adress);
	}

	@Override
	public void delete(AdressBean bean) {
		adressDAO.delete(toDomain(bean));

	}

	@Override
	public List<AdressBean> findByExample(AdressBean bean) {
		LOGGER.debug("find by example Adress start");

		List<Address> adress = adressDAO.findAll(Example.of(toDomain(bean)));

		LOGGER.debug("find by example Adress finish");
		return toBean(adress);
	}

	@Override
	public AdressBean toBean(Address domain) {
		AdressBean adressBean = new AdressBean();
		adressBean.setAddressId(domain.getAddressId());
		adressBean.setCity(domain.getCity());
		adressBean.setClientsList(domain.getClientsList());
		adressBean.setCountry(domain.getCountry());
		adressBean.setLat(domain.getLat());
		adressBean.setLng(domain.getLng());
		adressBean.setSalonList(domain.getSalonList());
		adressBean.setStreet(domain.getStreet());
		return adressBean;
	}

	@Override
	public Address toDomain(AdressBean bean) {
		Address adress = new Address();
		adress.setAddressId(bean.getAddressId());
		adress.setCity(bean.getCity());
		adress.setClientsList(bean.getClientsList());
		adress.setCountry(bean.getCountry());
		adress.setLat(bean.getLat());
		adress.setLng(bean.getLng());
		adress.setSalonList(bean.getSalonList());
		adress.setStreet(bean.getStreet());
		return adress;
	}

	private List<AdressBean> toBean(List<Address> adresses) {
		List<AdressBean> adressBeans = new ArrayList<>();
		for (Address adress : adresses) {
			adressBeans.add(toBean(adress));
		}
		return adressBeans;
	}
}
