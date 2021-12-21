package com.onlinefoodorder.model;

import java.util.Objects;

public class RestaurantDetails 
{
	private String restaurant_name;
	private String area;
	private String city;
	private int pincode;
	private long restaurant_landline_no;
	private String owner_name;
	private String operational_hours;
	private String email;
	private String password;
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public long getRestaurant_landline_no() {
		return restaurant_landline_no;
	}
	public void setRestaurant_landline_no(long restaurant_landline_no) {
		this.restaurant_landline_no = restaurant_landline_no;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getOperational_hours() {
		return operational_hours;
	}
	public void setOperational_hours(String operational_hours) {
		this.operational_hours = operational_hours;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RestaurantDetails(String restaurant_name, String area, String city, int pincode, long restaurant_landline_no,
			String owner_name, String operational_hours, String email, String password) {
		super();
		this.restaurant_name = restaurant_name;
		this.area = area;
		this.city = city;
		this.pincode = pincode;
		this.restaurant_landline_no = restaurant_landline_no;
		this.owner_name = owner_name;
		this.operational_hours = operational_hours;
		this.email = email;
		this.password = password;
	}
	public RestaurantDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(area, city, email, operational_hours, owner_name, password, pincode, restaurant_landline_no,
				restaurant_name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestaurantDetails other = (RestaurantDetails) obj;
		return Objects.equals(area, other.area) && Objects.equals(city, other.city)
				&& Objects.equals(email, other.email) && Objects.equals(operational_hours, other.operational_hours)
				&& Objects.equals(owner_name, other.owner_name) && Objects.equals(password, other.password)
				&& pincode == other.pincode && restaurant_landline_no == other.restaurant_landline_no
				&& Objects.equals(restaurant_name, other.restaurant_name);
	}
	@Override
	public String toString() {
		return "RestaurantDetails [restaurant_name=" + restaurant_name + ", area=" + area + ", city=" + city
				+ ", pincode=" + pincode + ", restaurant_landline_no=" + restaurant_landline_no + ", owner_name="
				+ owner_name + ", operational_hours=" + operational_hours + ", email=" + email + ", password="
				+ password + "]";
	}
	
}
