package com.niksoft.dao;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="users")
@XmlSeeAlso({ User.class })
public class Users extends ArrayList<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Users() {

	}

	@XmlElement(name = "user")
	public List<User> getUsers() {
		return this;
	}
}
