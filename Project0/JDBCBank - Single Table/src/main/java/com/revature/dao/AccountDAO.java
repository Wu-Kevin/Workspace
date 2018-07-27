package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface AccountDAO {
	public Account getAccount(String username, String password);
	
	public User getUser(int userid);
	
	public boolean insertUser (String username, String password);
	
	public boolean insertUser(int userid, String username, String password);
	
	public boolean deleteUser(int bankid); 
	
	public List<User> allUsers();
	
	public boolean updateUser(int bankid, double change);
	
}

//create table bankAccount (
//	    bankaccount_id number(10) not null,
//	    user_id number(10) not null,
//	    account number(10) check (account >=0 ),
//	    primary key (bankaccount_id),
//	    foreign key (user_id) references userInformation(user_id)
//	    );