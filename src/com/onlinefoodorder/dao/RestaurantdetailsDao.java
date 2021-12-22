package com.onlinefoodorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.onlinefoodorder.model.RestaurantDetails;

public class RestaurantdetailsDao 
{
	public void insertRestaurantDetails(RestaurantDetails restaurant)
	{
		String insertQuery = "insert into restaurant_details(restaurant_name, area, city, pincode, restaurant_landline_no, owner_name, operational_hours, email, password) values(?,?,?,?,?,?,?,?,?)";
		ConnectionUtil con = new ConnectionUtil();
		Connection c1 = con.getDbConnection();
		PreparedStatement p1 = null;
		
		try {
			p1 = c1.prepareStatement(insertQuery);
			p1.setString(1, restaurant.getRestaurant_name());
			p1.setString(2, restaurant.getArea());
			p1.setString(3, restaurant.getCity());
			p1.setInt(4, restaurant.getPincode());
			p1.setLong(5, restaurant.getRestaurant_landline_no());
			p1.setString(6, restaurant.getOwner_name());
			p1.setString(7, restaurant.getOperational_hours());
			p1.setString(8, restaurant.getEmail());
			p1.setString(9, restaurant.getPassword());
			p1.executeUpdate();
			System.out.println("Restaurant details are inserted");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Try again");
		}
	}
	public void restaurantUpdate(String email, String password)
	{
		String updateQuery = "update restaurant_details set password=? where email=?";
		Connection con = ConnectionUtil.getDbConnection();
		try {
			PreparedStatement p1 = con.prepareStatement(updateQuery);
			p1.setString(2, email);
			p1.setString(1, password);
			int i = p1.executeUpdate();
			System.out.println(i+" restaurant details updated");
			p1.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		 }		
	}
	public void deleteRestaurant(String email)
	{
		String deleteQuery = "delete from restaurant_details where email=?";
		Connection con = ConnectionUtil.getDbConnection();
		try {
			PreparedStatement p1 = con.prepareStatement(deleteQuery);
			p1.setString(1, email);
			int i = p1.executeUpdate();
			System.out.println(i+" restaurant deleted");
			p1.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public int fintRestaurantId(String email)
	{
		String find_id = "select restaurant_id from restaurant_details where email='"+email+"'";
		Connection con = ConnectionUtil.getDbConnection();
		int foodid = 0;
		try {
			Statement s1 = con.createStatement();
			ResultSet rs = s1.executeQuery(find_id);
			if(rs.next())
			{
				foodid = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodid;
	}
}
