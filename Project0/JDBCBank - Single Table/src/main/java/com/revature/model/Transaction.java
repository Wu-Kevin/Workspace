package com.revature.model;

public class Transaction {
	private int transaction_id;
	private int transaction_amount;
	private int bankaccount_id;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(int transaction_id, int transaction_amount, int bankaccount_id) {
		super();
		this.transaction_id = transaction_id;
		this.transaction_amount = transaction_amount;
		this.bankaccount_id = bankaccount_id;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(int transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public int getBankaccount_id() {
		return bankaccount_id;
	}

	public void setBankaccount_id(int bankaccount_id) {
		this.bankaccount_id = bankaccount_id;
	}

	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", transaction_amount=" + transaction_amount
				+ ", bankaccount_id=" + bankaccount_id + "]";
	}

}

//transaction_id number(10) not null,
//transaction_amount number(10) check (transaction_amount >=0 ),
//bankaccount_id number(10) not null,