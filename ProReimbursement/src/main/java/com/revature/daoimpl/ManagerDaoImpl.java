package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Manager;
import com.revature.dao.ManagerDao;
import com.revature.util.ConnFactory;

public class ManagerDaoImpl implements ManagerDao{
	static {
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ConnFactory cf = ConnFactory.getInstance();
	private String url= "jdbc:postgresql://java2010usf.cgdcd13do7zd.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=reimbursement";
	private String username = "sukanya";
	private String password = "sukanya14";
	
	@Override
	public void createAdmin(Manager a) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "insert into manager values(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, a.getEmployee_ID());
		ps.setString(2, a.getfName());
		ps.setString(3, a.getlName());
		ps.setString(4, a.getEmail());
		ps.setString(5, a.getPassword());
		ps.setString(6, a.getTitle());
		ps.executeUpdate();	
	}

	@Override
	public Manager findAdminById(int employeeID) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from manager where employee_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, employeeID);
		ResultSet rs = ps.executeQuery();
		Manager a = null;
		while(rs.next()) {
			a = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
		}
		return a;
	}

	@Override
	public Manager getAdminByEmail(String email) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from manager where email=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		Manager a = null;
		while(rs.next()) {
			a = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
		}
		return a;
	}

	@Override
	public List<Manager> getAllAdmins() throws SQLException {
		List<Manager> aList = new ArrayList<Manager>();
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from manager";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			aList.add(new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
		}
		return aList;
	}

	@Override
	public void insertDeptLookUp(int customerID, String department) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "insert into dept_lookup values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, customerID);
		ps.setString(2, department);
		ps.executeUpdate();	
		
	}

	@Override
	public String getDepartment(int employeeID) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select department from manager where employee_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, employeeID);
		ResultSet rs = ps.executeQuery();
		String dept = null;
		while(rs.next()) {
			dept = rs.getString(1);
		}
		return dept;
	}

	@Override
	public List<Integer> departmentListLookUp(String department) throws SQLException {
		List<Integer> aList = new ArrayList<Integer>();
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select customer_id from dept_lookup where department=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, department);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			aList.add(rs.getInt(1));
		}
		return aList;
	}

	@Override
	public String getTitle(int employeeID) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select title from manager where employee_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, employeeID);
		ResultSet rs = ps.executeQuery();
		String title = null;
		while(rs.next()) {
			title = rs.getString(1);
		}
		return title;
	}

	@Override
	public int findBlankInApprovalDate(int formID, String title) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from approval_dates where form_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, formID);
		ResultSet rs = ps.executeQuery();
		LocalDate direct = null;
		LocalDate head = null;
		LocalDate benco = null;
		
		while(rs.next()) {
			direct = rs.getObject(3, LocalDate.class);
			head = rs.getObject(4, LocalDate.class);
			benco = rs.getObject(5, LocalDate.class);
		}
		if(direct == null) {
			 return 1;
		}
		if(direct != null && head == null) {
			return 2;
		}
		if(direct != null && head!=null && benco==null) {
			return 3;
		}
		
		return -1;
	}
}
