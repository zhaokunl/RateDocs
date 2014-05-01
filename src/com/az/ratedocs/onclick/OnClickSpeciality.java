package com.az.ratedocs.onclick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.az.ratedocs.DoctListActivity;
import com.az.ratedocs.R;

public class OnClickSpeciality implements OnClickInterface, View.OnClickListener {
	Activity activity;
    private Context context;
	
	/* Associate the on click methods with our buttons */
	public OnClickSpeciality(Activity activity, Context context) {
		this.activity = activity;
		this.context = context;
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
