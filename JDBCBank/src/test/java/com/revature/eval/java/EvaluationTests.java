package com.revature.eval.java;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.application.AccountActions;
import com.revature.model.User;

import jdbcExceptions.NoAccountsException;
import jdbcExceptions.NoUserException;

public class EvaluationTests {

	private static final AccountActions accountActions = new AccountActions();

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
	public void deleteAccountNoAccounts() throws NoAccountsException  {
		User u = new User (1234, "hello", "worlds!");
		assertTrue(AccountActions.deleteAccounts(u, new Scanner sc));
	}
	
//	public void basic() {
//		final String phrase = "Portable Network Graphics";
//		final String expected = "PNG";
//		assertEquals(expected, evaluationService.acronym(phrase));
//	}
//	@Test
//	public void trianglesWithNoEqualSidesAreNotEquilateral() {
//		EvaluationService.Triangle triangle = new EvaluationService.Triangle(5, 4, 6);
//		assertFalse(triangle.isEquilateral());
//	}

}
