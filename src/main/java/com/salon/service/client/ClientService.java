package com.salon.service.client;

import java.util.List;

import com.salon.repository.bean.client.ClientBean;
import com.salon.repository.bean.client.ClientBeanSimple;
import com.salon.repository.bean.client.ClientProfileBean;
import com.salon.repository.entity.client.Client;
import com.salon.service.CRUDService;


public interface ClientService extends CRUDService<ClientBean, Client> {

	List<ClientBeanSimple> findAllClients();

	ClientProfileBean findClient(long id);

	ClientProfileBean changeClientProfile(ClientProfileBean clientBean);
}
