package com.salon.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.service.crypto.TokenCrypt;

import java.io.IOException;

public class AuthFillter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = "";
		TokenCrypt tokenCrypt = new TokenCrypt();
		AuthBean authBean = new AuthBean();
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("token")) {
				token = cookie.getValue();
			}
		}
		try {
			System.out.println(token);
			authBean = tokenCrypt.checkToken(token);
			System.out.println(authBean.getErrorMessage());
			if (token == null || authBean.getErrorMessage() != null) {
				res.sendRedirect("/login");
			}
		} catch (Throwable e) {
			System.out.println(authBean.getErrorMessage());
			e.printStackTrace();
		}
		chain.doFilter(req, res);
	}
}
