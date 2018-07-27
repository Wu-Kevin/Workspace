package com.revature.dao;

import java.util.List;

import com.revature.model.Account;

public interface AccountDAO {
	public Account getAccount(int bankid);
	
	public boolean insertAccount(int userid);
	
	public boolean deleteAccount(int bankid); 
	
	public List<Account> allAccounts();
	
	public boolean updateAccount(int bankid, double change);
	
}
