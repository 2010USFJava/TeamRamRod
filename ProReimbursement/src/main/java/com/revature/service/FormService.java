package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Form;
import com.revature.controller.CusLoginController;
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
	
	public void insertFormIdLookUp(Form f) {
		try {
			form.insertFormIdCustomerLookUp(f);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateForm(String optional) {
		try {
			int customerID = CusLoginController.currentCustomer.getEmployeeID();
			List<Integer> fList = form.findAllFormIDsLookUp(customerID);
			int temp = 0;
			for(int i: fList) {
				if(i > temp) {
					temp = i;
				}
			}
			System.out.println("temp: " + temp);
			form.updateForm(temp, optional);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
