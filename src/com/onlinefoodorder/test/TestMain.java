package com.onlinefoodorder.test;

import java.util.List;
import java.util.Scanner;

import com.onlinefoodorder.dao.FoodItemsDao;
import com.onlinefoodorder.dao.RestaurantdetailsDao;
import com.onlinefoodorder.dao.UserDao;
import com.onlinefoodorder.model.FoodItems;
import com.onlinefoodorder.model.RestaurantDetails;
import com.onlinefoodorder.model.User;

public class TestMain {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("\t Welcome to Online Food Order ");
		System.out.println("\n1.Register the user\n2. User Login Or Admin Login \n3.User Profile update\n4. Amin Process \n5. food items\nEnter your choice");
		int userchoice1 = Integer.parseInt(input.nextLine());
		UserDao userDao = null;
		FoodItemsDao fooditem = new FoodItemsDao();
		int flag = 0;

		switch (userchoice1) {
		case 1:																//Register the user
			userDao = new UserDao(); // User Register
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
			System.out.print("Address : "); 								// email address
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
			
			User detail = new User(user_name, phone_no, address, email_address, password);
			userDao.insertUser(detail);
			break;

		case 2:
																			// User Login
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
				User currentUser = userDao.validateUser(email_address, password);
				User validAdmin = userDao.admin(email_address, password);

				if (validAdmin != null) {									//Admin Login
					System.out.println("Welcome admin");
					System.out.println(
							"\n1.View Users\n2.Add Restaurant Details\n3.Delete user\n4. Delete Restaurant\n5. Edit Restaurant\n. Enter your choice");
					int userchoice2 = Integer.parseInt(input.nextLine());
					switch (userchoice2) {
					case 1:
						UserDao userdao = new UserDao();
						List<User> userList = userdao.viewUser();
						for (int i = 0; i < userList.size(); i++) {
							System.out.println(userList.get(i));
						}
						break;
					case 2:													//Restaurant details
						System.out.print("Restaurant name : ");						
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
								restaurant_pincode = input.nextLine();
								flag = 1;
							}
						} while (flag == 1);
						System.out.print("Restaurant landline number : ");
						String restaurant_landline_number = input.nextLine();
						do {
							if (restaurant_landline_number.matches("[6-9][0-9]{9}")) {
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
						RestaurantDetails restaurantdetail = new RestaurantDetails(restaurant_name, area, city, pincode, restaurant_landline_no,
								owner_name, operational_hours, email, password);
//					case 3:	
//						System.out.print("Enter the food Details");
//						System.out.print("food id : ");
//						int food_id = Integer.parseInt(input.nextLine());
//						System.out.print("Cuisine name : ");
//						String cuisine_name = input.nextLine();
//						System.out.print("Food name : ");
//						String food_name = input.nextLine();
//						System.out.print("Description : ");
//						String description = input.nextLine();
//						System.out.println("Price : ");
//						double price = input.nextDouble();
//						fooditem = new FoodItemsDao();
//						
//						FoodItem food = new FoodItem(food_id, cuisine_name, food_name, description, price);
//						fooditem.insertFoodItems(food);	
					}
					break;
				} else if (currentUser != null) {
					System.out.println("Welcome\t" + currentUser.getUser_name());
					flag = 1;
				} else
					flag = 0;
			} while (flag == 0);
			break;

		case 3:																	// User profile Update & Delete
			System.out.println("\n1.Update\n2.Delete");
			int userchoice2 = Integer.parseInt(input.nextLine());
			switch (userchoice2) {
			case 1: // user
				System.out.print("Enter the email address to update password :");
				email_address = input.nextLine();
				System.out.print("Enter Password :");
				password = input.nextLine();
				userDao.userProfileUpdate(email_address, password);
				break;
			case 2:
				System.out.println("Enter the email address ");
				email_address = input.nextLine();
				userDao.userProfileDelete(email_address);
				break;
			}
			break;
		case 4:
			System.out.println("\n1. delete restaurant\n2. update restaurant\n3. delete the user\n4. view all users"); //Admin 
			int userchoice3 = Integer.parseInt(input.nextLine());
			switch(userchoice3)
			{	
			case 1:
				System.out.println("Enter the restaurant to delete");					//Admin delete the restaurant
				String restaurant_name = input.nextLine();
				restaurantdetail.deleteRestaurant(restaurant_name);
				break;
			case 2:
				System.out.print("Enter the email address to update password :");		//Admin update the restaurant details
				String email = input.nextLine();
				System.out.print("Enter Password :");
				password = input.nextLine();
				RestaurantdetailsDao restaurantdetails = new RestaurantdetailsDao();
				restaurantdetails.restaurantUpdate(email, password);
				break;
			case 3:
				System.out.println("Enter the emailaddress to delete the user ");		//Admin delete the user
				email_address = input.nextLine();
				userDao.userProfileDelete(email_address);
				break;
			case 4:																		//Admin view the user
				UserDao userDao1 = new UserDao();
				List<User> userList = userDao1.viewUser();
				for(int i=0; i<userList.size();i++)
				{
					System.out.println(userList.get(i));
				}
				break;
			}
		case 5:

			System.out.println("\n1.Show Food Items\n2.Show Orders\n3.Update Order\n4.Cancel Order");
			int userChoice = Integer.parseInt(input.nextLine());
			switch (userChoice) {
			case 1:
				FoodItemsDao foodDao = new FoodItemsDao();
				List<FoodItem> FoodItems = foodDao.showFoodItems();
				for (int i = 0; i < FoodItems.size(); i++) {
					System.out.println(FoodItems.get(i));
				}
				break;
			}
		}
	}
}