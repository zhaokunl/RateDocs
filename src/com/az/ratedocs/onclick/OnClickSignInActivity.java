package com.az.ratedocs.onclick;

import com.az.ratedocs.CreateAccountActivity;
import com.az.ratedocs.ForgetPasswordActivity;
import com.az.ratedocs.R;
import com.az.ratedocs.SignInActivity;
import com.az.ratedocs.entities.EntitiesHandler;
import com.az.ratedocs.entities.HandlerFactory;
import com.az.ratedocs.exceptionhandler.WebServiceException;
import com.az.ratedocs.utilities.StartIntent;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class OnClickSignInActivity implements OnClickInterface {
	Activity activity;

	public OnClickSignInActivity(Activity a) {
		this.activity = a;
		
		/* Associate the on click methods with the buttons in our activity */
		EntitiesHandler entitiesHandler = HandlerFactory.getHandler(activity);
		try {
			entitiesHandler.getCurrentUser();
			StartIntent.startIntent(activity, SignInActivity.class);
		} catch (WebServiceException e) {
			/* Do nothing */
		}
		
		Button button = (Button) activity.findViewById(R.id.btn_submitforgetpassword);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clickedForgotPassword();
			}
		});
		
		button = (Button) activity.findViewById(R.id.btn_signup);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clickedRegisterUser();
			}
		});
		
		button = (Button) activity.findViewById(R.id.btn_login);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clickedLogin();
			}
		});
	}

	/* Start the forgot password activity */
	public void clickedForgotPassword() {
		StartIntent.startIntent(activity, ForgetPasswordActivity.class);
	}

	/* Start the registration activity */
	public void clickedRegisterUser() {
		StartIntent.startIntent(activity, CreateAccountActivity.class);
	}

	/* Attempt to log the user in */
	public void clickedLogin() {

		/* Get the input user name and password */
		String uname = ((TextView) activity.findViewById(R.id.btn_login)).getText().toString();
		String pword = ((TextView) activity.findViewById(R.id.btn_ForgetPass)).getText().toString();

		/* Log the user in to the server */
		EntitiesHandler entityHandler = HandlerFactory.getHandler(activity);
		entityHandler.logIn(uname, pword, activity, SignInActivity.class);
	}
}
