//talk about how restriction is removed for admin by simply removing a if statement check for userid
package com.revature.application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.model.User;
import com.revature.service.UserService;

import jdbcExceptions.NoUserException;

public class AdminActions {

	public static void adminInterface(Scanner sc) {
		System.out.println("\nWhat would you like to do? Please select a number");
		System.out.println("(1). View all existing users");
		System.out.println("(2). Create an user");
		System.out.println("(3). Delete an user");
		System.out.println("(4). Update a user");
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
				try {
					viewUsers();
				} catch (NoUserException e) {
					System.out.println(e);
				}
				break;
			case 2:
				createUser(sc);
				break;
			case 3:
				try {
				deleteUser(sc);
				} catch (NoUserException e) {
					System.out.println(e);
				}
				break;
			case 4:
				try {
					updateUser(sc);
				} catch (NoUserException e) {
					System.out.println(e);
				}
				break;
			}
			System.out.println("\nWhat would you like to do? Please select a number");
			System.out.println("(1). View all existing users");
			System.out.println("(2). Create an user");
			System.out.println("(3). Delete an user");
			System.out.println("(4). Update a user");
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

	public static void viewUsers() throws NoUserException {
		List<User> userList = UserService.getUserService().allUsers();

		int i = 1;
		for (User u : userList) {
				System.out.println("Account i" + i + ": User ID = #" + u.getUserID()
						+ " with username = " + u.getUserName()
						+ " and password = " + u.getPassword());
				i++;
		}
		if (i == 1) {
			throw new NoUserException("\nThere are no users to view");
		}
	}

	public static void createUser(Scanner sc) {
		
		User newUser = new User();
		
		System.out.println("\nCreate a username: ");
		newUser.setUserName(sc.next());
		System.out.println("The username is: " + newUser.getUserName());
		System.out.println("\nCreate a password: ");
		newUser.setPassword(sc.next());
		System.out.println("The password is: " + newUser.getPassword());
		
		// what happens if this user exists in the database?
		try {
			AccountActions.checkExists(newUser);
			System.out.println("\nThis username and password cominbation already exists.");
			System.out.println("Returning to menu...");
			return;
		} catch (NoUserException e) {
			UserService.getUserService().insertUser(newUser.getUserName(), newUser.getPassword());
			System.out.println("The account has been created!");
		}
	}

	public static void deleteUser(Scanner sc) throws NoUserException {

		List<User> userList = UserService.getUserService().allUsers();

		int i = 1;
		for (User u : userList) {
			System.out.println("Account i" + i + ": User ID = #" + u.getUserID() + " with username = " + u.getUserName()
					+ " and password = " + u.getPassword());
			i++;
		}
		if (i == 1) {
			throw new NoUserException("\nThere are no users to delete");
		}

		System.out.println("\nWhich user would you like to delete? Please enter the corresponding User ID");
		int input = sc.nextInt();

		for (User u : userList) {
			if (u.getUserID() == input) {
				// delete cascade
				UserService.getUserService().deleteUser(input);
				System.out.println("User ID #" + u.getUserID() + " has been deleted");
				return;
			}
		}
		throw new NoUserException("\nUser ID #" + input + " was not found. Returning to menu...");
	}

	public static void updateUser(Scanner sc) throws NoUserException {
		
		List<User> userList = UserService.getUserService().allUsers();

		int i = 1;
		for (User u : userList) {
			System.out.println("Account i" + i + ": User ID = #" + u.getUserID() + " with username = " + u.getUserName()
					+ " and password = " + u.getPassword());
			i++;
		}

		System.out.println(
				"\nWhich user would you like to edit? Please enter the corresponding User ID");
		int input = sc.nextInt();

		
		for (User u : userList) {
			if (u.getUserID() == input) {
				System.out.println("\nWhat would you like to do?");
				System.out.println("(1) Edit userID");
				System.out.println("(2) Edit username");
				System.out.println("(3) Edit password");
				System.out.println("(4) Return to menu");
				
				int menu = sc.nextInt();

				try {
					while (menu < 1 || menu > 4) {
						System.out.println(i + " is not a valid selection, please try again!");
						menu = sc.nextInt();
					}
					switch (menu) {
					case 1:
						System.out.println("\nEnter the new User ID");
						int changeID = sc.nextInt();
						u.setUserID(changeID);
						UserService.getUserService().updateUser(input, u.getUserID(), u.getUserName(), u.getPassword());
						System.out.println("\nUser ID has been successfully updated");
						break;
						//cannot be an existing userID IMPLEMENT
					case 2:
						System.out.println("\nEnter the new Username ");
						String changeUserName = sc.next();
						u.setUserName(changeUserName);
						UserService.getUserService().updateUser(input, u.getUserID(), u.getUserName(), u.getPassword());
						System.out.println("\nUser ID has been successfully updated");
						break;
						//cannot be an existing username IMPLEMENT
					case 3:
						System.out.println("\nEnter the new Password ");
						String changePassword = sc.next();
						u.setPassword(changePassword);
						UserService.getUserService().updateUser(input, u.getUserID(), u.getUserName(), u.getPassword());
						System.out.println("\nUser ID has been successfully updated");
						break;

					case 4:
						return;
					}
				} catch (InputMismatchException e) {
					System.err.println("Caught input mismatch error: " + e.getMessage());
				} catch (Exception e) {
					System.err.println("Caught exception: " + e.getMessage());
				}


			}
			else {
				throw new NoUserException("\nUser ID #" + input + " not found. Returning to menu...");
			}
		}
		
	}
}



