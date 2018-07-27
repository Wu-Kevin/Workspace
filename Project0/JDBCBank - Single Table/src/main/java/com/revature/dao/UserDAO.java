package com.revature.dao;

import java.util.List;
import com.revature.model.User;

public interface UserDAO {

	public User getUser(String username, String password);
	
	public User getUser(int userid);
	
	public boolean insertUser (String username, String password);
	
	public boolean insertUser(int userid, String username, String password);
	
	public boolean deleteUser(int bankid); 
	
	public List<User> allUsers();
	
	public boolean updateUser(int bankid, double change);
	
}

//create table userInformation (
//	    user_id number(10) not null,
//	    username varchar2(200) not null,
//	    password varchar2(200) not null,
//	    primary key (user_id)
//	);
