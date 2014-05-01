package com.az.ratedocs.webservice;

import android.app.Activity;
import android.util.Log;

import com.az.ratedocs.entities.UserInfoInterface;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ParseInfoUser implements UserInfoInterface {

	private ParseUser user;
	public ParseInfoUser() {
		user = new ParseUser();
	}
	
	public ParseInfoUser(ParseUser currentUser){
		user = currentUser;
	}
	
	@Override
	public String getUserName() {
		return user.getString(PConstants.PARSE_ID);
	}

	@Override
	public String getEmailID() {
		return user.getString(PConstants.PARSE_USER_EMAIL);
	}

	@Override
	public String getPassword() {
		return user.getString(PConstants.PARSE_PASSWORD);
	}

	@Override
	public void setUserName(String userName) {
		user.setUsername(userName);
	}

	@Override
	public void setEmailID(String email) {
		user.setEmail(email);
	}

	@Override
	public void setPassword(String password) {
		user.setPassword(password);
	}

	@Override
	public void signUp(Activity activity, Class<?> class1) {
		user.signUpInBackground(new ParseSignUpCallback(activity, class1));
	}
}
