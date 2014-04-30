package com.az.ratedocs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CallActivity extends Activity{
	private ImageButton call;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_profile);
	
	call = (ImageButton) findViewById(R.id.call);

	call.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			Intent Contact = new Intent(Intent.ACTION_CALL);
//			Contact.setData(Uri.parse("tel:"
//					+ doctor.getString("phone")));
			startActivity(Contact);
		}
	});
	}
}
