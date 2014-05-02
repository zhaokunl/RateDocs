package com.az.ratedocs;

import android.app.Activity;
import android.os.Bundle;

import com.az.ratedocs.onclick.OnClickFactory;
import com.az.ratedocs.onclick.OnClickInterface;
import com.az.ratedocs.webservice.PConstants;
import com.parse.Parse;

public class DoctListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_items);
		
		@SuppressWarnings("unused")
		OnClickInterface onClickInterface = OnClickFactory.getOnClick(this, DoctListActivity.this);
	}
}