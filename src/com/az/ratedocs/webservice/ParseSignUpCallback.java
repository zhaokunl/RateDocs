package com.az.ratedocs.webservice;

import android.app.Activity;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.SignUpCallback;

public class ParseSignUpCallback extends SignUpCallback{

	private Activity activity;
	private Class<?> class1;

	public ParseSignUpCallback(Activity activity, Class<?> class1){
		this.activity = activity;
		this.class1 = class1;
	}

	/* Callback's cannot throw exceptions so we have to do error handling here */
	@Override
	public void done(ParseException e) {

		if(e == null) {
			Toast.makeText(activity.getBaseContext(), "Registration Successful", 
					Toast.LENGTH_SHORT).show();
//			StartIntent.startIntent(activity, class1);
		} else {
			switch (e.getCode()) {
			case 125:	Toast.makeText(activity.getBaseContext(), "Invalid Email Address", 
					Toast.LENGTH_SHORT).show();
			break;
			case 202: 	Toast.makeText(activity.getBaseContext(), "Username Taken", 
					Toast.LENGTH_SHORT).show();
			break;
			case 203: 	Toast.makeText(activity.getBaseContext(), "email Taken", 
					Toast.LENGTH_SHORT).show();
			break;
			default:	Toast.makeText(activity.getBaseContext(), "Unkown Error Occured", 
					Toast.LENGTH_SHORT).show();
			break;
			}
		}
	}
}
