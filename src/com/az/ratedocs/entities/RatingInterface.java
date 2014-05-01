package com.az.ratedocs.entities;

public interface RatingInterface {
	public Double getRating();
	public String getComment();
	public String getDoctorID();
	public String getUserName();
	
	public void setRating(Double score);
	public void setComment(String comment);
	public void setDoctorID(String id);
	public void setUserName(String name);
}
