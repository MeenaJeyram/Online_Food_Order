package com.onlinefoodorder.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.onlinefoodorder.model.User;

public class UserDao 
{
	public void insertUser(User user) 
	{
		String insertQuery = "insert into user_details(user_name, phone_no, address, email_address, password) values(?,?,?,?,?)";
		
		ConnectionUtil con1 = new ConnectionUtil();
		Connection c1 = con1.getDbConnection();
		PreparedStatement p1 = null;
		try {
			p1 = c1.prepareStatement(insertQuery);
			p1.setString(1, user.getUser_name());
			p1.setLong(2, user.getPhone_no());
			p1.setString(3, user.getAddress());
			p1.setString(4, user.getEmail_address());
			p1.setString(5, user.getPassword());
			p1.executeUpdate();
			System.out.println("User details are successfully inserted ");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("try Again");
		}
	}
	
	public User validateUser(String email_address,String password)
	{
		String validateQuery="select * from user_details where email_address='"+email_address+"' and password='"+password+"'";
		Connection con=ConnectionUtil.getDbConnection();
		User user=null;
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(validateQuery);
			if(rs.next())
			{
				user=new User(rs.getString(2),rs.getLong(3),rs.getString(4),email_address, password);
			}
			else {
				System.out.println("Not a valid user");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Statement error");
		} 
		return user;
	}
	
	public void userProfileUpdate(String email_address, String password)
	{
		String updateQuery = "update user_details set password=? where email_address=?";
		
		Connection con = ConnectionUtil.getDbConnection();
		try {
			PreparedStatement p1 = con.prepareStatement(updateQuery);
			p1.setString(2, email_address);
			p1.setString(1, password);
			int i = p1.executeUpdate();
			System.out.println(i+" row Updated");
			p1.close();
			con.close();
		} 
		catch (SQLException e)
		{	
			e.printStackTrace();
		}
	}
	
	public void userProfileDelete(String email_address)
	{
		String deleteQuery = "delete from user_details where email_address=?";
		Connection con = ConnectionUtil.getDbConnection();
		try {
			PreparedStatement p1 = con.prepareStatement(deleteQuery);
			p1.setString(1, email_address);
			int i = p1.executeUpdate();
			System.out.println(i+ "user deleted");
			p1.close();
			con.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	public User admin(String email_address,String password)
	{
		String adminQuery="select * from user_details where role='admin' and email_address='"+email_address+"'and password='"+password+"'";
		
		Connection con=ConnectionUtil.getDbConnection();
		User user=null;
		try {
			Statement stm =con.createStatement();
			ResultSet rs=stm.executeQuery(adminQuery);
			if(rs.next())
			{
				 user=new User(rs.getString(2),rs.getLong(3),rs.getString(5),email_address, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> viewUser()
	{
		List<User> userList = new ArrayList<User>();
		String showQuery = "select * from user_details where role = 'user'";
		Connection con = ConnectionUtil.getDbConnection();
		try {
			Statement s1 = con.createStatement();
			ResultSet rs = s1.executeQuery(showQuery);
			while(rs.next())
			{
				User user = new User(rs.getString(2),(rs.getLong(3)),rs.getString(5), rs.getString(6), rs.getString(7));
				userList.add(user);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	public static int findUserId(String email_address)
	{
		String find_user = "select user_id from user_details where email_address = '"+email_address+"'";
		Connection con = ConnectionUtil.getDbConnection();
		int userId = 0;
		
		try {
			Statement s1 = con.createStatement();
			ResultSet rs = s1.executeQuery(find_user);
			if(rs.next())
			{
				userId = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
}









