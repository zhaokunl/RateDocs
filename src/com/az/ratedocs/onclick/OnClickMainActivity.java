package com.az.ratedocs.onclick;

import com.az.ratedocs.CreateAccountActivity;
import com.az.ratedocs.ForgetPasswordActivity;
import com.az.ratedocs.R;
import com.az.ratedocs.SelectSpecialityActivity;
import com.az.ratedocs.MainActivity;
import com.az.ratedocs.entities.EntitiesHandler;
import com.az.ratedocs.entities.HandlerFactory;
import com.az.ratedocs.exceptionhandler.WebServiceException;
import com.az.ratedocs.sqlite.DoctorDatabaseHelper;
import com.az.ratedocs.utilities.StartIntent;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class OnClickMainActivity implements OnClickInterface {
	Activity activity;
	Context context;

	public OnClickMainActivity(Activity a, Context context) {
		this.activity = a;
		this.context = context;
		
		/* Associate the on click methods with the buttons in our activity */
		EntitiesHandler entitiesHandler = HandlerFactory.getHandler(activity);
		try {
			entitiesHandler.getCurrentUser();
		} catch (WebServiceException e) {
			/* Do nothing */
		}
		
		Button button = (Button) activity.findViewById(R.id.btn_ForgetPass);
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
		
		button = (Button) activity.findViewById(R.id.btn_skip);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				skip();
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
		String uname = ((EditText) activity.findViewById(R.id.username)).getText().toString();
		String pword = ((EditText) activity.findViewById(R.id.password)).getText().toString();

		/* Log the user in to the server */
		EntitiesHandler entityHandler = HandlerFactory.getHandler(activity);
		
		entityHandler.logIn(uname, pword, activity, SelectSpecialityActivity.class);
	}
	
	public void skip() {
		DoctorDatabaseHelper d = new DoctorDatabaseHelper(context);
		d.getDoctorList();
	}
}
