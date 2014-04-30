package com.az.ratedocs.entities;

import java.util.ArrayList;

public interface DoctorInterface {
	/* getter methods for the Doctor object */ 
	public String getId();
	public String getName();
	public String getSex();
	public String getSpecialization();
	public String getAddress();
	public double getLatitude();
	public double getLongtitude() ;
	public String getEmail();
	public String getPhone();
	public ArrayList<Integer> getRating();
	public ArrayList<String> getComments();
	
	/* setter methods for the Doctor object */ 
	public void setId(String id);
	public void setName(String name);
	public void setSex(String sex);
	public void setSpecialization(String specialization);
	public void setAddress(String address);
	public void setLatitude(double latitude);
	public void setLongtitude(double longtitude);
	public void setComments(ArrayList<String> comments);
	public void setEmail(String email);
	public void setPhone(String phone);
	public void setRating(ArrayList<Integer> rating);
}
