package com.revature.dao;

import java.util.List;

import com.revature.model.Transaction;

public interface TransactionDAO {
	public Transaction getTransaction(int transid);

	public List<Transaction> getTransactionUser(int bankid);

	public boolean insertTransaction(String type, double amount, double start, double end, int bankid);

	public boolean deleteTransaction(int transid);

	public boolean deleteTransactionUser(int bankid);
	
	public List<Transaction> allTransactions();

}

//create table transactions (
//	    transaction_id number(10) not null,
//	    transaction_amount number(10) check (transaction_amount >=0 ),
//	    bankaccount_id number(10) not null,
//	    primary key (transaction_id),
//	    constraint transaction_bankAccountFK foreign key (bankaccount_id) references bankAccount(bankaccount_id)
//	);
