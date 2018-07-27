package com.revature.application;

import java.util.List;
import java.util.Scanner;

import com.revature.model.User;
import com.revature.service.UserService;

public class AccountChecks {
	
	public static boolean checkExists(User loginUser) {
		List<User> accountList = checkAccounts();
		
		for(User u: accountList) {
			if (u.getUserName().equals(loginUser.getUserName()) && u.getPassword().equals(loginUser.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	
	public static User checkMultiple(User loginUser, Scanner sc) {
		List<User> accountList = checkAccounts();
		int count = 0;
		boolean contains = false;
		
		for(User u: accountList) {
			if (u.getUserName().equals(loginUser.getUserName()) && u.getPassword().equals(loginUser.getPassword())) {
				count++;
			}
		}
		
		if (count > 1) {
			System.out.println("Identical username and passwords detected.");
			System.out.println("Please input your unique user ID");
			loginUser.setUserID(sc.nextInt());
			
			for (User u : accountList) {
				if (u.getUserID() == loginUser.getUserID() && u.getUserName().equals(loginUser.getUserName()) && u.getPassword().equals(loginUser.getPassword())) {
					contains = true;
				}
			}
			if (contains == false) {
				System.out.println("This account does not exist. Logging out");
				System.exit(0);
			}
			else {
				System.out.println("Account found! Proceeding to banking options" + "\n");
				loginUser = UserService.getUserService().retrieveUser(loginUser.getUserID());
			}
					
		}

		//multiple accounts not found
		return loginUser;
	}
	
	public static List<User> checkAccounts() {
		return UserService.getUserService().allUsers();
	}
}
