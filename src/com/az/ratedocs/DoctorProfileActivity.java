package com.az.ratedocs;

/* This activity displays the detailed profile of a selected doctor retrived from the 
 * web databse. It also enables the users to call the doctor, email the doctor for an 
 * an appointment, as well as rate the doctors and go to the comments page.
 * */

import android.app.Activity;
import android.os.Bundle;
import com.az.ratedocs.onclick.OnClickFactory;
import com.az.ratedocs.onclick.OnClickInterface;

public class DoctorProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_profile);
		
		@SuppressWarnings("unused")
		OnClickInterface onClickInterface = OnClickFactory.getOnClick(this, DoctorProfileActivity.this);
	
	}
}
