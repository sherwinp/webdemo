package com.niksoft.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class VoucherDAService extends GenericDataAccessService<Voucher, Integer> {
	private static final Logger log = Logger.getLogger(VoucherDAService.class.getName());
	@Override
	public Vouchers findAll() {
		Vouchers list = new Vouchers();
		try {
			list =  execute("SELECT * FROM vouchers;", new mapped_values() );
			
		} catch (NamingException ex) {
			log.warning(ex.getMessage());
		} catch (SQLException ex) {
			log.warning(ex.getMessage());
		} finally {

		}
		return list;
	}

   private Vouchers execute(String commandText, mapped_values mv) throws NamingException, SQLException{
	   InitialContext ic = new InitialContext();
		try {
			String dsName = "java:jboss/datasources/DemoDSsqlite";
			DataSource ds = (javax.sql.DataSource) ic.lookup(dsName);
			Connection connection = ds.getConnection();
			try {
				Statement statement = connection.createStatement();
				try {
					ResultSet resultSet = statement.executeQuery(commandText);
					try{
						return mv.maptovalues(resultSet);
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
   class mapped_values{
	  Vouchers maptovalues(ResultSet rs) throws SQLException{
		  Vouchers list = new Vouchers();
			while(rs.next()){
				list.add(new Voucher(rs.getInt(1), rs.getString(2)));
			}
		  return list;
	   }
   }
}
