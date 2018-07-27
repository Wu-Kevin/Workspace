package com.revature.application;

import java.util.Scanner;

import com.revature.model.User;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		User loginUser = AccountActions.login(sc);
		AccountActions.userInterface(loginUser, sc);
		System.exit(0);
	}

}
