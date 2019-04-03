package com.salon.service.catalog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.salon.repository.bean.catalog.CatalogBean;
import com.salon.repository.bean.catalog.CatalogBeanCreateAdmin;
import com.salon.repository.entity.catalog.Catalog;
import com.salon.service.CRUDService;

public interface CatalogService extends CRUDService<CatalogBean, Catalog>{

	List<CatalogBeanCreateAdmin> findCatalogsBySkillId(Long skillId, HttpServletRequest request);
}
