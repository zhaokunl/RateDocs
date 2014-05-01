package com.az.ratedocs.onclick;

import com.az.ratedocs.CommentActivity;
import com.az.ratedocs.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnClickDoctorProfile implements OnClickInterface {
	private Activity activity;
	private Context context;
	private Button comment;
	private String id;
	private String username;

	/* Associate the on click methods with our buttons */
	public OnClickDoctorProfile(final Activity activity, final Context context) {
		this.activity = activity;
		this.context = context;

		String value = "";
		String name = "";
		Bundle extras = activity.getIntent().getExtras();

		if (extras != null) {
			value = extras.getString("id");
			name = extras.getString("username");
		}

		id = value;
		username = name;
		
		comment = (Button) activity.findViewById(R.id.comment);
		comment.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i1 = new Intent(context, CommentActivity.class);
				i1.putExtra("id", id);
				i1.putExtra("username", username);
				activity.startActivity(i1);
			}
		});
	}
}
