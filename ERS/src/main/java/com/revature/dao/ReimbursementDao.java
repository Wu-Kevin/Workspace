package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public interface ReimbursementDao {
	public Reimbursement insert(Reimbursement reimbursement);
	
	public void update(int transactionid, int managerid, int approval);

	public List<Reimbursement> selectByID(int employeeid);
	
	public List<Reimbursement> selectAll();
}
