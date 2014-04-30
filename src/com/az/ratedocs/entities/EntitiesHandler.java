package com.az.ratedocs.entities;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;

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
