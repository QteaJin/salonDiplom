package com.salon.controller.client;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.repository.bean.client.ClientBean;
import com.salon.repository.bean.client.ClientBeanSimple;
import com.salon.repository.bean.client.ClientProfileBean;
import com.salon.repository.bean.profile.ProfileBean;
import com.salon.service.client.ClientService;
import com.salon.service.crypto.TokenCryptImpl;
import com.salon.service.profile.ProfileService;
import com.salon.utility.EnumRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

	private ClientService clientService;

	@Autowired
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	@Autowired
	private TokenCryptImpl crypt;

	@Autowired
	private ProfileService profileService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ClientBean findById(@PathVariable("id") long id) {
		return clientService.findById(id);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<ClientBean> findAllClient() {
		return clientService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ClientBean createClient(@RequestBody ClientBean clientBean) {
		return clientService.save(clientBean);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable("id") long id) {
		clientService.delete(clientService.findById(id));
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ClientBean updateClient(@RequestBody ClientBean clientBean) {
		return clientService.update(clientBean);
	}

	@RequestMapping(value = "/admin/all", method = RequestMethod.GET)
	public List<ClientBeanSimple> findAllClientsForAdmin() {
		return clientService.findAllClients();
	}

	@RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
	public ClientProfileBean findClientForAdmin(@PathVariable("id") long id) {
		return clientService.findClient(id);
	}

	@RequestMapping(value = "/admin/changeProfile", method = RequestMethod.POST)
	public ClientProfileBean changeClientProfile(@RequestBody ClientProfileBean clientBean) {
		return clientService.changeClientProfile(clientBean);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ClientProfileBean getPersonalProfile(@CookieValue("token") String token) {
		ClientProfileBean bean = new ClientProfileBean();
		AuthBean authBean = crypt.checkToken(token);
		if(authBean.getErrorMessage() == null && authBean.getEnumRole().equals(EnumRole.CLIENT)) {
			
			bean = findClientForAdmin(authBean.getUserId());
			return bean;
		}

		bean.setErrorMessage(authBean.getErrorMessage());
		return bean;
	}
}
