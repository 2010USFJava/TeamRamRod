package com.revature.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Manager;
import com.revature.dao.ManagerDao;
import com.revature.daoimpl.ManagerDaoImpl;

public class AdminService {
	ManagerDao addao = new ManagerDaoImpl();
	
	public AdminService() {}
	
	public boolean loginVerify(String email, String password) {
		List<Manager> aList = new ArrayList<Manager>();
		try {
			aList = addao.getAllAdmins();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean verify = false;
		for(Manager ad: aList) {
			if(ad.getEmail().equals(email) && ad.getPassword().equals(password)) {
				verify = true;
			}
		}
		return verify;
	}
	
	public Manager logGetAdmin(String email, String password) {
		if(loginVerify(email, password)) {
			try {
				return addao.getAdminByEmail(email);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void insertNewAdmin(Manager ad) {
		try {
			addao.createAdmin(ad);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addDepartment(int customerID, String department) {
		try {
			addao.insertDeptLookUp(customerID, department);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
