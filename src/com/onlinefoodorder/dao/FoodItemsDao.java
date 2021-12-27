package com.onlinefoodorder.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.onlinefoodorder.model.FoodItems;
import com.onlinefoodorder.model.FoodItems;

public class FoodItemsDao 
{
	public void insertFoodItems(FoodItems fooditem)
	{
		String insertQuery = "insert into food_items(restaurant_id, cuisine_name, food_name, description, price)values(?,?,?,?,?)";
		ConnectionUtil con = new ConnectionUtil();
		Connection c1 = con.getDbConnection();
		try {
			PreparedStatement p1 = c1.prepareStatement(insertQuery);
			p1.setInt(1, fooditem.getRestaurant_id());
			p1.setString(2, fooditem.getCuisine_name());
			p1.setString(3, fooditem.getFood_name());
			p1.setString(4, fooditem.getDescription());
			p1.setDouble(5, fooditem.getPrice());
			p1.executeUpdate();
			System.out.println("Food items are inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<FoodItems> showFoodItems()
	{
		List<FoodItems> foodItemList = new ArrayList<FoodItems>();
		String showQuery = "select * from food_items";
		Connection con = ConnectionUtil.getDbConnection();
		try {
				Statement stmt = con.createStatement();
				ResultSet rs=stmt.executeQuery(showQuery);
				while(rs.next())
				{
					FoodItems fooditem = new FoodItems(Integer.parseInt(rs.getString(1)),rs.getString(3), rs.getString(4), rs.getString(5), Double.parseDouble(rs.getString(6)));
					foodItemList.add(fooditem);
				}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return foodItemList;
	}
	public List<FoodItems> findfoodNames(int r_id)
	{
		List<FoodItems> foodnamelist = new ArrayList<FoodItems>();
		String query = "select * from food_items where restaurant_id = "+r_id;
		Connection con = ConnectionUtil.getDbConnection();
		try {
			Statement p1 = con.createStatement();
			ResultSet rs = p1.executeQuery(query);
			while(rs.next())
			{
				FoodItems fooditem = new FoodItems(r_id, rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6));
				foodnamelist.add(fooditem);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return foodnamelist;	
	}

	public int findFoodItemId(String food_name)
	{
		String find = "select item_id from food_items where food_name = '"+food_name+"'";
		Connection con = ConnectionUtil.getDbConnection();
		int item = 0;
		try {
			Statement s1 = con.createStatement();
			ResultSet rs = s1.executeQuery(find);
			if(rs.next())
			{
				item = rs.getInt(1);
			}
		} 
		catch (SQLException e) {
				e.printStackTrace();
		}
		return item;
	}
	public int findFoodPrice(int foodid)
	{
		String price ="select price from food_items where item_id= '"+foodid+"'";
		Connection con = ConnectionUtil.getDbConnection();
		Statement s1;
		int foodprice = 0;
		try {
			s1=con.createStatement();
			ResultSet rs = s1.executeQuery(price);
			if(rs.next())
			{
				foodprice = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodprice;			
	}
	
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
