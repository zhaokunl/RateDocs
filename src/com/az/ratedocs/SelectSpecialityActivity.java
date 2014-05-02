package com.az.ratedocs;

import com.az.ratedocs.onclick.OnClickFactory;
import com.az.ratedocs.onclick.OnClickInterface;
import com.az.ratedocs.webservice.PConstants;
import com.parse.Parse;

import android.app.Activity;
import android.os.Bundle;

public class SelectSpecialityActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_speciality);
			
		@SuppressWarnings("unused")
		OnClickInterface onClickInterface = OnClickFactory.getOnClick(this, SelectSpecialityActivity.this);
	}
}
