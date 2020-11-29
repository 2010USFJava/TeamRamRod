package com.revature.beans;

public class Customer {
	private int employeeID;
	private String fName;
	private String lName;
	private String email;
	private String password;
	private double tuition;
	
	public Customer() {
		super();
	}
	
	public Customer(int employeeID, String fName, String lName, String email, String password, double tuition) {
		super();
		this.employeeID = employeeID;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.tuition = tuition;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
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

	public double getTuition() {
		return tuition;
	}

	public void setTuition(double tuition) {
		this.tuition = tuition;
	}

	@Override
	public String toString() {
		return "Customer [employeeID=" + employeeID + ", fName=" + fName + ", lName=" + lName + ", email=" + email
				+ ", password=" + password + ", tuition=" + tuition + "]";
	}

}
