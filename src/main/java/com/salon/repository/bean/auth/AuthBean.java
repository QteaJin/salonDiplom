package com.salon.repository.bean.auth;

import com.salon.utility.EnumRole;

import java.security.Timestamp;

public class AuthBean {
    private Long userId;
    private String userName;
    private EnumRole enumRole;
    private String token;
    private Timestamp activeTime;
    private String errorMessage;

    public AuthBean() {
    }

    public AuthBean(Long userId, String userName, EnumRole enumRole,
                    String token, Timestamp activeTime, String errorMessage) {
        this.userId = userId;
        this.userName = userName;
        this.enumRole = enumRole;
        this.token = token;
        this.activeTime = activeTime;
        this.errorMessage = errorMessage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public EnumRole getEnumRole() {
        return enumRole;
    }

    public void setEnumRole(EnumRole enumRole) {
        this.enumRole = enumRole;
    }

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
}
