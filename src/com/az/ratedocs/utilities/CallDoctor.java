package com.az.ratedocs.utilities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import com.az.ratedocs.R;
import com.az.ratedocs.model.ParseDoctor;

public class CallDoctor {

	private Activity activity;
	private ImageButton call;
	private ParseDoctor doctor;
	private String value;

	public CallDoctor(Activity activity, String value) {
		this.activity = activity;
		this.value = value;
		call();
	}

	public void call() {
		doctor = new ParseDoctor(value);

		call = (ImageButton) activity.findViewById(R.id.call);
		call.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent Contact = new Intent(Intent.ACTION_CALL);
				Contact.setData(Uri.parse("tel:" + doctor.getPhone()));
				activity.startActivity(Contact);
			}
		});

	}
}
