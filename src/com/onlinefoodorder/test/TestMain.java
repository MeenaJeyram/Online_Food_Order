package com.onlinefoodorder.test;

import java.util.List;
import java.util.Scanner;

import com.onlinefoodorder.dao.FoodItemsDao;
import com.onlinefoodorder.dao.OrderFoodsDao;
import com.onlinefoodorder.dao.RestaurantdetailsDao;
import com.onlinefoodorder.dao.UserDao;
import com.onlinefoodorder.model.FoodItems;
import com.onlinefoodorder.model.Orderfoods;
import com.onlinefoodorder.model.RestaurantDetails;
import com.onlinefoodorder.model.User;

public class TestMain {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("\t Welcome to Online Food Order ");
		System.out.println("\n1. Register the user\n2. User Login Or Admin Login\nEnter your choice");
		int userchoice1 = Integer.parseInt(input.nextLine());
		UserDao userDao = new UserDao();
		User user = null;
		RestaurantdetailsDao restaurantdetailDao = null;
		FoodItemsDao fooditem = null;
		FoodItems food = null;
		Orderfoods orderfood = null;
		OrderFoodsDao orderfoodsdao = null;

		int flag = 0;

		switch (userchoice1) {
		case 1:												 	// Register the user
			userDao = new UserDao();
			user = new User();
			System.out.print("User name : "); 					
			String user_name = input.nextLine();
		
			System.out.print("Phone Number : "); 			
			String phone = input.nextLine();
			long phone_no = Long.parseLong(phone);
			
			System.out.print("User Address : "); 				
			String address = input.nextLine();
			
			System.out.print("Email Address : "); 			
			String email_address = input.nextLine();
															
			System.out.print("Password : ");
			String password = input.nextLine();
			user = new User(user_name, phone_no, address, email_address, password);
			userDao.insertUser(user);
			break;
		
		case 2:
			fooditem = new FoodItemsDao();
			List<FoodItems> FoodItems = null;
			userDao = new UserDao();
			System.out.println("Enter the registered email address");
			email_address = input.nextLine();
			
			System.out.print("password :");
			password = input.nextLine();
			do {
				user = userDao.validateUser(email_address, password);
				User validAdmin = userDao.admin(email_address, password);

				if (validAdmin != null) { 									// Admin Login
					System.out.println("Welcome admin");
					System.out.println(
							"\n1. view User\n2. Find userId \n3. Delete User\n4. Add Restaurant Details \n5. Add food details\n6. Delete restaurant\n7. Update Restaurant Details \n8. Find foodid \n9.View Orders");
					int adminChoice = Integer.parseInt(input.nextLine());
					switch (adminChoice) {
					case 1: 												// view the user
						UserDao userDao1 = new UserDao();
						List<User> userList = userDao1.viewUser();
						for (int i = 0; i < userList.size(); i++) {
							System.out.println(userList.get(i));
						}
					case 2:													 // find the user
						System.out.println("User email address to find user id:");
						email_address = input.nextLine();
						int userId = userDao.findUserId(email_address);
						System.out.println("user id = " + userId);

					case 3: 												// delete user
						System.out.println("Enter the email address ");
						email_address = input.nextLine();
						userDao.userProfileDelete(email_address);
					case 4:
						System.out.println("-- Register the restaurant details --");
						System.out.print("Restaurant name : "); 			// Restaurant details
						String restaurant_name = input.nextLine();
						
						System.out.print("Area : ");
						String area = input.nextLine();
						
						System.out.print("City : ");
						String city = input.nextLine();
						
						System.out.print("Pincode : ");
						String restaurant_pincode = input.nextLine();
						
						int pincode = Integer.parseInt(restaurant_pincode);
						System.out.print("Restaurant landline number : ");
						String restaurant_landline_number = input.nextLine();
						
						long restaurant_landline_no = Long.parseLong(restaurant_landline_number);
						System.out.print("Restaurant owner name : ");
						String owner_name = input.nextLine();
						
						System.out.print("Enter operational hours : ");
						String operational_hours = input.nextLine();
						
						System.out.print("Email address :");
						String email = input.nextLine();
						
						System.out.print("Password : ");
						password = input.nextLine();
						
						RestaurantDetails restaurant = new RestaurantDetails(restaurant_name, area, city, pincode,
								restaurant_landline_no, owner_name, operational_hours, email, password);
						restaurantdetailDao = new RestaurantdetailsDao();
						restaurantdetailDao.insertRestaurantDetails(restaurant);
						break;
					
					case 5:
						restaurantdetailDao = new RestaurantdetailsDao();
						fooditem = new FoodItemsDao();
						System.out.println("-- Register the food details in the specific restaurant --");
						System.out.print("enter restauant email to find restaurant id : "); // restaurant food details
						email = input.nextLine();
						int restaurantid = restaurantdetailDao.findRestaurantId(email);
						System.out.println("restaurant id : " + restaurantid);

						System.out.println("Enter the food Details");
						System.out.print("restaurant id : ");
						int restaurant_id = Integer.parseInt(input.nextLine());
						System.out.print("Cuisine name : ");
						String cuisine_name = input.nextLine();
						System.out.print("Food name : ");
						String food_name = input.nextLine();
						System.out.print("Description : ");
						String description = input.nextLine();
						System.out.print("Price : ");
						double price = input.nextDouble();

						food = new FoodItems(restaurant_id, food_name, cuisine_name, description, price);
						fooditem.insertFoodItems(food);
						break;
					
					case 6:
						System.out.println("Enter the restaurant email address to delete"); // delete restaurant
						String emailid = input.nextLine();
						restaurantdetailDao.deleteRestaurant(emailid);
						break;
					case 7:

						System.out.print("Enter the restaurant email address to update password :"); //update restaurant																				
						email = input.nextLine();
						System.out.print("Enter Password :");
						password = input.nextLine();
						restaurantdetailDao.restaurantUpdate(email, password);
						break;
					case 8: // find food id
						fooditem = new FoodItemsDao();
						System.out.println("Enter the food name to find food id : ");
						food_name = input.nextLine();
						int item = fooditem.findFoodItemId(food_name);
						System.out.println("item id = " + item);
						break;
					case 9:																	//view orders
						orderfoodsdao = new OrderFoodsDao();
						System.out.println("view order");
						List<Orderfoods> orderlist = orderfoodsdao.viewOrderFoods();
						for(int i=0;i<orderlist.size();i++)
						{
							System.out.println(orderlist.get(i));
						}
					}
				} 
				else if (user != null) 
				{
					System.out.println("Welcome\t" + user.getUser_name());
					flag = 1;
					boolean flag1 = true;
					while (flag1) 
					{
					System.out.println("\n1. Show Food Items\n2. User details update\n3. User account delete");
					int userChoice = Integer.parseInt(input.nextLine());
					switch (userChoice) {
					case 1:
						fooditem = new FoodItemsDao(); // view foods
						FoodItems = fooditem.showFoodItems();
						for (int i = 0; i < FoodItems.size(); i++) {
							System.out.println(FoodItems.get(i));
						}
						System.out.println("1. order foods");
						int orderChoice = Integer.parseInt(input.nextLine());
						switch (orderChoice) 
						{
						case 1: // orders foods
							// user = new User();
							orderfoodsdao = new OrderFoodsDao();
							orderfood = new Orderfoods();
							fooditem = new FoodItemsDao();
							FoodItems = fooditem.showFoodItems();
							String emailaddress = user.getEmail_address();
							System.out.println(emailaddress);
							int userid = userDao.findUserId(emailaddress);
							System.out.println("user id : " + userid);

							System.out.print("Food name : ");
							String food_name = input.nextLine();

							int foodid = fooditem.findFoodItemId(food_name);
							System.out.println("food Id : " + foodid);

							int price = fooditem.findFoodPrice(foodid);
							System.out.println("price : " + price);

							System.out.println("quantity : ");
							int quantity = Integer.parseInt(input.nextLine());

							double total_price = (double) (quantity * price);
							userDao = new UserDao();

							int walletbal = userDao.walletbal(userid);
							int dedwallbal = (int) (walletbal - total_price);
								System.out.println("\n 1.confirm order \n 2.cancel order");
								int orderConfirmation = input.nextInt();
								input.nextLine();
								switch (orderConfirmation) 
								{
								case 1:
									if (total_price <= walletbal) {
										int upd = userDao.updatewallet(dedwallbal, userid);
										if(upd > 0) {
										orderfood = new Orderfoods(userid, foodid, quantity, total_price);
										orderfoodsdao.insertOrderFoods(orderfood);
										System.out.println("order placed successfully!!!");
										}else {
											System.out.println("something went wrong try again!!");
										}
									} else {
										System.out.println("Low Balance please top up!!");
									}
									flag1 = false;
									break;
								case 2:
									System.out.println("Order Cancelled");
									break;
								}
							}
					case 3: 																	// user update
						System.out.print("Enter the email address to update password :");
						email_address = input.nextLine();
						System.out.print("Enter Password :");
						password = input.nextLine();
						userDao.userProfileUpdate(email_address, password);
						break;
					case 4:																		//user profile delete
						System.out.println("Enter the email address ");
						email_address = input.nextLine();
						userDao.userProfileDelete(email_address);
						break;
					case 5:
						restaurantdetailDao = new RestaurantdetailsDao();
						fooditem = new FoodItemsDao();
						System.out.println("Enter the restaurant name to show foods items");
						String name = input.nextLine();
						int restaurant_Id = restaurantdetailDao.findRestaurantId2(name);
						FoodItems = fooditem.findfoodNames(restaurant_Id);
						for (int i = 0; i < FoodItems.size(); i++) 
						{
							System.out.println(FoodItems.get(i));
						}
						
					}
				}
			} else
				flag = 0;
				break;
		} while (flag == 0);
		break;
		}
	}
}
	

