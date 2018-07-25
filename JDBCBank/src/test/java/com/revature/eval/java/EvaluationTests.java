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
import com.revature.jdbcutil.Jdbcconnection;
import com.revature.model.User;

import jdbcExceptions.CannotDeleteException;
import jdbcExceptions.InvalidBankAccountException;
import jdbcExceptions.NoAccountsException;
import jdbcExceptions.NoUserException;
import jdbcExceptions.OverdraftException;

public class EvaluationTests {

	private static final AccountActions accountActions = new AccountActions();
	Connection conn; 

	
	//test 
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
	public void deleteAccountNoAccounts() throws NoAccountsException, CannotDeleteException, InvalidBankAccountException  {
		User u = new User (1234, "hello", "worlds!");
		Scanner sc = new Scanner(System.in);
		AccountActions.deleteAccounts(u, sc);
		sc.close();
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
//	@Test
//	public void trianglesWithNoEqualSidesAreNotEquilateral() {
//		EvaluationService.Triangle triangle = new EvaluationService.Triangle(5, 4, 6);
//		assertFalse(triangle.isEquilateral());
//	}

}
