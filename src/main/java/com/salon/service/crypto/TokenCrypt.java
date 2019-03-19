package com.salon.service.crypto;

import javax.servlet.http.HttpServletRequest;

import com.salon.repository.bean.auth.AuthBean;

public interface TokenCrypt {
	
	String cryptToken(String message);
	String decryptToken(String message);
	AuthBean checkToken(String token);
	String getCookie(HttpServletRequest req);

}
