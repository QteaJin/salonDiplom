package com.salon.service.salon;

import java.util.List;

import com.salon.repository.bean.salon.SalonBean;
import com.salon.repository.bean.salon.SalonCreateEditBean;
import com.salon.repository.entity.salon.Salon;
import com.salon.service.CRUDService;

public interface SalonService extends CRUDService<SalonBean, Salon>{
	
	SalonCreateEditBean createSalon(SalonCreateEditBean bean);
	List<SalonCreateEditBean> findAllSalons ();
}
