package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface TransactionDAO {
	public User getUser(String username, String password);
	
	public User getUser(int userid);
	
	public boolean insertUser (String username, String password);
	
	public boolean insertUser(int userid, String username, String password);
	
	public boolean deleteUser(int bankid); 
	
	public List<User> allUsers();
	
	public boolean updateUser(int bankid, double change);
	
}

//create table transactions (
//	    transaction_id number(10) not null,
//	    transaction_amount number(10) check (transaction_amount >=0 ),
//	    bankaccount_id number(10) not null,
//	    primary key (transaction_id),
//	    constraint transaction_bankAccountFK foreign key (bankaccount_id) references bankAccount(bankaccount_id)
//	);
