package com.az.ratedocs.onclick;

/************************************************************************************************
 *	18-641 Java for Smart Phone Development
 * 	Authors: 		Shubhang Chaudhary (shubhanc)
 * 					Fiona Britto (fbritto)
 * 					Kyle Verma (ktv)
 * 	Application: 	SmartLend
 * 	Date:			November 30th, 2013 
 ************************************************************************************************/


/* All the android imports */

import com.az.ratedocs.entities.EntitiesHandler;
import com.az.ratedocs.entities.HandlerFactory;
import com.az.ratedocs.exceptionhandler.WebServiceException;
import com.az.ratedocs.utilities.StartIntent;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


/************************************************************************************************
 * ClassName: OnClickMainActivity.java
 * Description: This class includes some of the on click methods for our log in activity
 ************************************************************************************************/

public class OnClickMainActivity implements OnClickInterface {
	Activity activity;

	public OnClickMainActivity(Activity a) {
		this.activity = a;
//		
//		/* Associate the on click methods with the buttons in our activity */
//		EntitiesHandler entitiesHandler = HandlerFactory.getHandler(activity);
//		try {
//			entitiesHandler.getCurrentUser();
//			StartIntent.startIntent(activity, Homepage.class);
//		} catch (WebServiceException e) {
//			/* Do nothing */
//		}
//		
//		Button button = (Button) activity.findViewById(R.id.forgot_password_btn_login);
//		button.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				clickedForgotPassword();
//			}
//		});
//		
//		button = (Button) activity.findViewById(R.id.register);
//		button.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				clickedRegisterUser();
//			}
//		});
//		
//		button = (Button) activity.findViewById(R.id.login_btn_login);
//		button.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				clickedLogin();
//			}
//		});
	}
//
//	/* Start the forgot password activity */
//	public void clickedForgotPassword() {
//		StartIntent.startIntent(activity, PasswordRecoveryActivity.class);
//	}
//
//	/* Start the registration activity */
//	public void clickedRegisterUser() {
//		StartIntent.startIntent(activity, Register.class);
//	}
//
//	/* Attempt to log the user in */
//	public void clickedLogin() {
//
//		/* Get the input user name and password */
//		String uname = ((TextView) activity.findViewById(R.id.username_login)).getText().toString();
//		String pword = ((TextView) activity.findViewById(R.id.password_login)).getText().toString();
//
//		/* Log the user in to the server */
//		EntitiesHandler entityHandler = HandlerFactory.getHandler(activity);
//		entityHandler.logIn(uname, pword, activity, Homepage.class);
//	}
}
