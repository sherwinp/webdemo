package com.niksoft;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.*;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter extends HttpServlet implements Filter {

	private static final Logger log = Logger.getLogger(AuthenticationFilter.class.getName());
	@Override
	public void destroy() {
		
		log.info("---Destroy Filter.");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,	FilterChain chain) throws IOException, ServletException {
		log.info("---Do Filter.");
		
		chain.doFilter(request, response);
//		
//		HttpServletRequest httpRequest = (HttpServletRequest)request;
//		HttpServletResponse httpResponse = (HttpServletResponse)response;
//		
//		for(String header: httpResponse.getHeaderNames()){
//			log.info(header);
//		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		log.info("---Init Filter.");

	}

}
