package com.revature.beans;

public class Manager {
	public int employee_ID;
	public String fName;
	public String lName;
	public String email;
	public String password;
	public String title;
	
	public Manager() {
		super();
	}

	public Manager(int employee_ID, String fName, String lName, String email, String password, String title) {
		super();
		this.employee_ID = employee_ID;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.title = title;
	}

	public int getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(int employee_ID) {
		this.employee_ID = employee_ID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Manager [employee_ID=" + employee_ID + ", fName=" + fName + ", lName=" + lName + ", email=" + email
				+ ", password=" + password + ", title=" + title + "]";
	}
}
