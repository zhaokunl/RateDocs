package com.az.ratedocs.onclick;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.az.ratedocs.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class OnClickForgetPassword implements OnClickInterface {
	Activity activity;
	private EditText et_forgetpassword = null;
	private Button btn_submitforgetpassword = null;
	private String password = null;

	/* Associate the on click methods with our buttons */
	public OnClickForgetPassword(Activity activity) {
		this.activity = activity;
		et_forgetpassword = (EditText) activity.findViewById(R.id.et_forgetpassword);
		btn_submitforgetpassword = (Button) activity.findViewById(R.id.btn_submitforgetpassword);
		
		btn_submitforgetpassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				password = et_forgetpassword.getText().toString();
				checkEmailID();
			}
		});
	}
	protected void checkEmailID() {
		if (TextUtils.isEmpty(password)) {
			et_forgetpassword.setError(activity.getString(R.string.error_field_required));
		} else if (!password.contains("@")) {
			et_forgetpassword.setError(activity.getString(R.string.error_invalid_email));
		}
		else
			forgotPassword(password);
	}

	public void forgotPassword(String email) {
		ParseUser.requestPasswordResetInBackground(email, new UserForgotPasswordCallback());
	}
	
	private class UserForgotPasswordCallback extends RequestPasswordResetCallback{
		public UserForgotPasswordCallback(){
			super();
		}
		
		@Override
		public void done(ParseException e) {
			if (e == null) {
				Toast.makeText(activity.getApplicationContext(), "Successfully sent link to your email for reset Password", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(activity.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			}
		}		
	}
}
