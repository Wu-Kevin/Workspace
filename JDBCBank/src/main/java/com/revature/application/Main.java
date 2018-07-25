package com.revature.application;

import java.util.Scanner;

import com.revature.model.User;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		User loginUser = AccountActions.login(sc);

		if (loginUser.getUserID()== 1) {
			AdminActions.adminInterface(sc);
		}
		else if (loginUser.getUserID() != 1) {
			AccountActions.userInterface(loginUser, sc);
		}
		
		sc.close();
	}

}

