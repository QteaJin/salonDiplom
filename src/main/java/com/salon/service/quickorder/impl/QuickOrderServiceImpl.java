package com.salon.service.quickorder.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salon.repository.bean.adress.AdressBean;
import com.salon.repository.bean.checklist.CheckListBean;
import com.salon.repository.bean.client.ClientBean;
import com.salon.repository.bean.profile.ProfileBean;
import com.salon.repository.bean.quickorder.QuickOrderBean;
import com.salon.service.adress.AdressService;
import com.salon.service.checklist.CheckListService;
import com.salon.service.client.ClientService;
import com.salon.service.profile.ProfileService;
import com.salon.utility.EnumStatusCheckList;

@Service
public class QuickOrderServiceImpl {
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	CheckListService checkListService;
	
	@Autowired
	AdressService adressService;

	public void createNewOrder(QuickOrderBean orderBean) {
					
			ProfileBean profileBean = new ProfileBean();
			profileBean.setName(orderBean.getName());
			profileBean.setEmail(orderBean.getEmail());
			profileBean.setPhone(orderBean.getPhone());
			profileBean.setLogin("quick");
			profileBean.setPassword("quick");
			profileService.save(profileBean);
			
			AdressBean adressBean = new AdressBean();
			adressBean.setCity("odessa");
			adressBean.setCountry("unknown");
//			adressBean = adressService.save(adressBean);

			
			
			
			ClientBean clientBean = new ClientBean();
			clientBean.setAddress(adressService.toDomain(adressBean));
			clientBean.setProfile(profileService.toDomain(profileBean));
			clientBean = clientService.save(clientBean);
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Date sql = null;
		    Date parsed;
			try {
				parsed = format.parse(orderBean.getDateCreate());
				 sql = new java.sql.Date(parsed.getTime());
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		    
			CheckListBean checkListBean = new CheckListBean();

			
			checkListBean.setDateCreate(sql);
			checkListBean.setDateAppointment(sql);
			checkListBean.setStatus(EnumStatusCheckList.ACTIVE);
			checkListBean.setDescription(orderBean.getDescription());
			checkListBean.setClient(clientService.toDomain(clientBean));
			checkListService.save(checkListBean);
		}
	
}


