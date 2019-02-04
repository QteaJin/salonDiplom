package com.salon.repository.bean.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salon.repository.entity.client.Client;
import com.salon.repository.entity.worker.Worker;


public class ProfileBean {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String login;
    private String password;
    private Worker worker;
    private Client client;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
