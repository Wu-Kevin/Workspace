package com.revature.application;

import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.model.User;
import com.revature.service.UserService;

public class AccountActions {

	public static User login(Scanner sc) {
		User loginUser = new User();

		System.out.println("(R)egistered or (U)nregistered user?");
		String input = sc.next();

		while (!input.equals("Registered") && !input.equals("Unregistered") && !input.equals("registered")
				&& !input.equals("unregistered") && !input.equals("R") && !input.equals("r") && !input.equals("U")
				&& !input.equals("u")) {
			System.out.println("Invalid command! Please Type Registered, Unregistered, R, or U");
			input = sc.next();
		}

		switch (input.charAt(0)) {
		case 'R':
		case 'r':
			System.out.println("Please log in to your account!");
			System.out.println("Username: ");
			// what if login is spaces or nothing
			loginUser.setUserName(sc.next());
			System.out.println("Your username is: " + loginUser.getUserName());
			System.out.println("Password: ");
			loginUser.setPassword(sc.next());
			System.out.println("You have entered your password" + "\n");

			// what happens if this user doesn't exist in the database?
			if (AccountChecks.checkExists(loginUser) == false) {
				System.out.println(
						"This username and password combination does not exist or input incorrectly. Would you like to register?");
				System.out.println("(1). Yes");
				System.out.println("(2). No");
				input = sc.next();
				if (input.equals("1") != true) {
					System.out.println("Thank you for your time! Exiting program.");
					System.exit(0);
				} else {
					UserService.getUserService().insertNewUser(loginUser.getUserName(), loginUser.getPassword());
					loginUser = UserService.getUserService().retrieveUser(loginUser.getUserName(),
							loginUser.getPassword());
					System.out.println("Your account has been created!");
					return loginUser;
				}
			}

			// what if multiple usernames?
			loginUser = AccountChecks.checkMultiple(loginUser, sc);
			if (loginUser.getUserID() != 0) {
				return loginUser;
			}

			// what about a superuser?
			loginUser = UserService.getUserService().retrieveUser(loginUser.getUserName(), loginUser.getPassword());
			return loginUser;

		case 'U':
		case 'u':
			System.out.println("Please register an account!");
			System.out.println("Create a username: ");
			loginUser.setUserName(sc.next());
			System.out.println("Your username is: " + loginUser.getUserName());
			System.out.println("Create a password: ");
			loginUser.setPassword(sc.next());
			System.out.println("You have created your password!");
			// what happens if this user exists in the database?

			UserService.getUserService().insertNewUser(loginUser.getUserName(), loginUser.getPassword());
			loginUser = UserService.getUserService().retrieveUser(loginUser.getUserName(), loginUser.getPassword());
			return loginUser;
		}
		return loginUser;
	}

	public static void userInterface(User loginUser, Scanner sc) {
		System.out.println("\nWhat would you like to do? Please select a number");
		System.out.println("(1). View existing accounts and balances");
		System.out.println("(2). Create an account");
		System.out.println("(3). Delete an account");
		System.out.println("(4). Deposit/Withdraw from an account");
		System.out.println("(5). Logout");

		int i = sc.nextInt();

		try {
			while (i < 1 || i > 5) {
				System.out.println(i + " is not a valid selection, please try again!");
				i = sc.nextInt();
			}
		} catch (InputMismatchException e) {
			System.err.println("Caught input mismatch error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Caught exception: " + e.getMessage());
		}

		while (i != 5) {
			switch (i) {
			case 1:
				viewAccounts(loginUser);
				break;
			case 2:
				createAccounts(loginUser, sc);
				break;
			case 3:
				deleteAccounts(loginUser, sc);
				break;
			case 4:
				updateAccounts(loginUser, sc);
				break;
			}
			System.out.println("\nWhat would you like to do? Please select a number");
			System.out.println("(1). View existing accounts and balances");
			System.out.println("(2). Create an account");
			System.out.println("(3). Delete an account");
			System.out.println("(4). Deposit/Withdraw from an account");
			System.out.println("(5). Logout");
			i = sc.nextInt();
			try {
				while (i < 1 || i > 5) {
					System.out.println(i + " is not a valid selection, please try again!");
					i = sc.nextInt();
				}
			} catch (InputMismatchException e) {
				System.err.println("Caught input mismatch error: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("Caught exception: " + e.getMessage());
			}
		}
		System.out.println("Thank you for your business!");

		return;

	}

	public static void viewAccounts(User loginUser) {
		List<User> accountList = AccountChecks.checkAccounts();

		int i = 1;
		for (User u : accountList) {
			if (u.getUserID() == loginUser.getUserID() && u.getUserName().equals(loginUser.getUserName())
					&& u.getPassword().equals(loginUser.getPassword())) {
				System.out.println("Account #" + i + ": Bank Account ID = #" + u.getBankAccountID()
						+ " with current funds = " + u.getAccountStatus());
				i++;
			}
		}
		if (i == 1) {
			System.out.println("You have no accounts to view");
		}
	}

	public static void createAccounts(User loginUser, Scanner sc) {

		// retain same userid, username, and password
		UserService.getUserService().insertAccount(loginUser.getUserID(), loginUser.getUserName(),
				loginUser.getPassword());
		System.out.println("Your new account has been created!");
		return;

	}

	//for some reason delete goes straight into modify account, please check
	public static void deleteAccounts(User loginUser, Scanner sc) {
		
		List<User> accountList = UserService.getUserService().allUsers();

		int i = 1;
		for (User u : accountList) {
			if (u.getUserID() == loginUser.getUserID() && u.getUserName().equals(loginUser.getUserName())
					&& u.getPassword().equals(loginUser.getPassword())) {
				System.out.println("Account #" + i + ": Bank Account ID = #" + u.getBankAccountID()
						+ " with current funds = " + u.getAccountStatus());
				i++;
			}
		}

		System.out.println("\nWhich account would you like to delete? Please enter the corresponding Bank Account ID");
		int input = sc.nextInt();

		for (User u : accountList) {
			if (u.getBankAccountID() == input && u.getUserID() == loginUser.getUserID()) {
				if (u.getAccountStatus() != 0) {
					System.out.println(
							"The current funds in this account must be 0 to delete this account. Returning to menu...");
					return;
				} else {
					System.out.println("Bank Account ID #" + u.getBankAccountID() + " has been deleted");
					UserService.getUserService().deleteAccount(input);
					return;
				}
			}
		}
		System.out.println("Bank Account ID #" + input + " was not found. Returning to menu...");

		return;
	}

	public static void updateAccounts(User loginUser, Scanner sc) {
		
		List<User> accountList = UserService.getUserService().allUsers();
		System.out.println();
		int i = 1;
		for (User u : accountList) {
			if (u.getUserID() == loginUser.getUserID() && u.getUserName().equals(loginUser.getUserName())
					&& u.getPassword().equals(loginUser.getPassword())) {
				System.out.println("Account #" + i + ": Bank Account ID = #" + u.getBankAccountID()
						+ " with current funds = " + u.getAccountStatus());
				i++;
			}
		}

		System.out.println(
				"\nWhich account would you like to deposit or withdraw from? Please enter the corresponding Bank Account ID");
		int input = sc.nextInt();
		double change = 0.0;

		for (User u : accountList) {
			if (u.getBankAccountID() == input) {
				System.out.println("\nWhat would you like to do?");
				System.out.println("(1) Deposit");
				System.out.println("(2) Withdraw");
				System.out.println("(3) Return to menu");
				input = sc.nextInt();

				try {
					while (input < 1 || input > 3) {
						System.out.println(i + " is not a valid selection, please try again!");
						input = sc.nextInt();
					}
				} catch (InputMismatchException e) {
					System.err.println("Caught input mismatch error: " + e.getMessage());
				} catch (Exception e) {
					System.err.println("Caught exception: " + e.getMessage());
				}

				while (input != 3) {
					switch (input) {
					case 1:
						System.out.println("\nEnter how much to deposit: ");
						change = sc.nextDouble();
						UserService.getUserService().modifyAccount(u.getBankAccountID(), change);
						System.out.println("$" + change + " successfully deposited!");
						System.out.println("\nFunds currently in bank account #" + u.getBankAccountID() + ": $"
								+ (u.getAccountStatus() + change));
						u.setAccountStatus(u.getAccountStatus() + change);
						break;
					case 2:
						System.out.println("\nEnter how much to withdraw: ");
						change = sc.nextDouble();
						if (change > u.getAccountStatus()) {
							System.out.println("$" + change + " is more than what is available in bank account #"
									+ u.getBankAccountID());
							break;
						}
						UserService.getUserService().modifyAccount(u.getBankAccountID(), -change);
						System.out.println("$" + change + " successfully withdrawn!");
						System.out.println("\nFunds currently in bank account #" + u.getBankAccountID() + ": $"
								+ (u.getAccountStatus() - change));
						u.setAccountStatus(u.getAccountStatus() - change);
						break;
					}
					System.out.println("\nWhat would you like to do?");
					System.out.println("(1) Deposit");
					System.out.println("(2) Withdraw");
					System.out.println("(3) Return to menu");
					input = sc.nextInt();
					try {
						while (input < 1 || input > 3) {
							System.out.println(i + " is not a valid selection, please try again!");
							input = sc.nextInt();
						}
					} catch (InputMismatchException e) {
						System.err.println("Caught input mismatch error: " + e.getMessage());
					} catch (Exception e) {
						System.err.println("Caught exception: " + e.getMessage());
					}
				}

			}
		}

	}
}
