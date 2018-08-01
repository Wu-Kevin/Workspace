package com.revature.model;

public class Employee {
	private int employeeId;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private int ismanager;

	public Employee(String password, String firstname, String lastname, String email, int ismanager) {
		super();
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.ismanager = ismanager;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsmanager() {
		return ismanager;
	}

	public void setIsmanager(int ismanager) {
		this.ismanager = ismanager;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", ismanager=" + ismanager + "]";
	}

}
