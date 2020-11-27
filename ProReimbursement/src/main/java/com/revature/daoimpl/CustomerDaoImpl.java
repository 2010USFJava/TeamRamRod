package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Customer;
import com.revature.dao.CustomerDao;
import com.revature.util.ConnFactory;

public class CustomerDaoImpl implements CustomerDao{
	static {
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ConnFactory cf = ConnFactory.getInstance();
	private String url = "jdbc:postgresql://postgres.cyxh07df0zfy.us-west-2.rds.amazonaws.com:5432/postgres?currentSchema=reimbursement";
	private String username = "aquamiguel";
	private String password = "3tyme4be!";
	
	@Override
	public void createCustomer(Customer c) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "insert into customer values(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, c.getEmployeeID());
		ps.setString(2, c.getfName());
		ps.setString(3, c.getlName());
		ps.setString(4, c.getEmail());
		ps.setString(5, c.getPassword());
		ps.executeUpdate();		
	}
	
	@Override
	public Customer getCustomerById(int employeeID) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from customer where customer_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, employeeID);
		ResultSet rs = ps.executeQuery();
		Customer c = null;
		while(rs.next()) {
			c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
		}
		return c;
	}
	
	@Override
	public Customer getCustomerByEmail(String email) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from customer where email=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		Customer c = null;
		while(rs.next()) {
			c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
		}
		return c;
	}
	
	
	@Override
	public List<Customer> getAllCustomers() throws SQLException{
		List<Customer> cList = new ArrayList<Customer>();
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from customer";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			cList.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		return cList;
	}

}
