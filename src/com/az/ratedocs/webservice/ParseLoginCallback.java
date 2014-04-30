/************************************************************************************************
 *	18-641 Java for Smart Phone Development
 * 	Authors: 		Shubhang Chaudhary (shubhanc)
 * 					Fiona Britto (fbritto)
 * 					Kyle Verma (ktv)
 * 	Application: 	SmartLend
 * 	Date:			November 30th, 2013 
 ************************************************************************************************/

package com.az.ratedocs.webservice;

import android.app.Activity;
import android.widget.Toast;

//import com.crouchingtigers.smartlend.utility.StartIntent;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/************************************************************************************************
 * ClassName: ParseLoginCallback.java
 * Description: Parse callback for logging a user in
 ************************************************************************************************/

public class ParseLoginCallback extends LogInCallback {

	private Activity activity;
	private Class<?> class1;

	public ParseLoginCallback(Activity activity, Class<?> class1){
		this.activity = activity;
		this.class1 = class1;
	}

	/* Callbacks cannot throw exceptions so we have to do exception handling here */
	@Override
	public void done(ParseUser user, ParseException e) {
		if(user != null) {
			Toast.makeText(activity.getBaseContext(), "Log in Successful", 
					Toast.LENGTH_SHORT).show();
//			StartIntent.startIntent(activity, class1);
		} else {
			Toast.makeText(activity.getBaseContext(), "Log in Failed", 
					Toast.LENGTH_SHORT).show();
		}
	}

}
