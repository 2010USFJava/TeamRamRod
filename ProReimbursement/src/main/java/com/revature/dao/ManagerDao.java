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
}
