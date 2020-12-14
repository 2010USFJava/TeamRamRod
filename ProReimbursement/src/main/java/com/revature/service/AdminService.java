package com.revature.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Form;
import com.revature.beans.Manager;
import com.revature.dao.FormDao;
import com.revature.dao.ManagerDao;
import com.revature.daoimpl.FormDaoImpl;
import com.revature.daoimpl.ManagerDaoImpl;

public class AdminService {
	ManagerDao addao = new ManagerDaoImpl();
	FormDao fdao = new FormDaoImpl();
	public static List<Integer> arrayFormDates = null;

	public AdminService() {
	}

	public boolean loginVerify(String email, String password) {
		List<Manager> aList = new ArrayList<Manager>();
		try {
			aList = addao.getAllAdmins();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean verify = false;
		for (Manager ad : aList) {
			if (ad.getEmail().equals(email) && ad.getPassword().equals(password)) {
				verify = true;
			}
		}
		return verify;
	}

	public Manager logGetAdmin(String email, String password) {
		if (loginVerify(email, password)) {
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
			// e.printStackTrace();
		}
	}

	public List<Form> getAdminList(int employeeID) {
		List<Integer> fList = new ArrayList<Integer>();
		List<Integer> returnList = null;
		List<Integer> secondList = null;
		List<Form> allForms = new ArrayList<Form>();
		List<Integer> thirdList = new ArrayList<Integer>();
		List<Integer> fourthList = new ArrayList<Integer>();
		int tempId = 0;
		
		try {
			String title = addao.getTitle(employeeID);
			System.out.println("title: " + title);
			
			if(!title.equals("benco")) {
				String dept = addao.getDepartment(employeeID);
				System.out.println("dept:" + dept);
				List<Integer> cList = addao.departmentListLookUp(dept);
				
				for(int cid: cList) {
					System.out.println("cid from dept_lookup: " + cid);
					fList = fdao.findAllFormIDsLookUp(cid);
					for(int f: fList) {
						thirdList.add(f);
					}
					System.out.println(thirdList);
				}
			} else { //benco
				List<Integer> allIds = fdao.getAllFormIDs();
				int temp = 0;
				for(int a: allIds) {
					if(addao.findBlankInApprovalDate(a, title) == 3) {
						fourthList.add(a);
						System.out.println("benco id:" + a);
					}
				}
			}
			
			if(title.equals("direct supervisor")) {
				for(int fid: thirdList) {
					System.out.println("fid from ds: " + fid);
					if(addao.findBlankInApprovalDate(fid, title) == 1) {
						fourthList.add(fid);
					}
				}
			}
				
			if(title.equals("department head")) {
				for(int fid: thirdList) {
					System.out.println("fid from dh: " + fid);
					if(addao.findBlankInApprovalDate(fid, title) == 2) {
						fourthList.add(fid);
					}
				}
			}
			List<Integer> tempList = new ArrayList<Integer>();
			if(title.equals("superhead")) {
				for(int fid: thirdList) {
					String dsTitle = "direct supervisor";
					if(addao.findBlankInApprovalDate(fid, dsTitle) == 1) {
						System.out.println("in superhead DS: " + fid);
						fourthList.add(fid);
					}
				}
				for(int x: thirdList) {
					String dhTitle = "department head";
					if(addao.findBlankInApprovalDate(x, dhTitle) == 2) {
					System.out.println("in superhead dh: " + tempId);
					for(int fid: fourthList) {
						if(fid != x) {
							tempList.add(x); 
							System.out.println("ids in superhead DH temp list: " + x);
						}
					}
					for(int t: tempList) {
						fourthList.add(t);
					}
				}
			}
		}
			
			arrayFormDates = fourthList;
			
			for(int fid: fourthList) {
				System.out.println("all form num that match empty: " + fid);
				Form f = fdao.findFormByID(fid); 
				allForms.add(f);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(allForms);
			
		return allForms;
		}
	
	public List<Form> getInitialFormList(){
		List<Integer> approvedIDs = null;
		List<Form> approvedForms = new ArrayList<Form>();
		try {
			approvedIDs = addao.getInitialApproved();
			List<Integer> trueList = addao.getFinalDecisionTrue(); //check!
			for(int a: approvedIDs) {
				for(int t: trueList) {
					if(a != t) {
						approvedForms.add(fdao.findFormByID(a));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return approvedForms;
	}
	
	public List<Integer> getInitialIDs(){
		List<Integer> approvedIDs = null;
		try {
			approvedIDs = addao.getInitialApproved();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return approvedIDs;
	}
	
	public void addFormIdToFinalApproval(int formID) {
		try {
			addao.addFinalApproval(formID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}


