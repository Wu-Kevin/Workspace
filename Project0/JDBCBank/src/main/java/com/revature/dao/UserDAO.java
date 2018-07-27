package com.revature.dao;

import java.util.List;
import com.revature.model.User;

public interface UserDAO {

	public User getUser(String username, String password);
	
	public User getUser(int userid);
	
	public boolean insertUser (String username, String password);
	
	public boolean deleteUser(int bankid); 
	
	public List<User> allUsers();
	
	public boolean updateUser(int input, int userid, String username, String password);

}

