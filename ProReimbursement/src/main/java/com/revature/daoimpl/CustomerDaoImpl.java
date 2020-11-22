package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Customer;
import com.revature.dao.CustomerDao;
import com.revature.util.ConnFactory;

public class CustomerDaoImpl implements CustomerDao{
	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void createCustomer(Customer c) throws SQLException {
		Connection conn = cf.getConnection();
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
	public Customer findCustomerById(int employeeID) throws SQLException {
		Connection conn = cf.getConnection();
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

}
