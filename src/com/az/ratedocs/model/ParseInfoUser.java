package com.az.ratedocs.model;

import android.app.Activity;
import com.az.ratedocs.entities.UserInfoInterface;
import com.az.ratedocs.webservice.PConstants;
import com.az.ratedocs.webservice.ParseSignUpCallback;
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
