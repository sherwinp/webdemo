package com.niksoft;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
public class AuthenticationValidator extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AuthenticationValidator.class.getName());

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean result = request.getUserPrincipal() != null;
		log.info(String.format("servlet: jsessionid: %s isInRole: %b", request.getSession().getId(), result));

		if (result) {
			response.setStatus(200);
			response.getWriter().format("%s", "{}");
		} else {
			response.setStatus(SC_FORBIDDEN);
		}

	}
}
