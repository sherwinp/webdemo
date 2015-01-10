package com.niksoft.dao;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="user")
public class User {
	public User(){
		
	}
	public User(int id, String name){
		
	}
	@XmlElement(name="id") private int id;
	@XmlElement(name="name") private String username;
	@XmlElement(name="city") private String city;
	@XmlElement(name="zip") private String zip;
	@XmlElement(name="email") private String email;
}

