package com.az.ratedocs.onclick;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.az.ratedocs.ForgetPasswordActivity;
import com.az.ratedocs.R;
import com.az.ratedocs.SelectSpecialityActivity;
import com.az.ratedocs.SignInActivity;
import com.az.ratedocs.entities.EntitiesHandler;
import com.az.ratedocs.entities.HandlerFactory;
import com.az.ratedocs.entities.UserInfoInterface;
import com.az.ratedocs.exceptionhandler.IncompleteFieldException;
import com.az.ratedocs.exceptionhandler.PasswordMatchException;

public class OnClickCreateAccount implements OnClickInterface {

	private Activity activity;

	public OnClickCreateAccount(Activity a) {
		this.activity = a;
		Button button = (Button) activity.findViewById(R.id.btnCreateAccount);
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

	@SuppressLint("NewApi")
	public void clickedRegister() throws PasswordMatchException,
			IncompleteFieldException {

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

		if (!pword.equals(cpword))
			throw new PasswordMatchException("Passwords must match");

		if (name.isEmpty() || email.isEmpty() || uname.isEmpty()
				|| pword.isEmpty())
			throw new IncompleteFieldException("Please fill in all fields");

		EntitiesHandler entityHandler = HandlerFactory.getHandler(activity);
		UserInfoInterface user = entityHandler.getUser();

		user.setUserName(uname);
		user.setPassword(pword);
		user.setEmailID(email);
		entityHandler.signUp(user, activity, SelectSpecialityActivity.class);
	}
}
