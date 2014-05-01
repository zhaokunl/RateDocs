package com.az.ratedocs.onclick;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.az.ratedocs.ConnectionDetector;
import com.az.ratedocs.SignInActivity;
import com.az.ratedocs.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class OnClickCreateAccount implements OnClickInterface, OnClickListener{
	Activity activity;

	private EditText mUserNameEditText;
	private EditText mEmailEditText;
	private EditText mPasswordEditText;
	private EditText mConfirmPasswordEditText;
	private Button mCreateAccountButton;
	private String mEmail;
	private String mUsername;
	private String mPassword;
	private String mConfirmPassword;

	// flag for Internet connection status
	private Boolean isInternetPresent = false;
	// Connection detector class
	private ConnectionDetector cd;

	/* Associate the on click methods with our buttons */
	public OnClickCreateAccount(Activity activity) {
		this.activity = activity;
		// creating connection detector class instance
		cd = new ConnectionDetector(activity.getApplicationContext());

		mUserNameEditText = (EditText) activity.findViewById(R.id.etUsername);
		mEmailEditText = (EditText) activity.findViewById(R.id.etEmail);
		mPasswordEditText = (EditText) activity.findViewById(R.id.etPassword);
		mConfirmPasswordEditText = (EditText) activity.findViewById(R.id.etPasswordConfirm);

		mCreateAccountButton = (Button) activity.findViewById(R.id.btnCreateAccount);
		mCreateAccountButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnCreateAccount:
			// get Internet status
			isInternetPresent = cd.isConnectingToInternet();
			// check for Internet status
			if (isInternetPresent) {
				// Internet Connection is Present
				// make HTTP requests
				createAccount();
			} else {
				// Internet connection is not present
				// Ask user to connect to Internet
				showAlertDialog(activity.getApplicationContext(),
						"No Internet Connection",
						"You don't have internet connection.", false);
			}

			break;

		default:
			break;
		}
	}

	private void createAccount() {
		clearErrors();

		boolean cancel = false;
		View focusView = null;

		// Store values at the time of the login attempt.
		mEmail = mEmailEditText.getText().toString();
		mUsername = mUserNameEditText.getText().toString();
		mPassword = mPasswordEditText.getText().toString();
		mConfirmPassword = mConfirmPasswordEditText.getText().toString();

		// Check for a valid confirm password.
		if (TextUtils.isEmpty(mConfirmPassword)) {
			mConfirmPasswordEditText
					.setError(activity.getString(R.string.error_field_required));
			focusView = mConfirmPasswordEditText;
			cancel = true;
		} else if (mPassword != null && !mConfirmPassword.equals(mPassword)) {
			mPasswordEditText
					.setError(activity.getString(R.string.error_invalid_confirm_password));
			focusView = mPasswordEditText;
			cancel = true;
		}
		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordEditText
					.setError(activity.getString(R.string.error_field_required));
			focusView = mPasswordEditText;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordEditText
					.setError(activity.getString(R.string.error_invalid_password));
			focusView = mPasswordEditText;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailEditText.setError(activity.getString(R.string.error_field_required));
			focusView = mEmailEditText;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			mEmailEditText.setError(activity.getString(R.string.error_invalid_email));
			focusView = mEmailEditText;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			Toast.makeText(activity.getApplicationContext(), "signUp",
					Toast.LENGTH_SHORT).show();
			signUp(mUsername.toLowerCase(Locale.getDefault()), mEmail,
					mPassword);
		}
	}

	private void signUp(final String mUsername, String mEmail, String mPassword) {
		Toast.makeText(activity.getApplicationContext(), mUsername + " - " + mEmail,
				Toast.LENGTH_SHORT).show();
		ParseUser user = new ParseUser();
		user.setUsername(mUsername);
		user.setPassword(mPassword);
		user.setEmail(mEmail);

		user.signUpInBackground(new SignUpCallback() {
			public void done(ParseException e) {
				if (e == null) {
					signUpMsg("Account Created Successfully");
					Intent in = new Intent(activity.getApplicationContext(),
							SignInActivity.class);
					activity.startActivity(in);
				} else {
					// Sign up didn't succeed. Look at the ParseException
					// to figure out what went wrong
					signUpMsg("Account already taken.");
				}
			}
		});
	}

	protected void signUpMsg(String msg) {
		Toast.makeText(activity.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

	private void clearErrors() {
		mEmailEditText.setError(null);
		mUserNameEditText.setError(null);
		mPasswordEditText.setError(null);
		mConfirmPasswordEditText.setError(null);
	}

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon(R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

}
