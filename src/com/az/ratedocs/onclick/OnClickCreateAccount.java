package com.az.ratedocs.onclick;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.az.ratedocs.R;
import com.az.ratedocs.SignInActivity;
import com.az.ratedocs.entities.EntitiesHandler;
import com.az.ratedocs.entities.HandlerFactory;
import com.az.ratedocs.entities.UserInfoInterface;
import com.az.ratedocs.exceptionhandler.IncompleteFieldException;
import com.az.ratedocs.exceptionhandler.PasswordMatchException;

public class OnClickCreateAccount implements OnClickInterface {

	private Activity activity;

	/* associate the on click methods with the buttons */
	public OnClickCreateAccount(Activity a) {
		this.activity = a;
		Button button = (Button) activity
				.findViewById(R.id.btn_signup);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					clickedRegister();
				} catch (PasswordMatchException e) {
					Toast.makeText(activity.getBaseContext(), e.getMessage(),
							Toast.LENGTH_SHORT).show();
				} catch (IncompleteFieldException e) {
					Toast.makeText(activity.getBaseContext(), e.getMessage(),
							Toast.LENGTH_SHORT).show();
				} 
			}

		});
	}

	/* Attempt to register the user */
	@SuppressLint("NewApi")
	public void clickedRegister() throws PasswordMatchException,
			IncompleteFieldException {

		/* Grab the information from the edit texts */
		String name = ((TextView) activity
				.findViewById(R.id.etUsername)).getText()
				.toString();
		String email = ((TextView) activity
				.findViewById(R.id.etEmail)).getText().toString();
		String uname = ((TextView) activity
				.findViewById(R.id.etUsername)).getText()
				.toString();
		String pword = ((TextView) activity
				.findViewById(R.id.etPassword)).getText()
				.toString();
		String cpword = ((TextView) activity
				.findViewById(R.id.etPasswordConfirm)).getText()
				.toString();

		/*
		 * If the password does not match the confirmation password throw an
		 * exception
		 */
		if (!pword.equals(cpword))
			throw new PasswordMatchException("Passwords must match");

		/* If the user has not filled in every field throw an exception */
		if (name.isEmpty() || email.isEmpty() || uname.isEmpty()
				|| pword.isEmpty())
			throw new IncompleteFieldException("Please fill in all fields");

		/* get a blank user from the entities handler */
		EntitiesHandler entityHandler = HandlerFactory.getHandler(activity);
		UserInfoInterface user = entityHandler.getUser();

		/* Give the user the new credentials and attempt to register them */
		user.setUserName(uname);
		user.setPassword(pword);
		user.setEmailID(email);
		entityHandler.signUp(user, activity, SignInActivity.class);
	}
}
