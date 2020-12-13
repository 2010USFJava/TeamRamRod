package com.revature.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.revature.beans.Form;

public interface FormDao {
	
	public void createForm (Form f) throws SQLException;
	
	public Form findFormByID(int formID) throws SQLException;
	
	public void insertFormIdCustomerLookUp(Form f) throws SQLException;
	
	public List<Integer> findAllFormIDsLookUp(int customerID) throws SQLException;
	
	public int findCustomerIDByFormIDLookUp(int formID) throws SQLException;
	
	public void updateOptional(int formID, String optional) throws SQLException;
	
	public void updateEmail(int formID, boolean email) throws SQLException;
	
	public LocalDate getDate(int formID) throws SQLException;
	
	public List<String> getEmailAndOptional(int formID) throws SQLException;
	
	public List<Integer> getAllFormIDs() throws SQLException;

}
