package com.revature.model;

public class User {

	private int userID;
	private int bankAccountID;
	private String userName;
	private String password;
	private double accountStatus;
	
	public User() {
		super();
	}
	
	public User(int userID, int bankAccountID, String userName, String password, double accountStatus) {
		super();
		this.userID = userID;
		this.bankAccountID = bankAccountID;
		this.userName = userName;
		this.password = password;
		this.accountStatus = accountStatus;
	}
	
	@Override
	public String toString() {
		return "User [userID=" + userID + ", bankAccountID=" + bankAccountID + ", userName=" + userName + ", password="
				+ password + ", accountStatus=" + accountStatus + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getBankAccountID() {
		return bankAccountID;
	}
	public void setBankAccountID(int bankAccountID) {
		this.bankAccountID = bankAccountID;
	}
	public double getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(double accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	
}
