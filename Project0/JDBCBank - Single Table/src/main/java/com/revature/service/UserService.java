package com.revature.service;

import java.util.List;

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
	
	public static boolean insertNewUser(String username, String password) {
		return UserDAOImplement.getDao().insertUser(username, password);
	}
	
	public static boolean insertAccount(int userid, String username, String password) {
		return UserDAOImplement.getDao().insertUser(userid, username, password);
	}
	
	public static boolean deleteAccount(int bankid) {
		return UserDAOImplement.getDao().deleteUser(bankid);
	}
	
	public static List <User> allUsers() {
		return UserDAOImplement.getDao().allUsers();
	}
	
	public static boolean modifyAccount(int bankid, double change) {
		return UserDAOImplement.getDao().updateUser(bankid, change);
	}
	
	

}

