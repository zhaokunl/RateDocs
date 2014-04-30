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

import android.app.Activity;
/************************************************************************************************
 * ClassName: UserInfoInterface.java
 * Description: This Interface allows classes to generically access users without having to know
 * 				How the user is implementd. This would allow us to switch from Parse to some other
 * 				web server without having to rewrite all of our code
 ************************************************************************************************/

public interface UserInfoInterface {
	/* getters for the user object */
	public String getUserName();
	public String getEmailID();
	public String getPassword();
	public String getPhone();
	
	/* setters for the user object */
	public void setUserName(String userName);
	public void setEmailID(String email);
	public void setPassword(String password);
	public void setPhone(String phoneNumber);
	public void signUp(Activity activity, Class<?> class1);
}
