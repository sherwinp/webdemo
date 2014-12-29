package com.niksoft.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="vouchers")
@XmlSeeAlso({ Voucher.class })
public class Vouchers extends ArrayList<Voucher> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Vouchers() {

	}

	@XmlElement(name = "voucher")
	public List<Voucher> getVouchers() {
		return this;
	}
}
