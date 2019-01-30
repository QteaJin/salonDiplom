package com.salon.repository.bean.profile;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProfileBean {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String login;
    private String password;
    private Long workerId;
    private Long clientId;

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
    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
