package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Manager;

public interface ManagerDao {
	public void createAdmin(Manager a) throws SQLException;
	
	public Manager findAdminById(int employeeID) throws SQLException;

	public Manager getAdminByEmail(String email) throws SQLException;
	
	public List<Manager> getAllAdmins() throws SQLException;
	
	public void insertDeptLookUp(int customerID, String department) throws SQLException;
	
	public String getDepartment(int employeeID) throws SQLException;
	
	public List<Integer> departmentListLookUp(String department) throws SQLException;
	
	public String getTitle(int employeeID) throws SQLException;
	
	public int findBlankInApprovalDate(int formID, String title) throws SQLException;
	
	public List<Integer> getInitialApproved() throws SQLException;
	
	public void addFinalApproval(int formID) throws SQLException;
	
	public List<Integer> getFinalDecisionTrue() throws SQLException;
	
	public boolean getFinalApprovalInFinalTrue(int formID) throws SQLException;
	
	public boolean getFinalApprovalInFinalFalse(int formID) throws SQLException;
} 
