package com.revature.dao;

import java.sql.SQLException;

import com.revature.beans.ApprovalDates;

public interface ApprovalDatesDao {
	public void createApprovalDate(ApprovalDates ad) throws SQLException;
	
	public ApprovalDates getApprovalDate(int formID) throws SQLException;
	
	public void updateApprovalDate(int formID) throws SQLException;

}
