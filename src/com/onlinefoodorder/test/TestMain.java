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
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("\t Welcome to Online Food Order ");
		System.out.println("\n1. Register the user\n2. User Login Or Admin Login\nEnter your choice");
		int userchoice1 = Integer.parseInt(input.nextLine());
		UserDao userDao = new UserDao();
		User user = null;
		RestaurantdetailsDao restaurantdetailDao = null;
		FoodItemsDao fooditem = null;
		FoodItems food =null;
		Orderfoods orderfood = null;
		OrderFoodsDao orderfoodsdao = null;
		
		int flag = 0;

		switch (userchoice1) {
		case 1:																//Register the user
			userDao = new UserDao(); 
			//user = new User();
			System.out.print("user name : "); 								// Username
			String user_name = input.nextLine();
			do {
				if (user_name.matches("[A-Z a-z]{3,}")) {
					flag = 0;
					break;
				} else {
					System.out.println("Enter valid username : ");
					user_name = input.nextLine();
					flag = 1;
				}
			} while (flag == 1);
			System.out.print("Phone Number : "); 							// Phone Number
			String phone = input.nextLine();
			do {
				if (phone.matches("[6-9][0-9]{9}")) {
					flag = 0;
					break;
				} else {
					System.out.print("Enter valid phone number :");
					phone = input.nextLine();
					flag = 1;
				}
			} while (flag == 1);
			long phone_no = Long.parseLong(phone);
			System.out.print("Address : "); 								// address
			String address = input.nextLine();
			do {
				if (address.matches("[A-Za-z0-9]{4,}+[,][A-Za-z]{4,}+[,][0-9]{6}+{25,}")) {
					flag = 0;
					break;
				} else {
					System.out.print("enter valid address : ");
					address = input.nextLine();
					flag = 1;
				}
			} while (flag == 1);
			System.out.print("Email Address : "); 							// Email address
			String email_address = input.nextLine();
			do {
				if (email_address.matches("[a-z]+[@][a-z]+[.][a-z]+{12,}")) {
					flag = 0;
					break;
				} else {
					System.out.print("Enter valid mailid : ");
					email_address = input.nextLine();
					flag = 1;
				}
			} while (flag == 1); 											 // password
			System.out.print("Password : ");
			String password = input.nextLine();
			do {
				if (password.matches("[A-Z]+[a-z]+[0-9]+{8,10}")) {
					flag = 0;
					break;
				} else {
					System.out.print("Enter valid password : ");
					password = input.nextLine();
					flag = 1;
				}
			} while (flag == 1);
			
			user = new User(user_name, phone_no, address, email_address, password);
			userDao.insertUser(user);
			break;
		case 2:																	
			fooditem = new FoodItemsDao();
			List<FoodItems> FoodItems = null;										
			userDao = new UserDao();
			System.out.println("Enter the registered email address");
			email_address = input.nextLine();
			do {
				if (email_address.matches("[a-z0-9]+[@][a-z]+[.][a-z]+{8,15}")) {
					flag = 0;
					break;
				} else {
					System.out.print("email address : ");
					email_address = input.nextLine();
					flag = 1;
				}
			} while (flag == 1);
			System.out.print("password :");
			password = input.nextLine();
			do {
				if (password.matches("[A-Za-z0-9]{8,10}")) {
					flag = 0;
					break;
				} else {
					System.out.println("Enter valid password ");
					password = input.nextLine();
					flag = 1;
				}
			} while (flag == 1);

			do {
				user = userDao.validateUser(email_address, password);
				User validAdmin = userDao.admin(email_address, password);

				if (validAdmin != null) {											//Admin Login
					System.out.println("Welcome admin");
					System.out.println("\n1. view User\n2. Find userId \n3. Delete User\n4. Add Restaurant Details \n5. Add food details\n6. Delete restaurant\n7. Update Restaurant Details \n8. Find foodid");													
					int adminChoice = Integer.parseInt(input.nextLine());
					switch(adminChoice)
					{
					case 1:															//view the user
						UserDao userDao1 = new UserDao();
						List<User> userList = userDao1.viewUser();
						for(int i=0; i<userList.size();i++)
						{
							System.out.println(userList.get(i));
						}
					case 2:															//find the user
						System.out.println("User email address to find user id:");
						email_address = input.nextLine();
						int userId = userDao.findUserId(email_address);
						System.out.println("user id = "+userId);
						
					case 3:															//delete user
						System.out.println("Enter the email address ");
						email_address = input.nextLine();
						userDao.userProfileDelete(email_address);
					case 4:
						System.out.println("-- Register the restaurant details --");
						System.out.print("Restaurant name : ");						//Restaurant details		
						String restaurant_name = input.nextLine();
						do {
							if (restaurant_name.matches("[A-Z a-z 0-9]{6,}")) {
								flag = 0;
								break;
							} else {
								System.out.println("Enter valid restaurant name : ");
								restaurant_name = input.nextLine();
								flag = 1;
							}
						} while (flag == 1);
						System.out.print("Area : ");
						String area = input.nextLine();
						do {
							if (area.matches("[A-Z a-z 0-9]{4,}")) {
								flag = 0;
								break;
							} else {
								System.out.print("Enter valid area name : ");
								area = input.nextLine();
								flag = 1;
							}
						} while (flag == 1);
						System.out.print("City : ");
						String city = input.nextLine();
						do {
							if (city.matches("[A-Z a-z]{4,}")) {
								flag = 0;
								break;
							} else {
								System.out.print("Enter valid City name : ");
								city = input.nextLine();
								flag = 1;
							}
						} while (flag == 1);
						System.out.print("Pincode : ");
						String restaurant_pincode = input.nextLine();
						do {
							if (restaurant_pincode.matches("[0-9]{6}")) {
								flag = 0;
								break;
							} else {
								System.out.print("Enter valid City name : ");
								restaurant_pincode=input.nextLine();
								flag = 1;
							}
						} while (flag == 1);
						int pincode = Integer.parseInt(restaurant_pincode);
						System.out.print("Restaurant landline number : ");
						String restaurant_landline_number = input.nextLine();
						do {
							if (restaurant_landline_number.matches("[0][2-5]{3}+[5-9]{6}")) {
								flag = 0;
								break;
							} else {
								System.out.print("Enter valid phone number :");
								restaurant_landline_number = input.nextLine();
								flag = 1;
							}
						} while (flag == 1);
						long restaurant_landline_no = Long.parseLong(restaurant_landline_number);
						System.out.print("Restaurant owner name : ");
						String owner_name = input.nextLine();
						do {
							if (owner_name.matches("[A-Z a-z]{3,}")) {
								flag = 0;
								break;
							} else {
								System.out.print("Enter valid name : ");
								owner_name = input.nextLine();
								flag = 1;
							}
						} while (flag == 1);
						System.out.print("Enter operational hours : ");
						String operational_hours = input.nextLine();
						do {
							if (operational_hours.matches("[0-9]{2}+[-][0-9]{2}")) {
								flag = 0;
								break;
							} else {
								System.out.print("Enter valid hours : ");
								operational_hours = input.nextLine();
								flag = 1;
							}
						} while (flag == 1);
						System.out.print("Email address :");
						String email = input.nextLine();
						do {
							if (email.matches("[a-z]+[@][a-z]+[.][a-z]+{12,}")) {
								flag = 0;
								break;
							} else {
								System.out.print("Enter valid mailid : ");
								email = input.nextLine();
								flag = 1;
							}
						} while (flag == 1);
						System.out.print("Password : ");
						password = input.nextLine();
						do {
							if (password.matches("[A-Z]+[a-z]+[0-9]+{8,10}")) {
								flag = 0;
								break;
							} else {
								System.out.print("Enter valid password : ");
								password = input.nextLine();
								flag = 1;
							}
						} while (flag == 1);
						RestaurantDetails restaurant = new RestaurantDetails(restaurant_name, area, city, pincode, restaurant_landline_no, owner_name, operational_hours, email, password);
						restaurantdetailDao = new RestaurantdetailsDao();
						restaurantdetailDao.insertRestaurantDetails(restaurant);
						break;
					case 5:
						restaurantdetailDao = new RestaurantdetailsDao();
						fooditem = new FoodItemsDao();
						System.out.println("-- Register the food details in the specific restaurant --");
						System.out.print("enter restauant email to find restaurant id : ");		//restaurant food details
						email = input.nextLine();
						int restaurantid = restaurantdetailDao.fintRestaurantId(email);
						System.out.println("restaurant id : " +restaurantid);
						
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
						System.out.println("Enter the restaurant email address to delete");		//Admin delete the restaurant
						String emailid = input.nextLine();
						restaurantdetailDao.deleteRestaurant(emailid);
						break;
					case 7:		
						
						System.out.print("Enter the restaurant email address to update password :"); //Admin update the restaurant details
						email = input.nextLine();
						System.out.print("Enter Password :");
						password = input.nextLine();
						restaurantdetailDao.restaurantUpdate(email, password);
						break;
					case 8:																		//find food id
						fooditem = new FoodItemsDao();
						System.out.println("Enter the food name to find food id : ");
						food_name = input.nextLine();
						int item = fooditem.findFoodItemId(food_name);
						System.out.println("item id = " +item);
						break;
					}	
				} else if (user != null) {												
					System.out.println("Welcome\t" + user.getUser_name());
					flag = 1;
					System.out.println("\n1. Show Food Items\n2. User details update\n3. User account delete");
					int userChoice = Integer.parseInt(input.nextLine());
					switch (userChoice) {
					case 1:
						fooditem = new FoodItemsDao();								//view foods
						FoodItems = fooditem.showFoodItems();
						for (int i = 0; i < FoodItems.size(); i++) {
							System.out.println(FoodItems.get(i));
						}
						System.out.println("\n1.Orders foods\n2.view Orders \n3.Update Order\n3.Cancel Order");
						int orderChoice = Integer.parseInt(input.nextLine());
						switch(orderChoice)
						{
						case 1:														//orders foods
							//user = new User();
							orderfoodsdao = new OrderFoodsDao();
							orderfood = new Orderfoods();
							fooditem = new FoodItemsDao();
							FoodItems = fooditem.showFoodItems();
							String emailaddress = user.getEmail_address();
							System.out.println(emailaddress);
							int userid = userDao.findUserId(emailaddress);
							System.out.println("user id : " +userid);
							
							System.out.print("Food name : ");
							String food_name = input.nextLine();
							
							int foodid = fooditem.findFoodItemId(food_name);
							System.out.println("food Id : "+foodid);
							
							int price = fooditem.findFoodPrice(foodid);
							System.out.println("price : " +price);
							
							System.out.println("No. of foods :");
							int quantity = Integer.parseInt(input.nextLine());
							
							double total_price = (double)(quantity*price);
							
							orderfood = new Orderfoods(userid, foodid, quantity, total_price);
							orderfoodsdao.insertOrderFoods(orderfood);
							break;
						case 2:																	//view orders
							orderfoodsdao = new OrderFoodsDao();
							System.out.println("view order");
							List<Orderfoods> orderlist = orderfoodsdao.viewOrderFoods();
							for(int i=0;i<orderlist.size();i++)
							{
								System.out.println(orderlist.get(i));
							}
						case 3:
							orderfoodsdao = new OrderFoodsDao();
							System.out.println("Enter the item id to update the order");
							
						}
						break;
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
					}
				} else
					flag = 0;
					break;
			} while (flag == 0);
			break;
		}
	}
}











