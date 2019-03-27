package com.salon.controller.cabinet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.service.crypto.TokenCryptImpl;

@RestController
public class CabinetController {
	@Autowired
	private TokenCryptImpl crypt;

	@RequestMapping(value = "/cabinet", method = RequestMethod.GET)
	public void forwardCabinetUser(@CookieValue(value = "token", required = false) String token,
			HttpServletResponse response) throws Throwable {

		AuthBean authBean = crypt.checkToken(token);
		if (authBean.getErrorMessage() != null) {
			response.sendRedirect("/login");
		} else {

			switch (authBean.getEnumRole().name()) {
			case "CLIENT":
				response.sendRedirect("/customer");

				break;

			case "WORKER":
				response.sendRedirect("/employee");

				break;

			case "ADMIN":
				response.sendRedirect("/admin");

				break;

			case "MANAGER":
				response.sendRedirect("/manager");

				break;

			default:
				response.sendRedirect("/");
				break;

			}

		}
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
		public ModelAndView clientCabinet(ModelAndView model, HttpServletResponse response, HttpServletRequest request)  {
		
		String token = crypt.getCookie(request);
		AuthBean authBean = crypt.checkToken(token);
		
		if (authBean.getErrorMessage() != null) {
			Cookie errorCookie = new Cookie("token", "error");
			response.addCookie(errorCookie);

			}
		model.setViewName("client");
		return model;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminCabinet(ModelAndView model, HttpServletResponse response, HttpServletRequest request)  {
	
	String token = crypt.getCookie(request);
	AuthBean authBean = crypt.checkToken(token);
	
	if (authBean.getErrorMessage() != null) {
		Cookie errorCookie = new Cookie("token", "error");
		response.addCookie(errorCookie);

		}
	model.setViewName("admin");
	return model;
}
}
