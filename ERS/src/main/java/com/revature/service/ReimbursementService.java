package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDaoJdbc;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class ReimbursementService {
	
private static ReimbursementService reimbursementService;
	
	private ReimbursementService() {
		
	}
	
	public static ReimbursementService getReimbursementService() {
		if(reimbursementService == null) {
			reimbursementService = new ReimbursementService();
		}
		return reimbursementService;
	}
	
	public Reimbursement insertReimbursement(Reimbursement reimbursement) {
		return ReimbursementDaoJdbc.getReimbursementDaoJdbc().insert(reimbursement);
	}
	
	public List<Reimbursement> selectByID(int employeeid) {
		return ReimbursementDaoJdbc.getReimbursementDaoJdbc().selectByID(employeeid);
	}
	
	public List<Reimbursement> selectAll() {
		return ReimbursementDaoJdbc.getReimbursementDaoJdbc().selectAll();
	}
	
	public void changeReimbursement(int reimbursementid, int managerid, int approval) {
		ReimbursementDaoJdbc.getReimbursementDaoJdbc().update(reimbursementid, managerid, approval);
	}
}
