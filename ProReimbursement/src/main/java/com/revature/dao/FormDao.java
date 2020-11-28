package com.revature.dao;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;

import com.revature.beans.Form;

public interface FormDao {
	
	public void createForm (Form f) throws SQLException;
	
	public Form findFormbyID(int formID) throws SQLException;

}
