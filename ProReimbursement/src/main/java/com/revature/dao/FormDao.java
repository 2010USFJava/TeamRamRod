package com.revature.dao;

import java.sql.SQLException;

import com.revature.beans.Customer;
import com.revature.beans.Form;

public interface FormDao {
	
	public void createForm (Form f) throws SQLException;
	
	public Form findFormByID(int formID) throws SQLException;
	
	public void insertFormIdCustomerLookUp(Form f) throws SQLException;
	
	public int findFormIDByCustomerIDLookUp(int customerID) throws SQLException;
	
	public int findCustomerIDByFormIDLookUp(int formID) throws SQLException;

}
