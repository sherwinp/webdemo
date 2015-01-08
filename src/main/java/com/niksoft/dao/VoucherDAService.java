package com.niksoft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.NamingException;

public class VoucherDAService extends GenericDataAccessService<Voucher, Integer> {
	private static final Logger log = Logger.getLogger(VoucherDAService.class.getName());
	@Override
	public Vouchers findAll() {
		Vouchers list = new Vouchers();
		try {
			list =  (Vouchers) execute("SELECT * FROM vouchers;", new mapped_values() );
			
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
	public Vouchers maptovalues(ResultSet rs) throws SQLException{
		  Vouchers list = new Vouchers();
			while(rs.next()){
				list.add(new Voucher(rs.getInt(1), rs.getString(2)));
			}
		  return list;
	   }
   }
}
