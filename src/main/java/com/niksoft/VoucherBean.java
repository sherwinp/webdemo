package com.niksoft;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@ManagedBean
@RequestScoped
public class VoucherBean {
	private static final Logger log = Logger.getLogger(VoucherBean.class.getName());

	public VoucherBean() throws ClassNotFoundException, SQLException, NamingException {
		init();
	}

	public void init() throws ClassNotFoundException, SQLException, NamingException {
		InitialContext ic = new InitialContext();
		try {
			String dsName = "java:jboss/datasources/DemoDSsqlite";
			DataSource ds = (javax.sql.DataSource) ic.lookup(dsName);
			Connection connection = ds.getConnection();
			try {
				Statement statement = connection.createStatement();
				try {
					ResultSet resultSet = statement.executeQuery("SELECT count(*) AS VoucherCount FROM vouchers;");
					try {

						while (resultSet.next()) {
							if( resultSet.getFetchSize() > 0 )	
								count = resultSet.getInt(0);
						}

					} finally {
						resultSet.close();
					}
				} finally {
					statement.close();
				}

			} finally {
				connection.close();
			}
		} finally {
			ic.close();
			ic = null;
		}
	}

	public String logout() {
		log.warning("AuthenticationController.logout: logging - out.");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		request.getSession().invalidate();
		return "index.html?faces-redirect=true&";
	}

	public String VoucherCount() {
		return String.format("%d", count);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private int count = 0;
}
