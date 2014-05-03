package com.az.ratedocs.entities;

/* This interface allows classes to generically access to the rating object.
 * */

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
