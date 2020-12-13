package com.revature.dao;

import java.sql.SQLException;
import java.time.LocalDate;

import com.revature.beans.ApprovalDates;

public interface ApprovalDatesDao {
	public void createApprovalDate(ApprovalDates ad) throws SQLException;
	
	public ApprovalDates getApprovalDate(int formID) throws SQLException;
	
	public void enterFirstDate(int formID) throws SQLException;
	
	public void updateApprovalDate(int formID, String title) throws SQLException;
	
	public void approvalInitial(int formID, boolean decision) throws SQLException;
	
	public double getReimbursement(int formID) throws SQLException;
	
	public void updateReimbursement(int formID, double reimburse) throws SQLException;

}
