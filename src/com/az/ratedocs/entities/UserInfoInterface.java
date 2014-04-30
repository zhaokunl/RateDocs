package com.az.ratedocs.entities;

import android.app.Activity;

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
