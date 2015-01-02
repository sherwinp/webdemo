package com.niksoft;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.*;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@RequestScoped
public class RequestController {
	private static final Logger log = Logger.getLogger(RequestController.class.getName());
	public RequestController() {
	};

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		String landingPage = "index.html";
		
		try{
			
			request.login(username, password);

		}catch(ServletException ex){
			
		}
		
		if (request.getUserPrincipal() == null) {
			context.addMessage(null, new FacesMessage("Unknown login"));
			log.info(request.getSession().getId());

		} else {
			landingPage = "index.html?faces-redirect=true&";
			log.warning(String.format("!! Authenticated !! %s", request.getUserPrincipal().getName()));
		}

		return landingPage;
	}

	public boolean IsInRole() throws PolicyContextException{
		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		boolean result = request.getUserPrincipal() != null;
		log.log(Level.FINE, String.format("jsessionid: %s isInRole: %b", request.getSession().getId(), result));
		Subject subject = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
		if( subject != null )
		for (Principal principal : subject.getPrincipals()) {
			log.log(Level.FINE, "In subject: " + principal.getName());
		}
		return result;
	}
	
	public String logout() {
		log.warning("AuthenticationController.logout: logging - out.");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		request.getSession().invalidate();
		return "index.html?faces-redirect=true&";
	}
	
	public String getURIId() throws UnsupportedEncodingException {
		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		String url = String.format("%s", request.getRequestURL());
		return url;
	}

	public String getUsers(){
		return "users.xhtml?faces-redirect=true&";
	}
	  public String getUsername() {
	    return this.username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  public String getPassword() {
	    return this.password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }

	  private String username;
	  private String password;
}
