package com.az.ratedocs;

/* This activity shows a list of doctors with basic information including doctor name, 
 * distance from the current user and doctor phone number.
 * */

import android.app.Activity;
import android.os.Bundle;
import com.az.ratedocs.onclick.OnClickFactory;
import com.az.ratedocs.onclick.OnClickInterface;

public class DoctListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_items);
		
		@SuppressWarnings("unused")
		OnClickInterface onClickInterface = OnClickFactory.getOnClick(this, DoctListActivity.this);
	}
}