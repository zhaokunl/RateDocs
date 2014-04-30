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
import java.util.HashMap;

import android.app.Activity;

//import com.crouchingtigers.smartlend.exceptionhandler.WebServiceException;
/************************************************************************************************
 * ClassName: EntitiesHandler.java
 * Description: This Interface allows classes to generically generate entities (book & user) and
 * 				access the server (login, logout, query, etc...) without knowledge of the
 * 				underlying server technology
 ************************************************************************************************/

public interface EntitiesHandler {
	public void logOut();
	public void logIn(String uname, String pword, Activity activity,Class<?> class1);
	public void signUp(UserInfoInterface user, Activity activity,Class<?> class1);
	public void resetPassword(String email, Activity activity);
	public void setCurrentBook(DoctorInterface book);
	public DoctorInterface getBook();
	public UserInfoInterface getUser();
//	public UserInfoInterface getCurrentUser() throws WebServiceException;
	public DoctorInterface getCurrentBook(Activity activity);
	public ArrayList<DoctorInterface> getCurrentList(Activity activity);
//	void query(String title, Activity activity, HashMap<String, String> advSearch) throws WebServiceException;
//	public void getUserBooks(Activity activity) throws WebServiceException;
}
