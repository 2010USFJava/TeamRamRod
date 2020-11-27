package com.revature.dao;

import java.sql.SQLException;

import com.revature.beans.Manager;

public interface ManagerDao {
	public void createManager(Manager m) throws SQLException;
	
	public Manager findManagerById(int employeeID) throws SQLException;
}
