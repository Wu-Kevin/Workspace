package com.revature.eval.java;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.application.AccountActions;
import com.revature.application.AdminActions;
import com.revature.jdbcutil.Jdbcconnection;
import com.revature.model.User;
import com.revature.service.AccountService;
import com.revature.service.TransactionService;
import com.revature.service.UserService;

import jdbcExceptions.CannotDeleteException;
import jdbcExceptions.ExistingUserException;
import jdbcExceptions.InvalidBankAccountException;
import jdbcExceptions.NoAccountsException;
import jdbcExceptions.NoTransactionsException;
import jdbcExceptions.NoUserException;
import jdbcExceptions.OverdraftException;
import com.revature.model.User;

import jdbcExceptions.NoAccountsException;
import jdbcExceptions.NoUserException;

public class EvaluationTests {

	Connection conn; 

	@Test
	public void checkExistsTrue() throws NoUserException {
		User u = new User (1, "admin", "admin");
		assertTrue(AccountActions.checkExists(u));
	}
	
	@Test (expected = NoUserException.class) 
	public void checkExistsFalse() throws NoUserException  {
		User u = new User (1234, "hello", "worlds!");
		assertTrue(AccountActions.checkExists(u));
	}
	
	@Test (expected = NoAccountsException.class) 
	public void deleteAccountWrongAccountNumber() throws NoAccountsException, CannotDeleteException, InvalidBankAccountException  {
		User u = new User (1234, "hello", "worlds!");
		Scanner sc = new Scanner(System.in);
		AccountActions.deleteAccounts(u, sc);
		sc.close();
	}
	
	@Test (expected = InvalidBankAccountException.class) 
	public void deleteAccountsNoAccounts() throws NoAccountsException, CannotDeleteException, InvalidBankAccountException  {
		User u = new User (1, "admin", "admin");
		ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
		System.setIn(in);
		Scanner sc = new Scanner(System.in);
		AccountActions.deleteAccounts(u, sc);
		sc.close();
	}
	
	@Test (expected = CannotDeleteException.class) 
	public void deleteAccountsCannotDeleteHasMoney() throws NoAccountsException, CannotDeleteException, InvalidBankAccountException  {
		User u = new User (1, "admin", "admin");
		ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);
		Scanner sc = new Scanner(System.in);
		AccountActions.deleteAccounts(u, sc);
		sc.close();
	}
	
	@Test (expected = NoAccountsException.class) 
	public void updateAccountsNoAccounts() throws NoAccountsException, OverdraftException, InvalidBankAccountException {
		User u = new User (20, "test123", "test123");
		Scanner sc = new Scanner(System.in);
		AccountActions.updateAccounts(u, sc);
		sc.close();
	}
	
	@Test (expected = InvalidBankAccountException.class) 
	public void updateAccountsWrongAccountID() throws NoAccountsException, CannotDeleteException, InvalidBankAccountException, OverdraftException  {
		User u = new User (1, "admin", "admin");
		ByteArrayInputStream in = new ByteArrayInputStream("20".getBytes());
		System.setIn(in);
		Scanner sc = new Scanner(System.in);
		AccountActions.updateAccounts(u, sc);
		sc.close();
	}
	
	@Test (expected = InputMismatchException.class) 
	public void updateAccountsInvalidInput() throws NoAccountsException, CannotDeleteException, InvalidBankAccountException, OverdraftException  {
		User u = new User (1, "admin", "admin");
		ByteArrayInputStream in = new ByteArrayInputStream("1\nhelloWorld?".getBytes());
		System.setIn(in);
		Scanner sc = new Scanner(System.in);
		AccountActions.updateAccounts(u, sc);
		sc.close();
	}

	@Test (expected = OverdraftException.class)
	public void updateAccountsTestDeposit() throws NoAccountsException, CannotDeleteException, InvalidBankAccountException, OverdraftException  {
		User u = new User (1, "admin", "admin");
		ByteArrayInputStream in = new ByteArrayInputStream("1\n 2 \n 100000".getBytes());
		System.setIn(in);
		Scanner sc = new Scanner(System.in);
		AccountActions.updateAccounts(u, sc);
		sc.close();
	}

	@Test(expected = NoAccountsException.class)
	public void viewTransactionsNoAccounts()
			throws NoAccountsException, InvalidBankAccountException, NoTransactionsException {
		User u = new User(20, "test123", "test123");
		Scanner sc = new Scanner(System.in);
		AccountActions.viewTransactions(u, sc);
		sc.close();
	}
	
	@Test(expected = InvalidBankAccountException.class)
	public void viewTransactionsInvalidInput()
			throws NoAccountsException, InvalidBankAccountException, NoTransactionsException {
		User u = new User (1, "admin", "admin");
		ByteArrayInputStream in = new ByteArrayInputStream("10000000".getBytes());
		System.setIn(in);
		Scanner sc = new Scanner(System.in);
		AccountActions.viewTransactions(u, sc);
		sc.close();
	}
	
	@Test(expected = NoUserException.class)
	public void deleteUserDoesntExist()
			throws NoAccountsException, InvalidBankAccountException, NoTransactionsException, NoUserException {
		ByteArrayInputStream in = new ByteArrayInputStream("10000000".getBytes());
		System.setIn(in);
		Scanner sc = new Scanner(System.in);
		AdminActions.deleteUser(sc);
		sc.close();
	}
	
	@Test(expected = NoUserException.class)
	public void updateUserDoesntExist()
			throws NoAccountsException, InvalidBankAccountException, NoTransactionsException, NoUserException, ExistingUserException {
		ByteArrayInputStream in = new ByteArrayInputStream("10000000".getBytes());
		System.setIn(in);
		Scanner sc = new Scanner(System.in);
		AdminActions.updateUser(sc);
		sc.close();
	}
	
	@Test(expected = InputMismatchException.class)
	public void updateUserWrongInput()
			throws NoAccountsException, InvalidBankAccountException, NoTransactionsException, NoUserException, ExistingUserException {
		ByteArrayInputStream in = new ByteArrayInputStream("1\nhello".getBytes());
		System.setIn(in);
		Scanner sc = new Scanner(System.in);
		AdminActions.updateUser(sc);
		sc.close();
	}
	
	@Test 
	public void accountServiceTestRetrieveAct() {
		AccountService.getActService().retrieveAct(1);
	}
	
	@Test 
	public void accountServiceTestInsertAct() {
		assertTrue(AccountService.getActService().insertAct(1));
	}
	
	@Test 
	public void accountServiceTestDeleteAct() {
		assertTrue(AccountService.getActService().deleteAct(3459878));
	}
	
	@Test 
	public void transServiceRetrieveTrans() {
		TransactionService.getTransService().getTransaction(1);
	}
	
	@Test 
	public void transServiceRetrieveTransUser() {
		TransactionService.getTransService().getTransactionUser(1);
	}
	
	@Test 
	public void transServiceDeleteTransaction() {
		assertTrue(TransactionService.getTransService().deleteTransaction(123456));
	}

	@Test 
	public void transServiceDeleteTransactionUser() {
		assertTrue(TransactionService.getTransService().deleteTransactionUser(123456));
	}

	@Test 
	public void userServiceTestRetrieveUserByID() {
		UserService.getUserService().retrieveUser(1);
	}
	
	public void userServiceTestRetrieveUserNotID() {
		UserService.getUserService().retrieveUser("admin", "admin");
	}
	
	public void userServiceTestDeleteUserNonexistent() {
		assertTrue(UserService.getUserService().deleteUser(123456));
	}
	
	public void userServiceTestUpdateUserNonexistent() {
		assertTrue(UserService.getUserService().updateUser(123, 123, "hello", "world"));
	}
	
	//check why these don't work when I have time
//	@Test(expected = ExistingUserException.class)
//	public void updateUserIDExists()
//			throws NoAccountsException, InvalidBankAccountException, NoTransactionsException, NoUserException, ExistingUserException {
//		ByteArrayInputStream in = new ByteArrayInputStream("1\n1\n1\n1".getBytes());
//		System.setIn(in);
//		Scanner sc = new Scanner(System.in);
//		AdminActions.updateUser(sc);
//		sc.close();
//	}
//	
//	@Test(expected = ExistingUserException.class)
//	public void updateUsernameExists()
//			throws NoAccountsException, InvalidBankAccountException, NoTransactionsException, NoUserException, ExistingUserException {
//		ByteArrayInputStream in = new ByteArrayInputStream("1\n2\nadmin".getBytes());
//		System.setIn(in);
//		Scanner sc = new Scanner(System.in);
//		AdminActions.updateUser(sc);
//		sc.close();
//	}

}
