package com.az.ratedocs.utilities;

/* This class enable users to select a time and email the doctor for an appointment.
 * */

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.az.ratedocs.R;
import com.az.ratedocs.model.ParseDoctor;
import com.az.ratedocs.webservice.PConstants;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class EmailDoctor {
	private Spinner s1, s2;
	private Activity activity;
	private Button request;
	private ParseObject doc;
	private String value;
	private EditText t;

	public EmailDoctor(Activity activity, String value) {
		this.activity = activity;
		this.value = value;
		s1 = (Spinner) activity.findViewById(R.id.spinner1);
		s2 = (Spinner) activity.findViewById(R.id.spinner2);
		t = (EditText) activity.findViewById(R.id.editText6);

		ParseQuery<ParseObject> query = ParseQuery
				.getQuery(PConstants.PARSE_DOCTOR);
		query.whereEqualTo("ID", value);

		query.getFirstInBackground(new GetCallback<ParseObject>() {
			public void done(ParseObject object, com.parse.ParseException e) {
				if (object == null) {
					Log.d("score", "The getFirst request failed.");
				} else {
					doc = object;
					requestAppointment();
				}
			}
		});
	}

	public void requestAppointment() {

		request = (Button) activity.findViewById(R.id.req);
		request.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ParseDoctor d = new ParseDoctor(doc);

				String subject = Constants.SUBJECT;
				String month = s2.getSelectedItem().toString();
				String day = s1.getSelectedItem().toString();
				String hour = t.getText().toString();

				String message = "Dear "
						+ d.getName()
						+ " :\n\n"
						+ "I would like to schedule a personal appointment with you.\n"
						+ "Would you be available at:\n" + "Time: " + hour
						+ "\nMonth: " + month + "\nDate: " + day + "\n"
						+ "Please let me know. Thanks.\n\n" + "Best regards.\n\n"
						+ ParseUser.getCurrentUser().getUsername();

				Intent intentEmail = new Intent(Intent.ACTION_SEND);
				intentEmail.putExtra(Intent.EXTRA_EMAIL,
						new String[] { d.getEmail() });
				intentEmail.putExtra(Intent.EXTRA_SUBJECT, subject);
				intentEmail.putExtra(Intent.EXTRA_TEXT, message);
				intentEmail.setType(Constants.EMAIL_TYPE);
				Log.d("message", message);
				activity.startActivity(Intent.createChooser(intentEmail,
						Constants.EMAIL_MESSAGE));
			}
		});
	}
}
