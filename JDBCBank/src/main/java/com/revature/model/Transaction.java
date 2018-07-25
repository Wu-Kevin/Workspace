package com.revature.model;

public class Transaction {
	private int transaction_id;
	private String transaction_type;
	private double transaction_amount;
	private double balance_start;
	private double balance_end;
	private int bankaccount_id;
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(int transaction_id, String transaction_type, double transaction_amount, double balance_start,
			double balance_end, int bankaccount_id) {
		super();
		this.transaction_id = transaction_id;
		this.transaction_type = transaction_type;
		this.transaction_amount = transaction_amount;
		this.balance_start = balance_start;
		this.balance_end = balance_end;
		this.bankaccount_id = bankaccount_id;
	}
	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", transaction_type=" + transaction_type
				+ ", transaction_amount=" + transaction_amount + ", balance_start=" + balance_start + ", balance_end="
				+ balance_end + ", bankaccount_id=" + bankaccount_id + "]";
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public double getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public double getBalance_start() {
		return balance_start;
	}
	public void setBalance_start(double balance_start) {
		this.balance_start = balance_start;
	}
	public double getBalance_end() {
		return balance_end;
	}
	public void setBalance_end(double balance_end) {
		this.balance_end = balance_end;
	}
	public int getBankaccount_id() {
		return bankaccount_id;
	}
	public void setBankaccount_id(int bankaccount_id) {
		this.bankaccount_id = bankaccount_id;
	}
}