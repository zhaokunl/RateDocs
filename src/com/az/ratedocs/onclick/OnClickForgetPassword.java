package com.az.ratedocs.onclick;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.az.ratedocs.R;
import com.az.ratedocs.entities.EntitiesHandler;
import com.az.ratedocs.entities.HandlerFactory;


public class OnClickForgetPassword implements OnClickInterface{
	Activity activity;
	
	/* associate the on click methods with the buttons */
	public OnClickForgetPassword(Activity activity) {
		this.activity = activity;
		Button button = (Button) activity.findViewById(R.id.btn_submitforgetpassword);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				clickedRecoverPassword();
			}
			
		});
	}

	/* Use the input email address to email the user instructions on how to change their password */
	public void clickedRecoverPassword() {
		EntitiesHandler entityHandler = HandlerFactory.getHandler(activity);
        String email = ((TextView) activity.findViewById(R.id.et_forgetpassword)).getText().toString(); 
        entityHandler.resetPassword(email, activity);
	}
}
