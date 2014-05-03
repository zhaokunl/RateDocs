package com.az.ratedocs;

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
