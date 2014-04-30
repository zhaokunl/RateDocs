package com.az.ratedocs.model;

import java.util.ArrayList;

import android.app.Activity;

import com.az.ratedocs.entities.DoctorInterface;

public class Doctor implements DoctorInterface{
	private String id;
	private String name;
	private String sex;
	private String specialization;
	private String address;
	private double latitude;
	private double longtitude;
	private String email;
	private String phone;
	private ArrayList<Integer> rating;
	private ArrayList<String> comments;
	
	public Doctor() {
		super();
	}

	public Doctor(String id, String name, String sex, String specialization, 
			String address, double latitude, double longtitude, String email,
			String phone, ArrayList<Integer> rating, ArrayList<String> comments) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.specialization = specialization;
		this.address = address;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.email = email;
		this.phone = phone;
		this.rating = rating;
		this.comments =comments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public ArrayList<Integer> getRating() {
		return rating;
	}

	public void setRating(ArrayList<Integer> rating) {
		this.rating = rating;
	}

	public ArrayList<String> getComments() {
		return comments;
	}

	public void setComments(ArrayList<String> comments) {
		this.comments = comments;
	}

	public void addRatings(int ratingValue) {
		rating.add(ratingValue);
	}
	
	public void addComments(String CommentsValue) {
		comments.add(CommentsValue);
	}
	
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", specialization=" + specialization + ", address=" + address
				+ ", latitude=" + latitude + ", longtitude=" + longtitude
				+ ", email=" + email + ", phone=" + phone + ", rating="
				+ rating + ", comments=" + comments + "]";
	}

	@Override
	public void save(Activity activity) {
	}
}

