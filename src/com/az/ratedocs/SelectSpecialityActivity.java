package com.az.ratedocs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectSpecialityActivity extends Activity implements View.OnClickListener {
	private Button all;
	private Button cardiology;
	private Button dentistry;
	private Button dermatology;
	private Button fm;
	private Button gs;
	private Button nephrology;
	private Button neurology;
	private Button og;
	private Button oncology;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_speciality);
		all = (Button) findViewById(R.id.button1);
		all.setOnClickListener(this);
		cardiology = (Button) findViewById(R.id.button2);
		cardiology.setOnClickListener(this);
		dentistry = (Button) findViewById(R.id.button3);
		dentistry.setOnClickListener(this);
		dermatology = (Button) findViewById(R.id.button4);
		dermatology.setOnClickListener(this);
		fm = (Button) findViewById(R.id.button5);
		fm.setOnClickListener(this);
		gs = (Button) findViewById(R.id.button6);
		gs.setOnClickListener(this);
		nephrology = (Button) findViewById(R.id.button7);
		nephrology.setOnClickListener(this);
		neurology = (Button) findViewById(R.id.button8);
		neurology.setOnClickListener(this);
		og = (Button) findViewById(R.id.button9);
		og.setOnClickListener(this);
		oncology = (Button) findViewById(R.id.button10);
		oncology.setOnClickListener(this);	
	}

	public void onClick(View v) {
		String name = "";
		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			name = extras.getString("username");
		}
		
		
		switch (v.getId()) {
		case R.id.button1: {
			Intent i1 = new Intent(this, DoctListActivity.class);
			i1.putExtra("string", "All");
			i1.putExtra("username", name);
			startActivity(i1);
			break;
		}
		case R.id.button2: {
			Intent i1 = new Intent(this, DoctListActivity.class);
			i1.putExtra("string", "Cardiology");
			i1.putExtra("username", name);
			startActivity(i1);
			break;
		}
		case R.id.button3: {
			Intent i1 = new Intent(this, DoctListActivity.class);
			i1.putExtra("string", "Dentistry");
			i1.putExtra("username", name);
			startActivity(i1);
			break;
		}
		case R.id.button4: {
			Intent i1 = new Intent(this, DoctListActivity.class);
			i1.putExtra("string", "Dermatology");
			i1.putExtra("username", name);
			startActivity(i1);
			break;
		}
		case R.id.button5: {
			Intent i1 = new Intent(this, DoctListActivity.class);
			i1.putExtra("string", "Family Medicine");
			i1.putExtra("username", name);
			startActivity(i1);
			break;
		}
		case R.id.button6: {
			Intent i1 = new Intent(this, DoctListActivity.class);
			i1.putExtra("string", "General Surgery");
			i1.putExtra("username", name);
			startActivity(i1);
			break;
		}
		case R.id.button7: {
			Intent i1 = new Intent(this, DoctListActivity.class);
			i1.putExtra("string", "Nephrology");
			i1.putExtra("username", name);
			startActivity(i1);
			break;
		}
		case R.id.button8: {
			Intent i1 = new Intent(this, DoctListActivity.class);
			i1.putExtra("string", "Neurology");
			i1.putExtra("username", name);
			startActivity(i1);
			break;
		}
		case R.id.button9: {
			Intent i1 = new Intent(this, DoctListActivity.class);
			i1.putExtra("string", "Obstetrics and Gynecology");
			i1.putExtra("username", name);
			startActivity(i1);
			break;
		}
		case R.id.button10: {
			Intent i1 = new Intent(this, DoctListActivity.class);
			i1.putExtra("string", "Oncology");
			i1.putExtra("username", name);
			startActivity(i1);
			break;
		}
		}
	}
}
