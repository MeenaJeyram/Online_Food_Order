package com.onlinefoodorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import com.onlinefoodorder.model.Orderfoods;

public class OrderFoodsDao 
{
	public void insertOrderFoods(Orderfoods order)
	{
		String insert = "insert into order_foods(item_id, quantity, total_price) values(?,?,?)";
		Connection con = ConnectionUtil.getDbConnection();
		try {
			PreparedStatement p1 = con.prepareStatement(insert);
			p1.setInt(1, order.getItem_id());
			p1.setInt(2, order.getQuantity());
			p1.setDouble(3, order.getTotal_price());
			p1.executeUpdate();
			System.out.println("Successfully inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
