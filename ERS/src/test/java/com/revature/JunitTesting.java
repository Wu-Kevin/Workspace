package com.revature;

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

import com.revature.model.Employee;
import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;

public class JunitTesting {

		@Test
		public void checkLoginFalse()  {
			Employee e = new Employee ();
			assertTrue(EmployeeService.getEmployeeService().loginEmployee(e)!=null);
		}
		
		@Test
		public void checkSelectFalse()  {
			Employee e = new Employee(10000, "test", "test", "test", "test", 0);
			assertTrue(EmployeeService.getEmployeeService().loginEmployee(e)!=null);
		}
		
		@Test 
		public void updateImpossible()  {
			Employee e = new Employee(1000000, "test", "test", "test", "test", 0);
			EmployeeService.getEmployeeService().updateEmployee(e);
		}
		
		@Test
		public void retriveAllEmployeesIsTrue()  {
			assertTrue(EmployeeService.getEmployeeService().listEmployees()!=null);
		}
		
		@Test 
		public void registerEmployeeNotUnique() {
			Employee e = new Employee(1000, "test", "test", "test", "test", 1);
			EmployeeService.getEmployeeService().registerEmployee(e);
		}
		
		@Test
		public void checkReimbursementSelectFalse()  {
			assertTrue(ReimbursementService.getReimbursementService().selectByID(123)!=null);
		}
		
		@Test
		public void retrieveAllFunction()  {
			assertTrue(ReimbursementService.getReimbursementService().selectAll()!=null);
		}
		
		@Test 
		public void changeReimbursementImpossible()  {
			ReimbursementService.getReimbursementService().changeReimbursement(1,1,1);
		}
		
		
}
