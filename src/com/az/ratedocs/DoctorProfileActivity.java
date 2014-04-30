package com.az.ratedocs;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class DoctorProfileActivity extends Activity implements
		OnItemSelectedListener {

	private ImageButton call;
	private Button request;
	private Button comment;
	private EditText t;
	private Spinner s1, s2;
	private RatingBar ratingBar;
	private ParseObject doctor;
	private TextView text5;
	private String selected_day, selected_month;
	private List<String> days = new ArrayList<String>();
	private List<String> months = new ArrayList<String>();
	private ArrayList<Double> scores = new ArrayList<Double>();
	private ArrayList<String> comments = new ArrayList<String>();
	private double totalrating = 0.0;
	private String id;
	private String username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_profile);

		String value = "";
		String name = "";
		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			value = extras.getString("id");
			name = extras.getString("username");
		}

		id = value;
		username = name;

		ratingBar = (RatingBar) findViewById(R.id.ratingBar1);

		ParseQuery<ParseObject> query = ParseQuery.getQuery("doctor_profile");
		query.whereEqualTo("ID", value);
		query.getFirstInBackground(new GetCallback<ParseObject>() {
			public void done(ParseObject object, com.parse.ParseException e) {
				if (object == null) {
					Log.d("score", "The getFirst request failed.");
				} else {

					doctor = object;

					ParseFile fileObject = (ParseFile) object.get("photo");
					fileObject.getDataInBackground(new GetDataCallback() {
						@Override
						public void done(byte[] data, com.parse.ParseException e) {

							if (e == null) {
								Bitmap bmp = BitmapFactory.decodeByteArray(
										data, 0, data.length);
								ImageView pic;

								pic = (ImageView) findViewById(R.id.image);
								pic.setImageBitmap(bmp);
							} else {
								Toast.makeText(getApplicationContext(),
										e.getMessage(), Toast.LENGTH_LONG)
										.show();
							}
						}
					});

					TextView text1 = (TextView) findViewById(R.id.textView1);
					text1.setText(object.getString("name"));
					TextView text2 = (TextView) findViewById(R.id.textView2);
					text2.setText(object.getString("sex"));
					TextView text3 = (TextView) findViewById(R.id.textView3);
					text3.setText(object.getString("specialization"));
					TextView text4 = (TextView) findViewById(R.id.textView4);
					text4.setText(object.getString("address"));

					text5 = (TextView) findViewById(R.id.textView5);
					getRatings(id);

					t = (EditText) findViewById(R.id.editText6);
					

					s1 = (Spinner) findViewById(R.id.spinner1);
					s2 = (Spinner) findViewById(R.id.spinner2);

					appointment();

					// Set ChangeListener to Rating Bar
					ratingBar
							.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
								public void onRatingChanged(
										RatingBar ratingBar, float rating,
										boolean fromUser) {

									ParseObject newrating = new ParseObject(
											"ratings");
									newrating.put("rating", rating);
									newrating.put("doctorID", id);
									newrating.put("username", username);
									newrating.saveInBackground();

									Toast.makeText(
											getApplicationContext(),
											"Your Selected Ratings  : "
													+ String.valueOf(rating),
											Toast.LENGTH_LONG).show();
								}
							});

					gotoComments();

					Log.d("score", "Retrieved the object.");
				}
			}
		});
	}

	// get the ratings from the database and calculate the average score
	public void getRatings(String value) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("ratings");
		query.whereEqualTo("doctorID", value);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> ratinglist,
					com.parse.ParseException e) {
				if (e == null) {
					Log.d("why", "not that into u");

					for (ParseObject d : ratinglist) {
						scores.add(d.getDouble("rating"));
						comments.add(d.getString("comment"));
					}

					double sum = 0.0;
					for (int i = 0; i < scores.size(); i++) {
						sum += scores.get(i);
					}

					Log.d("cry", Double.toString(sum));

					totalrating = sum / scores.size();
					String total = String.format("%.2f", totalrating);
					text5.setText(total);
					Log.d("cryagain", Double.toString(totalrating));
				} else {
					Log.d("rating", "The getRatings request failed.");
				}
			}
		});
	}

	// send email to the doctor for an appointment
	public void appointment() {
		for (int i = 1; i <= 31; i++) {
			days.add("" + i);
		}

		for (int i = 1; i <= 12; i++) {
			months.add("" + i);
		}

		ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, months);
		adapter_month
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s1.setAdapter(adapter_month);
		s1.setOnItemSelectedListener(this);

		ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, days);
		adapter_year
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s2.setAdapter(adapter_year);
		s2.setOnItemSelectedListener(this);

		request = (Button) findViewById(R.id.req);
		request.setOnClickListener(new View.OnClickListener() {

			String subject = "Appointment";
			String month = s1.getSelectedItem().toString();
			String day = s2.getSelectedItem().toString();
			String message = month + " " + day + " " + t.getText().toString();

			@Override
			public void onClick(View arg0) {
				Intent intentEmail = new Intent(Intent.ACTION_SEND);
				intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[] { doctor
						.getString("email").toString() });
				intentEmail.putExtra(Intent.EXTRA_SUBJECT, subject.toString());
				intentEmail.putExtra(Intent.EXTRA_TEXT, message.toString());
				intentEmail.setType("message/rfc822");
				startActivity(Intent.createChooser(intentEmail,
						"Choose an email provider :"));
			}
		});
	}

	public void gotoComments() {
		comment = (Button) findViewById(R.id.comment);
		comment.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i1 = new Intent(DoctorProfileActivity.this,
						CommentActivity.class);
				i1.putExtra("id", id);
				i1.putExtra("username", username);
				startActivity(i1);
			}
		});
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
			long arg3) {
		switch (arg0.getId()) {
		case R.id.spinner1:
			selected_month = arg0.getItemAtPosition(pos).toString();

			break;
		case R.id.spinner2:
			selected_day = arg0.getItemAtPosition(pos).toString();

			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
}
