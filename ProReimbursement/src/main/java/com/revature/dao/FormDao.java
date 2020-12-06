package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Form;

public interface FormDao {
	
	public void createForm (Form f) throws SQLException;
	
	public Form findFormByID(int formID) throws SQLException;
	
	public void insertFormIdCustomerLookUp(Form f) throws SQLException;
	
	public List<Integer> findAllFormIDsLookUp(int customerID) throws SQLException;
	
	public int findCustomerIDByFormIDLookUp(int formID) throws SQLException;
	
	public void updateForm(int formID, String optional) throws SQLException;

}
