package com.salon.controller.cabinet;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.service.crypto.TokenCrypt;

@RestController
public class CabinetController {
	@Autowired
	private TokenCrypt crypt;

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
}
