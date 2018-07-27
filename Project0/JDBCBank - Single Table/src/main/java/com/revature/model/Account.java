package com.revature.model;

public class Account {
	private int bankAccountID;
	private int userID;
	private double accountStatus;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int bankAccountID, int userID, double accountStatus) {
		super();
		this.bankAccountID = bankAccountID;
		this.userID = userID;
		this.accountStatus = accountStatus;
	}

	@Override
	public String toString() {
		return "Account [bankAccountID=" + bankAccountID + ", userID=" + userID + ", accountStatus=" + accountStatus
				+ "]";
	}

	public int getBankAccountID() {
		return bankAccountID;
	}

	public void setBankAccountID(int bankAccountID) {
		this.bankAccountID = bankAccountID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(double accountStatus) {
		this.accountStatus = accountStatus;
	}

}
