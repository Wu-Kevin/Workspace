package com.revature.application;

import java.util.List;
import java.awt.Menu;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.service.AccountService;
import com.revature.service.TransactionService;
import com.revature.service.UserService;

import jdbcExceptions.CannotDeleteException;
import jdbcExceptions.InvalidBankAccountException;
import jdbcExceptions.NoAccountsException;
import jdbcExceptions.NoTransactionsException;
import jdbcExceptions.NoUserException;
import jdbcExceptions.OverdraftException;

public class AccountActions {

	public static boolean checkExists(User loginUser) throws NoUserException {
		List<User> accountList = checkUsers();

		for (User u : accountList) {
			if (u.getUserName().equals(loginUser.getUserName()) && u.getPassword().equals(loginUser.getPassword())) {
				return true;
			}
		}
		throw new NoUserException(
				"Could not find username: " + loginUser.getUserName() + " and password: " + loginUser.getPassword());
	}
	
	public static List<User> checkUsers() {
		return UserService.getUserService().allUsers();
	}
	
	public static List<Account> checkAccounts() {
		return AccountService.getActService().allAccounts();
	}
	
	public static User login(Scanner sc) {
		User loginUser = new User();

		System.out.println("(R)egistered or (U)nregistered user?");
		String input = sc.next();

		try {
			while (!input.equals("Registered") && !input.equals("Unregistered") && !input.equals("registered")
					&& !input.equals("unregistered") && !input.equals("R") && !input.equals("r") && !input.equals("U")
					&& !input.equals("u")) {
				System.out.println("\nInvalid command! Please Type Registered, Unregistered, R, or U");
				input = sc.next();
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}

		switch (input.charAt(0)) {
		case 'R':
		case 'r':
			System.out.println("\nPlease log in to your account!");
			System.out.println("\nUsername: ");
			// what if login is spaces or nothing
			loginUser.setUserName(sc.next());
			System.out.println("Your username is: " + loginUser.getUserName());
			System.out.println("\nPassword: ");
			loginUser.setPassword(sc.next());
			System.out.println("You have entered your password");

			// what happens if this user doesn't exist in the database?
			try {
				checkExists(loginUser);
			} catch (NoUserException e) {
				System.out.println("\n" + e);
				System.out.println("\nPlease make a selection:");
				System.out.println("(1). Retype user information");
				System.out.println("(2). Register account");
				System.out.println("(3). Exit application");
				int i = sc.nextInt();
				try {
					while (i < 1 || i > 3) {
						System.out.println("\n" + i + " is not a valid selection, please try again!");
						i = sc.nextInt();
					}
					switch (i) {
					case 1:
						System.out.println("\nPlease retype your username: ");
						loginUser.setUserName(sc.next());
						System.out.println("\nPlease retype your password: ");
						loginUser.setPassword(sc.next());
						try {
							checkExists(loginUser);
							System.out.println("Account found! Logging in");
							return loginUser = UserService.getUserService().retrieveUser(loginUser.getUserName(),
									loginUser.getPassword());
						} catch (NoUserException err) {
							System.out.println("\n" + err);
							System.out.println("\nWould you like to try again? (Y)es or (N)o:");
							input = sc.next();
							while (input.equals("No") == false	&& input.equals("no") == false && input.equals("n") == false && input.equals("N") == false) {
								System.out.println("\nPlease retype your username: ");
								loginUser.setUserName(sc.next());
								System.out.println("Please retype your password: ");
								loginUser.setPassword(sc.next());
								try {
									checkExists(loginUser);
									System.out.println("Account found! Logging in");
									return loginUser = UserService.getUserService().retrieveUser(loginUser.getUserName(),
											loginUser.getPassword());
								} catch (NoUserException erro) {
									System.out.println("\n" + erro);
									System.out.println("\nWould you like to try again? (Y)es or (N)o:");
									input = sc.next();
								}
							}
							System.out.println("Exiting application.");
							System.exit(0);
						}
					case 2:
						UserService.getUserService().insertUser(loginUser.getUserName(), loginUser.getPassword());
						loginUser = UserService.getUserService().retrieveUser(loginUser.getUserName(),
								loginUser.getPassword());
						System.out.println("Your account has been created! Your unique user ID is #" + loginUser.getUserID());
						return loginUser;

					case 3:
						System.out.println("Exiting application. Thank you for your business!");
						System.exit(0);
					}

				} catch (InputMismatchException er) {
					System.err.println("Caught input mismatch error: " + er.getMessage());
				} catch (Exception er) {
					System.err.println("Caught exception: " + er.getMessage());
				}
			}
			loginUser = UserService.getUserService().retrieveUser(loginUser.getUserName(), loginUser.getPassword());
			return loginUser;

		case 'U':
		case 'u':
			System.out.println("\nPlease register an account!");
			System.out.println("\nCreate a username: ");
			loginUser.setUserName(sc.next());
			System.out.println("Your username is: " + loginUser.getUserName());
			System.out.println("\nCreate a password: ");
			loginUser.setPassword(sc.next());
			System.out.println("You have created your password.");
			
			// what happens if this user exists in the database?
			try {
				checkExists(loginUser);
				System.out.println("\nThis username and password cominbation already exists.");
				System.out.println("Logging in!");
				return loginUser = UserService.getUserService().retrieveUser(loginUser.getUserName(),loginUser.getPassword());
			} catch (NoUserException e) {
				UserService.getUserService().insertUser(loginUser.getUserName(), loginUser.getPassword());
				loginUser = UserService.getUserService().retrieveUser(loginUser.getUserName(),
						loginUser.getPassword());
				System.out.println("\nYour account has been created! Your unique user ID is #" + loginUser.getUserID());
				return loginUser;
			}
				
			
		}
		return null;
	}

	public static void userInterface(User loginUser, Scanner sc) {
		System.out.println("\nWhat would you like to do? Please select a number");
		System.out.println("(1). View existing accounts and balances");
		System.out.println("(2). Create an account");
		System.out.println("(3). Delete an account");
		System.out.println("(4). Deposit/Withdraw from an account");
		System.out.println("(5). View transactions");
		System.out.println("(6). Logout");

		int i = sc.nextInt();

		try {
			while (i < 1 || i > 6) {
				System.out.println(i + " is not a valid selection, please try again!");
				i = sc.nextInt();
			}
		} catch (InputMismatchException e) {
			System.err.println("Caught input mismatch error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Caught exception: " + e.getMessage());
		}

		while (i != 6) {
			switch (i) {
			case 1:
				try {
					viewAccounts(loginUser);
				} catch (NoAccountsException e) {
					System.out.println(e);
				}
				break;
			case 2:
				createAccounts(loginUser);
				break;
			case 3:
				try {
					deleteAccounts(loginUser, sc);
				} catch (NoAccountsException e) {
					System.out.println(e);
				} catch (CannotDeleteException e) {
					System.out.println(e);
				} catch (InvalidBankAccountException e) {
					System.out.println(e);
				}
				break;
			case 4:
				try {
					updateAccounts(loginUser, sc);
				} catch (NoAccountsException e) {
					System.out.println(e);
				} catch (OverdraftException e) {
					System.out.println(e);
				} catch (InvalidBankAccountException e) {
						System.out.println(e);
				}
				break;
			case 5:
				try {
					viewTransactions(loginUser, sc);
				} catch (NoAccountsException e) {
					System.out.println(e);
				} catch (InvalidBankAccountException e) { 
					System.out.println(e);
				} catch (NoTransactionsException e) { 
					System.out.println(e);
				}
			}
			System.out.println("\nWhat would you like to do? Please select a number");
			System.out.println("(1). View existing accounts and balances");
			System.out.println("(2). Create an account");
			System.out.println("(3). Delete an account");
			System.out.println("(4). Deposit/Withdraw from an account");
			System.out.println("(5). View transactions");
			System.out.println("(6). Logout");
			i = sc.nextInt();
			try {
				while (i < 1 || i > 6) {
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

	public static void viewAccounts(User loginUser) throws NoAccountsException{
		List<Account> accountList = checkAccounts();

		int i = 1;
		
		for (Account a : accountList) {
			if (a.getUserID() == loginUser.getUserID()) {
				System.out.println("Account #" + i + ": Bank Account ID = #" + a.getBankAccountID()
						+ " with current balance of $" + a.getAccountStatus());
				i++;
			}
		}
		if (i == 1) {
			throw new NoAccountsException("\nYou have no accounts to view. Returning to menu...");
		}
	}

	public static void createAccounts(User loginUser) {

		// retain same userid when inserting account
		AccountService.getActService().insertAct(loginUser.getUserID());
		System.out.println("Your new account has been created!");
		return;

	}

	public static void deleteAccounts(User loginUser, Scanner sc) throws NoAccountsException, CannotDeleteException, InvalidBankAccountException{
		
		List<Account> accountList = checkAccounts();

		int i = 1;
		for (Account a : accountList) {
			if (a.getUserID() == loginUser.getUserID()) {
				System.out.println("Account #" + i + ": Bank Account ID = #" + a.getBankAccountID()
						+ " with current balance of $" + a.getAccountStatus());
				i++;
			}
		}
		
		if (i == 1) {
			throw new NoAccountsException("\nYou have no accounts to delete. Returning to menu...");			
		}
		
		//what happens if no accounts...
		System.out.println("\nWhich account would you like to delete? Please enter the corresponding Bank Account ID");
		int input = sc.nextInt();

		for (Account a : accountList) {
			if (a.getBankAccountID() == input && a.getUserID() == loginUser.getUserID()) {
				
				if (a.getAccountStatus() != 0) {
					throw new CannotDeleteException ("\nThe current balance in this account must be 0 to delete this account. Returning to menu...");
				} else {
					AccountService.getActService().deleteAct(a.getBankAccountID());
					System.out.println("Bank Account ID #" + a.getBankAccountID() + " has been deleted");
					return;
				}
			}
		}
		throw new InvalidBankAccountException("\nBank Account ID #" + input + " was not found. Returning to menu...");
	}

	public static void updateAccounts(User loginUser, Scanner sc) throws NoAccountsException, OverdraftException, InvalidBankAccountException {
		
		List<Account> accountList = checkAccounts();

		int i = 1;
		for (Account a : accountList) {
			if (a.getUserID() == loginUser.getUserID()) {
				System.out.println("Account #" + i + ": Bank Account ID = #" + a.getBankAccountID()
						+ " with current balance of $" + a.getAccountStatus());
				i++;
			}
		}
		
		if (i == 1) {
			throw new NoAccountsException("\nYou have no accounts to deposit or withdraw from. Returning to menu...");
		}

		System.out.println("\nWhich account would you like to deposit or withdraw from? Please enter the corresponding Bank Account ID");
		int input = sc.nextInt();
		double change = 0.0;

		for (Account a : accountList) {
			if (a.getBankAccountID() == input && a.getUserID() == loginUser.getUserID()) {
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
						AccountService.getActService().updateAccount(a.getBankAccountID(), change);
						TransactionService.getTransService().insertTransaction("deposit", change, a.getAccountStatus(), (a.getAccountStatus() + change), a.getBankAccountID());
						System.out.println("$" + change + " successfully deposited!");
						System.out.println("\nFunds currently in bank account #" + a.getBankAccountID() + ": $"
								+ (a.getAccountStatus() + change));
						a.setAccountStatus(a.getAccountStatus() + change);
						break;
					case 2:
						System.out.println("\nEnter how much to withdraw: ");
						change = sc.nextDouble();
						if (change > a.getAccountStatus()) {
							throw new OverdraftException("\n$" + change + " is more than what is available in bank account #"	+ a.getBankAccountID() + ". Returning to menu...");
						}
						AccountService.getActService().updateAccount(a.getBankAccountID(), -change);
						TransactionService.getTransService().insertTransaction("withdraw", change, a.getAccountStatus(), (a.getAccountStatus() - change), a.getBankAccountID());

						System.out.println("$" + change + " successfully withdrawn!");
						System.out.println("\nFunds currently in bank account #" + a.getBankAccountID() + ": $"
								+ (a.getAccountStatus() - change));
						a.setAccountStatus(a.getAccountStatus() - change);
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
		throw new InvalidBankAccountException("\nNo bank account matches Bank Account ID #" + input);

	}

	public static void viewTransactions(User loginUser, Scanner sc) throws NoAccountsException, InvalidBankAccountException, NoTransactionsException{
		
		List<Transaction> transactionList = TransactionService.getTransService().allTransactions();
		List<Account> accountList = checkAccounts();
		int checkID = 0;
		List<Integer> bankAccountIDList = new ArrayList<Integer>();

		int i = 1;
		for (Account a : accountList) {
			if (a.getUserID() == loginUser.getUserID()) {
				System.out.println("Account #" + i + ": Bank Account ID = #" + a.getBankAccountID()
						+ " with current balance of $" + a.getAccountStatus());
				bankAccountIDList.add(a.getBankAccountID());
				checkID = a.getUserID();
				i++;
			}
		}
		
		if (i == 1) {
			throw new NoAccountsException("\nYou have no accounts to view transactions from. Returning to menu...");			
		}
		
	System.out.println("\nWhich account would you like view the transaction history of?");
	System.out.println("Please enter the corresponding Bank Account ID");
	int input = sc.nextInt();
	
	if (bankAccountIDList.contains(input) == false) {
		throw new InvalidBankAccountException("\nBank Account ID #" + input + " was not found. Returning to menu...");
	}

	i = 1;
	for (Transaction t : transactionList) {
		if (t.getBankaccount_id() == input && checkID == loginUser.getUserID()) {
				System.out.println("Transaction #" + i +
						" || Transaction Account ID #" + t.getTransaction_id() + 
						" || Transaction Type: " + t.getTransaction_type() +
						" || Transaction Amount: " + t.getTransaction_amount() +
						" || Start Balance: " + t.getBalance_start() +
						" || End Balance: " + t.getBalance_end());
				i++;
		}
	}
	
	if (i == 1) {
		throw new NoTransactionsException("\nNo transactions for Bank Account #" + input + " found. Returning to menu...");
	}
	
	return;
	}
}
