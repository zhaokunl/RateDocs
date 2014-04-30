/************************************************************************************************
 *	18-641 Java for Smart Phone Development
 * 	Authors: 		Shubhang Chaudhary (shubhanc)
 * 					Fiona Britto (fbritto)
 * 					Kyle Verma (ktv)
 * 	Application: 	SmartLend
 * 	Date:			November 30th, 2013 
 ************************************************************************************************/


package com.az.ratedocs.entities;

/* All the android imports */

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;

//import com.crouchingtigers.smartlend.exceptionhandler.WebServiceException;
/************************************************************************************************
 * ClassName: BookInterface.java
 * Description: This Interface allows classes to generically access books without having to know
 * 				How the book is implementd. This would allow us to switch from Parse to some other
 * 				web server without having to rewrite all of our code
 ************************************************************************************************/

public interface DoctorInterface {
	/* getter methods for the Book object */ 
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
	
	/* setter methods for the Book object */ 
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
	
	/* Utility to save and delete books from derver*/
	public void save(Activity activity);
//	public void delete() throws WebServiceException;
}
