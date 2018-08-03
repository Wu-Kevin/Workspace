package com.revature.model;

public class Reimbursement {
	private int reimbursementid;
	private int employeeId;
	private double amount;
	private String rationale;
	private int approval;
	private int approvalid;

	@Override
	public String toString() {
		return "Reimbursement [reimbursementid=" + reimbursementid + ", employeeId=" + employeeId + ", amount=" + amount
				+ ", rationale=" + rationale + ", approval=" + approval + ", approvalid=" + approvalid + "]";
	}

	
	public Reimbursement(int reimbursementid, int employeeId, double amount, String rationale, int approval,
			int approvalid) {
		super();
		this.reimbursementid = reimbursementid;
		this.employeeId = employeeId;
		this.amount = amount;
		this.rationale = rationale;
		this.approval = approval;
		this.approvalid = approvalid;
	}

	public Reimbursement() {
		super();
		this.reimbursementid = 0;
		this.employeeId = 0;
		this.amount = 0.0;
		this.rationale = null;
		this.approval = 0;
		this.approvalid = 0;
	}
	
	public int getReimbursementid() {
		return reimbursementid;
	}

	public void setReimbursementid(int reimbursementid) {
		this.reimbursementid = reimbursementid;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
	}

	public int getApproval() {
		return approval;
	}

	public void setApproval(int approval) {
		this.approval = approval;
	}

	public int getApprovalid() {
		return approvalid;
	}

	public void setApprovalid(int approvalid) {
		this.approvalid = approvalid;
	}

}