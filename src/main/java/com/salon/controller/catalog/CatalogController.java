package com.salon.controller.catalog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.salon.repository.bean.catalog.CatalogBean;
import com.salon.repository.bean.catalog.CatalogBeanCreateAdmin;
import com.salon.service.catalog.CatalogService;
import com.salon.service.crypto.TokenCrypt;
import com.salon.service.crypto.TokenCryptImpl;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private TokenCryptImpl tokenCryptImpl;

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CatalogBean findById(@PathVariable("id") long id) {
		return catalogService.findById(id);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<CatalogBean> findAllCatalog() {
		return catalogService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public CatalogBean createCatalog(@RequestBody CatalogBean catalogBean) {
		return catalogService.save(catalogBean);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable("id") long id) {
		catalogService.delete(catalogService.findById(id));
	}

	@RequestMapping(method = RequestMethod.PUT)
	public CatalogBean updateCatalog(@RequestBody CatalogBean catalogBean) {
		return catalogService.update(catalogBean);
	}
	@RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
	public List<CatalogBeanCreateAdmin> findBySkillId(@PathVariable("id") long skillId, HttpServletRequest request) {
		
				
		return catalogService.findCatalogsBySkillId(skillId, request);
	}
}
