package com.niksoft.dao;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="voucher")
public class Voucher {
	
	public Voucher(){
		
	}
	public Voucher(Integer rowid, String Description){
		id = rowid; 
		descr = Description;
	}
	public Voucher(String Description){
		descr = Description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	@XmlElement(name="id")  private int id;
	@XmlElement(name="descr")  private String descr;
}
