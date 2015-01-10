package com.niksoft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.NamingException;

public class UserDAService extends GenericDataAccessService<User, Integer> {
	private static final Logger log = Logger.getLogger(UserDAService.class.getName());
	@Override
	public Users findAll() {
		Users list = new Users();
		try {
			list = (Users) execute("SELECT * FROM A_USER;", new mapped_values() );
			
		} catch (NamingException ex) {
			log.warning(ex.getMessage());
		} catch (SQLException ex) {
			log.warning(ex.getMessage());
		} finally {

		}
		return list;
	}
	private class mapped_values implements IMapped_Values{
		  @Override
		public Users maptovalues(ResultSet rs) throws SQLException{
			  Users list = new Users();
				while(rs.next()){
					list.add(new User(rs.getInt(1), rs.getString(2)));
				}
			  return list;
		   }
	}
}
