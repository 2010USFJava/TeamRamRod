package com.revature.service;

import java.sql.SQLException;

import com.revature.beans.Form;
import com.revature.dao.FormDao;
import com.revature.daoimpl.FormDaoImpl;

public class FormService {
	FormDao form = new FormDaoImpl();
	
	public Form getForm(int formID) {
		System.out.println("inside getForm");
		try {
			return form.findFormByID(formID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertNewForm(Form f) {
		try {
			form.createForm(f);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


}
