package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

import com.revature.beans.ApprovalDates;
import com.revature.controller.CusLoginController;
import com.revature.dao.ApprovalDatesDao;
import com.revature.service.CustomerService;

public class ApprovalDatesDaoImpl implements ApprovalDatesDao {
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
	
	private String url= "jdbc:postgresql://java2010usf.cgdcd13do7zd.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=reimbursement";
	private String username = "sukanya";
	private String password = "sukanya14";
	
	CustomerService cServ = new CustomerService();
	
	@Override
	public void createApprovalDate(ApprovalDates ad) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "insert into approval_dates values(default,?,?,?,?,?,?)";
		double reimbursement = cServ.calculateReimbursementByEventNum(CusLoginController.currentForm.getCost(), CusLoginController.currentForm.getEventNum());
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1,todayLocalDate);
		ps.setObject(2, ad.getdSuperApproval());
		ps.setObject(3, ad.getdHeadApproval());
		ps.setObject(4, ad.getBenCoApproval());
		ps.setBoolean(5, ad.isApproved());
		ps.setDouble(6, reimbursement);
		ps.executeUpdate();		
	}

	@Override
	public ApprovalDates getApprovalDate(int formID) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select * from customer where customer_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, formID);
		ResultSet rs = ps.executeQuery();
		ApprovalDates ad = null;
		while(rs.next()) {
			ad = new ApprovalDates(rs.getInt(1), rs.getObject(2, LocalDate.class), rs.getObject(3, LocalDate.class), rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class), rs.getBoolean(6), rs.getDouble(7));
		}
		return ad;
	}

	@Override
	public void updateApprovalDate(int formID, String title) throws SQLException {
		String col = null;
		System.out.println("in approvaldao formID: " + formID);
		if(title.equals("benco")) {
			col = "benco_approval_date";
		} else if(title.equals("direct supervisor")) {
			col = "direct_supervisor_approval_date";
		} else if(title.equals("department head")) {
			col = "dept_head_approval_date";
		} 	
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "update approval_dates set " + col + "=? where form_id=?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setObject(1, todayLocalDate);
		ps.setInt(2, formID); 
		ps.executeUpdate();	
	}

	@Override
	public void approvalInitial(int formID, boolean decision) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "update approval_dates set is_approved=? where form_id=?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setObject(1, decision);
		ps.setInt(2, formID); 
		ps.executeUpdate();	
		
	}

	@Override
	public void enterFirstDate(int formID) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "update approval_dates set date_entered=? where form_id=?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setObject(1, todayLocalDate);
		ps.setInt(2, formID); 
		ps.executeUpdate();	
		
	}

	@Override
	public double getReimbursement(int formID) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "select reimbursement from approval_dates where form_id=?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setInt(1, formID);
		ResultSet rs = ps.executeQuery();
		double reimburse = 0.0;
		while(rs.next()) {
			reimburse = rs.getDouble(1);
		}
		return reimburse;
	}

	@Override
	public void updateReimbursement(int formID, double reimburse) throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
		String sql = "update approval_dates set reimbursement=? where form_id=?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setInt(1, formID);
		ps.setDouble(2, reimburse);
		ps.executeUpdate();		
	}



}
