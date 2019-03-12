package com.salon.controller.client;

import com.salon.repository.bean.client.ClientBean;
import com.salon.service.client.ClientService;
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
    
//    @RequestMapping(value = "/cookie", method = RequestMethod.GET)
//    public String getCookie(@CookieValue (value = "token", required = false) String token ) {
//    
//    	if(token == null) {return "null";} 
//    	return token;
//    }
}
