package com.az.ratedocs;

import com.az.ratedocs.onclick.OnClickFactory;
import com.az.ratedocs.onclick.OnClickInterface;
import com.az.ratedocs.webservice.PConstants;
import com.parse.Parse;
import com.parse.ParseAnalytics;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		
		@SuppressWarnings("unused")
		OnClickInterface onClickInterface = OnClickFactory.getOnClick(this, MainActivity.this);
	}
}
