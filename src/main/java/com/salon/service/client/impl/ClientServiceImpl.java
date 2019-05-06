package com.salon.service.client.impl;

import com.salon.repository.bean.adress.AdressBean;
import com.salon.repository.bean.client.ClientBean;
import com.salon.repository.bean.client.ClientBeanSimple;
import com.salon.repository.bean.client.ClientProfileBean;
import com.salon.repository.dao.client.ClientDAO;
import com.salon.repository.entity.client.Client;
import com.salon.service.adress.AdressService;
import com.salon.service.client.ClientService;
import com.salon.service.google.GoogleLocationService;
import com.salon.service.salon.SalonService;
import com.salon.utility.EnumRole;
import com.salon.utility.EnumStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientDAO clientDAO;

    public ClientDAO getClientDAO() {
        return clientDAO;
    }

    @Autowired
    private GoogleLocationService locationService;

    @Autowired
    private AdressService adressService;

    @Autowired
    private SalonService salonService;


    @Override
    public ClientBean save(ClientBean bean) {
        LOGGER.debug("Client save start");

//        AdressBean adressBean = adressService.save(locationService.receiveAddressFromGoogle());
//        bean.setAddress(adressService.toDomain(adressBean));

        Client client = clientDAO.save(toDomain(bean));

        LOGGER.debug("Client save finish");

        return toBean(client);
    }

    @Override
    public List<ClientBean> findAll() {
        LOGGER.debug("find all Client start");

        List<Client> clients = clientDAO.findAll();

        LOGGER.debug("find all Client finish");

        return toBean(clients);
    }



    @Override
    public ClientBean findById(Long id) {
        LOGGER.debug("find by id Client start");

        Optional<Client> client = clientDAO.findById(id);

        LOGGER.debug("find by id Client start");

        return toBean(client.get());
    }

    @Override
    public ClientBean update(ClientBean bean) {
        LOGGER.debug("update Client start");

        Client client = clientDAO.saveAndFlush(toDomain(bean));

        LOGGER.debug("update Client finish");

        return toBean(client);
    }

    @Override
    public void delete(ClientBean bean) {
        clientDAO.delete(toDomain(bean));
    }

    @Override
    public List<ClientBean> findByExample(ClientBean bean) {
        LOGGER.debug("find by example Client start");

        List<Client> clients = clientDAO.findAll(Example.of(toDomain(bean)));

        LOGGER.debug("find by example Client finish");
        return toBean(clients);
    }

    @Override
    public ClientBean toBean(Client domain) {
        ClientBean clientBean = new ClientBean();
        clientBean.setId(domain.getId());
        clientBean.setAddress(domain.getAddress());
        clientBean.setRole(domain.getRole());
        clientBean.setStatus(domain.getStatus());
        clientBean.setProfile(domain.getProfile());
        clientBean.setDiscount(domain.getDiscount());
        clientBean.setSalon(domain.getSalon());
        clientBean.setCheckList(domain.getCheckList());
        clientBean.setDescription(domain.getDescription());

        return clientBean;
    }

    @Override
    public Client toDomain(ClientBean bean) {
        Client client = new Client();
        client.setId(bean.getId());
        client.setAddress(bean.getAddress());
        if (Objects.isNull(bean.getRole())) {
            client.setRole(EnumRole.CLIENT);
        } else {
            client.setRole(bean.getRole());
        }

        if (Objects.isNull(bean.getStatus())) {
            client.setStatus(EnumStatus.NOACTIVE);
        } else {
            client.setStatus(bean.getStatus());
        }

        client.setProfile(bean.getProfile());
        client.setDiscount(bean.getDiscount());
        client.setCheckList(bean.getCheckList());
        client.setSalon(bean.getSalon());
        client.setDescription(bean.getDescription());
        return client;
    }

    private List<ClientBean> toBean(List<Client> clients) {
        List<ClientBean> clientBeans = new ArrayList<>();
        for (Client client : clients) {
            clientBeans.add(toBean(client));
        }
        return clientBeans;
    }

	@Override
	public List<ClientBeanSimple> findAllClients() {
		List<ClientBean> beans = findAll();
		List<ClientBeanSimple> beanSimples = new ArrayList<>();
		for (ClientBean bean : beans) {
			ClientBeanSimple simple = new ClientBeanSimple();
			simple.setEmail(bean.getProfile().getEmail());
			simple.setId(bean.getId());
			simple.setName(bean.getProfile().getName());
			simple.setPhone(bean.getProfile().getPhone());
			simple.setProfileId(bean.getProfile().getProfileId());
			simple.setStatus(bean.getStatus());
			beanSimples.add(simple);
		}
		return beanSimples;
	}

	@Override
	public ClientProfileBean findClient(long id) {
		ClientBean clientBean = findById(id);
		ClientProfileBean bean = new ClientProfileBean();
		bean.setDescription(clientBean.getProfile().getDescription());
		bean.setEmail(clientBean.getProfile().getEmail());
		bean.setId(clientBean.getId());
		bean.setLogin(clientBean.getProfile().getLogin());
		bean.setName(clientBean.getProfile().getName());
		bean.setPassword(clientBean.getProfile().getPassword());
		bean.setPhone(clientBean.getProfile().getPhone());
		bean.setStatus(clientBean.getStatus());
		return bean;
	}

	@Override
	public ClientProfileBean changeClientProfile(ClientProfileBean clientBean) {
		ClientBean bean = findById(clientBean.getId());
		bean.getProfile().setDescription(clientBean.getDescription());
		bean.getProfile().setEmail(clientBean.getEmail());
		bean.getProfile().setLogin(clientBean.getLogin());
		bean.getProfile().setName(clientBean.getName());
		bean.getProfile().setPassword(clientBean.getPassword());
		bean.getProfile().setPhone(clientBean.getPhone());
		if(clientBean.getStatus() != null) {
			bean.setStatus(clientBean.getStatus());
		}
		
		update(bean);
		return clientBean;
	}
}
