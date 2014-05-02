package com.az.ratedocs.onclick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.az.ratedocs.DoctListActivity;
import com.az.ratedocs.R;

public class OnClickSpeciality implements OnClickInterface, View.OnClickListener {
	Activity activity;
    private Context context;
    
    Button all;
	Button cardiology;
	Button dentistry;
	Button dermatology;
	Button fm;
	Button gs;
	Button nephrology;
	Button neurology;
	Button og;
	Button oncology;
	
	/* Associate the on click methods with our buttons */
	public OnClickSpeciality(Activity activity, Context context) {
		this.activity = activity;
		this.context = context;
		
		all = (Button) activity.findViewById(R.id.button1);
		all.setOnClickListener(this);
		cardiology = (Button) activity.findViewById(R.id.button2);
		cardiology.setOnClickListener(this);
		dentistry = (Button) activity.findViewById(R.id.button3);
		dentistry.setOnClickListener(this);
		dermatology = (Button) activity.findViewById(R.id.button4);
		dermatology.setOnClickListener(this);
		fm = (Button) activity.findViewById(R.id.button5);
		fm.setOnClickListener(this);
		gs = (Button) activity.findViewById(R.id.button6);
		gs.setOnClickListener(this);
		nephrology = (Button) activity.findViewById(R.id.button7);
		nephrology.setOnClickListener(this);
		neurology = (Button) activity.findViewById(R.id.button8);
		neurology.setOnClickListener(this);
		og = (Button) activity.findViewById(R.id.button9);
		og.setOnClickListener(this);
		oncology = (Button) activity.findViewById(R.id.button10);
		oncology.setOnClickListener(this);	
	}
	
	@Override
	public void onClick(View v) {
		String name = "";
		Bundle extras = activity.getIntent().getExtras();

		if (extras != null) {
			name = extras.getString("username");
		}
		
		switch (v.getId()) {
		case R.id.button1: {
			Log.d("entered button 1 branch", "true");
			Intent i1 = new Intent(context, DoctListActivity.class);
			i1.putExtra("string", "All");
			i1.putExtra("username", name);
			activity.startActivity(i1);
			break;
		}
		case R.id.button2: {
			Intent i1 = new Intent(context, DoctListActivity.class);
			i1.putExtra("string", "Cardiology");
			i1.putExtra("username", name);
			activity.startActivity(i1);
			break;
		}
		case R.id.button3: {
			Intent i1 = new Intent(context, DoctListActivity.class);
			i1.putExtra("string", "Dentistry");
			i1.putExtra("username", name);
			activity.startActivity(i1);
			break;
		}
		case R.id.button4: {
			Intent i1 = new Intent(context, DoctListActivity.class);
			i1.putExtra("string", "Dermatology");
			i1.putExtra("username", name);
			activity.startActivity(i1);
			break;
		}
		case R.id.button5: {
			Intent i1 = new Intent(context, DoctListActivity.class);
			i1.putExtra("string", "Family Medicine");
			i1.putExtra("username", name);
			activity.startActivity(i1);
			break;
		}
		case R.id.button6: {
			Intent i1 = new Intent(context, DoctListActivity.class);
			i1.putExtra("string", "General Surgery");
			i1.putExtra("username", name);
			activity.startActivity(i1);
			break;
		}
		case R.id.button7: {
			Intent i1 = new Intent(context, DoctListActivity.class);
			i1.putExtra("string", "Nephrology");
			i1.putExtra("username", name);
			activity.startActivity(i1);
			break;
		}
		case R.id.button8: {
			Intent i1 = new Intent(context, DoctListActivity.class);
			i1.putExtra("string", "Neurology");
			i1.putExtra("username", name);
			activity.startActivity(i1);
			break;
		}
		case R.id.button9: {
			Intent i1 = new Intent(context, DoctListActivity.class);
			i1.putExtra("string", "Obstetrics and Gynecology");
			i1.putExtra("username", name);
			activity.startActivity(i1);
			break;
		}
		case R.id.button10: {
			Intent i1 = new Intent(context, DoctListActivity.class);
			i1.putExtra("string", "Oncology");
			i1.putExtra("username", name);
			activity.startActivity(i1);
			break;
		}
		}
	}
}
