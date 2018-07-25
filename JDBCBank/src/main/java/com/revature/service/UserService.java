package com.revature.service;

import java.util.List;

import com.revature.dao.AccountDAOImplement;
import com.revature.dao.UserDAOImplement;
import com.revature.model.User;

public class UserService {
	private static UserService userService;
	
	private UserService() {
	}
	
	public static UserService getUserService() {
		if (userService==null) {
			userService = new UserService();
		}
		return userService;
	}
	
	public static User retrieveUser(String username, String password) {
		return UserDAOImplement.getDao().getUser(username, password);
	}
	
	//method overload
	public static User retrieveUser(int userid) {
		return UserDAOImplement.getDao().getUser(userid);
	}
	
	public static boolean insertUser(String username, String password) {
		return UserDAOImplement.getDao().insertUser(username, password);
	}
	
	
	public static boolean deleteUser(int userid) {
		return UserDAOImplement.getDao().deleteUser(userid);
	}
	
	public static List <User> allUsers() {
		return UserDAOImplement.getDao().allUsers();
	}
	
	//three different types of updates...
	public static boolean updateUser(int input, int userid, String username, String password) {
		return UserDAOImplement.getDao().updateUser(input, userid, username, password);
	}

}

