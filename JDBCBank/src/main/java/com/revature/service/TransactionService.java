package com.revature.service;

import java.util.List;

import com.revature.dao.TransactionDAOImplement;
import com.revature.model.Transaction;

public class TransactionService {

	private static TransactionService transService;

	private TransactionService() {
	}

	public static TransactionService getTransService() {
		if (transService == null) {
			transService = new TransactionService();
		}
		return transService;
	}
	
	public static Transaction getTransaction(int transid) {
		return TransactionDAOImplement.getTransDao().getTransaction(transid);
	}

	public static List<Transaction> getTransactionUser(int bankid) {
		return TransactionDAOImplement.getTransDao().getTransactionUser(bankid);
	}

	public static boolean insertTransaction(String type, double amount, double start, double end, int bankid) {
		return TransactionDAOImplement.getTransDao().insertTransaction(type, amount, start, end, bankid);
	}

	public static boolean deleteTransaction(int transid) {
		return TransactionDAOImplement.getTransDao().deleteTransaction(transid);
	}

	public static boolean deleteTransactionUser(int bankid) {
		return TransactionDAOImplement.getTransDao().deleteTransactionUser(bankid);
	}
	public static List<Transaction> allTransactions() {
		return TransactionDAOImplement.getTransDao().allTransactions();
	}
}

