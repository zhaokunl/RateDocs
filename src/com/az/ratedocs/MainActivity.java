package com.az.ratedocs;

import java.util.Locale;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends Activity{
	private Button btn_LoginIn = null;
	private Button btn_SignUp = null;
	private Button btn_ForgetPass = null;
	private EditText mUserNameEditText;
	private EditText mPasswordEditText;
	private String name;

	// flag for Internet connection status
	Boolean isInternetPresent = false;
	// Connection detector class
	ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		
		//Initializing Parse SDK
		onCreateParse();
		//Calling ParseAnalytics to see Analytics of our app
		ParseAnalytics.trackAppOpened(getIntent());
		
		// creating connection detector class instance
		cd = new ConnectionDetector(getApplicationContext());

		btn_LoginIn = (Button) findViewById(R.id.btn_login);
		btn_SignUp = (Button) findViewById(R.id.btn_signup);
		btn_ForgetPass = (Button) findViewById(R.id.btn_ForgetPass);
		mUserNameEditText = (EditText) findViewById(R.id.username);
		mPasswordEditText = (EditText) findViewById(R.id.password);

		btn_LoginIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// get Internet status
				isInternetPresent = cd.isConnectingToInternet();
				// check for Internet status
				if (isInternetPresent) {
					// Internet Connection is Present
					// make HTTP requests
					attemptLogin();
				} else {
					// Internet connection is not present
					// Ask user to connect to Internet
					showAlertDialog(MainActivity.this, "No Internet Connection",
							"You don't have internet connection.", false);
				}
			}
		});

		btn_SignUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent in =  new Intent(MainActivity.this,CreateAccountActivity.class);
				startActivity(in);
			}
		});
		
		btn_ForgetPass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in =  new Intent(MainActivity.this,ForgetPasswordActivity.class);
				startActivity(in);
			}
		});
	}

	public void onCreateParse() { 
		Parse.initialize(this, "FV8nEMjH46KbLhjAThHk7R5AJE3rm14I9rnt1B18", "F8iVzlKmbXHeRZuSnp8pY0DuuCeGdhi6fUyrL4MC"); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_forgot_password:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void attemptLogin() {

		clearErrors();

		// Store values at the time of the login attempt.
		String username = mUserNameEditText.getText().toString();
		String password = mPasswordEditText.getText().toString();

		name = username;
		
		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(password)) {
			mPasswordEditText.setError(getString(R.string.error_field_required));
			focusView = mPasswordEditText;
			cancel = true;
		} else if (password.length() < 4) {
			mPasswordEditText.setError(getString(R.string.error_invalid_password));
			focusView =mPasswordEditText;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(username)) {
			mUserNameEditText.setError(getString(R.string.error_field_required));
			focusView = mUserNameEditText;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// perform the user login attempt.
			login(username.toLowerCase(Locale.getDefault()), password);
		}
	}

	private void login(String lowerCase, String password) {
		ParseUser.logInInBackground(lowerCase, password, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException e) {
				if(e == null)
					loginSuccessful();
				else
					loginUnSuccessful();
			}
		});
	}

	protected void loginSuccessful() {
		Intent in =  new Intent(MainActivity.this,SelectSpecialityActivity.class);
		in.putExtra("username", name);
		startActivity(in);
	}
	protected void loginUnSuccessful() {
		Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
		showAlertDialog(MainActivity.this,"Login", "Username or Password is invalid.", false);
	}

	private void clearErrors(){
		mUserNameEditText.setError(null);
		mPasswordEditText.setError(null);
	}

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
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
