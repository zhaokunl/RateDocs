package com.az.ratedocs;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.az.ratedocs.onclick.OnClickFactory;
import com.az.ratedocs.onclick.OnClickInterface;
import com.az.ratedocs.webservice.PConstants;
import com.parse.Parse;
import com.parse.ParseAnalytics;

public class SignInActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		
		@SuppressWarnings("unused")
		OnClickInterface onClickInterface = OnClickFactory.getOnClick(this, SignInActivity.this);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
