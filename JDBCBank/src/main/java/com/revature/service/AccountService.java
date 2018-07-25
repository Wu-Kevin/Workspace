package com.revature.service;

import java.util.List;

import com.revature.dao.AccountDAOImplement;
import com.revature.model.Account;

public class AccountService {
	private static AccountService actService;

	private AccountService() {
	}

	public static AccountService getActService() {
		if (actService == null) {
			actService = new AccountService();
		}
		return actService;
	}

	public static Account retrieveAct(int bankid) {
		return AccountDAOImplement.getActDao().getAccount(bankid);
	}

	public static boolean insertAct(int userid) {
		return AccountDAOImplement.getActDao().insertAccount(userid);
	}

	public static boolean deleteAct(int bankid) {
		return AccountDAOImplement.getActDao().deleteAccount(bankid);
	}

	public static List<Account> allAccounts() {
		return AccountDAOImplement.getActDao().allAccounts();
	}

	public static boolean updateAccount(int bankid, double change) {
		return AccountDAOImplement.getActDao().updateAccount(bankid, change);
	}
}
