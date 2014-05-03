package com.az.ratedocs;

/* This activity displays a list of doctors with basic contact info which are all taken 
 * from the local database. It enables the users to get doctor contacts even without 
 * Internet connection.
 * */

import android.app.Activity;
import android.os.Bundle;
import com.az.ratedocs.display.DisplayFactory;
import com.az.ratedocs.display.DisplayHelper;

public class LocalDoctorListActivity extends Activity{
	
	private String value;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_localdoctor_items);
		
		DisplayHelper bd = DisplayFactory.getDisplayHelper(this, value, LocalDoctorListActivity.this);
		bd.display();
	}
}
