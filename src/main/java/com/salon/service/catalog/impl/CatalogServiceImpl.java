package com.salon.service.catalog.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.salon.repository.bean.catalog.CatalogBean;
import com.salon.repository.bean.catalog.CatalogBeanCreateAdmin;
import com.salon.repository.dao.catalog.CatalogDAO;
import com.salon.repository.entity.catalog.Catalog;
import com.salon.repository.entity.skills.Skills;
import com.salon.service.catalog.CatalogService;
import com.salon.service.crypto.TokenCryptImpl;

@Service
public class CatalogServiceImpl implements CatalogService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogServiceImpl.class);

	@Autowired
	private CatalogDAO catalogDAO;
	
    @Autowired
    private TokenCryptImpl tokenCrypt;

	@Override
	public CatalogBean save(CatalogBean bean) {
		LOGGER.debug("Catalog save start");

		Catalog catalog = catalogDAO.save(toDomain(bean));

		LOGGER.debug("Catalog save finish");

		return toBean(catalog);
	}

	@Override
	public List<CatalogBean> findAll() {
		LOGGER.debug("find all Catalog start");

		List<Catalog> catalogs = catalogDAO.findAll();

		LOGGER.debug("find all Catalog finish");

		return toBean(catalogs);
	}

	@Override
	public CatalogBean findById(Long id) {
		LOGGER.debug("find by id Catalog start");

		Optional<Catalog> catalog = catalogDAO.findById(id);

		LOGGER.debug("find by id Catalog finish");

		return toBean(catalog.get());
	}

	@Override
	public CatalogBean update(CatalogBean bean) {
		LOGGER.debug("update Catalog start");

		Catalog catalog = catalogDAO.saveAndFlush(toDomain(bean));

		LOGGER.debug("update Catalog finish");

		return toBean(catalog);
	}

	@Override
	public void delete(CatalogBean bean) {
		catalogDAO.delete(toDomain(bean));

	}

	@Override
	public List<CatalogBean> findByExample(CatalogBean bean) {
		LOGGER.debug("find by example Catalog start");

		List<Catalog> catalogs = catalogDAO.findAll(Example.of(toDomain(bean)));

		LOGGER.debug("find by example Catalog finish");
		return toBean(catalogs);
	}

	@Override
	public CatalogBean toBean(Catalog domain) {
		CatalogBean bean = new CatalogBean();
		bean.setCatalogId(domain.getCatalogId());
		bean.setCheckLists(domain.getCheckLists());
		bean.setDescription(domain.getDescription());
		bean.setName(domain.getName());
		bean.setPrice(domain.getPrice());
		bean.setSkills(domain.getSkills());
		bean.setTimeLead(domain.getTimeLead());
		return bean;
	}

	@Override
	public Catalog toDomain(CatalogBean bean) {
		Catalog catalog = new Catalog();
		catalog.setCatalogId(bean.getCatalogId());
		catalog.setCheckLists(bean.getCheckLists());
		catalog.setDescription(bean.getDescription());
		catalog.setName(bean.getName());
		catalog.setPrice(bean.getPrice());
		catalog.setSkills(bean.getSkills());
		catalog.setTimeLead(bean.getTimeLead());

		return catalog;
	}

	private List<CatalogBean> toBean(List<Catalog> catalogs) {
		List<CatalogBean> catalogBeans = new ArrayList<CatalogBean>();
		for (Catalog catalog : catalogs) {
			catalogBeans.add(toBean(catalog));
		}

		return catalogBeans;
	}

	@Override
	public List<CatalogBeanCreateAdmin> findCatalogsBySkillId(Long skillId, HttpServletRequest request) {
		List <CatalogBean> catalogBeans = new ArrayList<CatalogBean>();
		List <CatalogBeanCreateAdmin> catalogBeansAdmin = new ArrayList<CatalogBeanCreateAdmin>();
		
		String token = tokenCrypt.getCookie(request);
		if(tokenCrypt.checkToken(token).getErrorMessage() != null) {
			CatalogBeanCreateAdmin catalogBeanCreateAdmin = new CatalogBeanCreateAdmin();
			catalogBeanCreateAdmin.setToken("error");
			catalogBeansAdmin.add(catalogBeanCreateAdmin);
			return catalogBeansAdmin;
		}
		
		Skills skill = new Skills();
		skill.setSkillsId(skillId);
		CatalogBean catalogBeanExample = new CatalogBean();
		catalogBeanExample.setSkills(skill);
		catalogBeans = findByExample(catalogBeanExample);
		if(!catalogBeans.isEmpty()) {
			for (CatalogBean catalogBean : catalogBeans) {
				CatalogBeanCreateAdmin beanCreateAdmin = new CatalogBeanCreateAdmin();
				beanCreateAdmin.setCatalogId(catalogBean.getCatalogId());
				beanCreateAdmin.setDescription(catalogBean.getDescription());
				beanCreateAdmin.setName(catalogBean.getName());
				beanCreateAdmin.setPrice(catalogBean.getPrice());
				beanCreateAdmin.setTimeLead(catalogBean.getTimeLead());
				beanCreateAdmin.setSkillId(catalogBean.getSkills().getSkillsId());
				catalogBeansAdmin.add(beanCreateAdmin);
			}
			return catalogBeansAdmin;
		}
		
		return catalogBeansAdmin;
	}
}
