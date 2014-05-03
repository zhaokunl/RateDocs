package com.az.ratedocs.display;

import java.util.ArrayList;
import java.util.List;
import com.az.ratedocs.R;
import com.az.ratedocs.model.ParseDoctor;
import com.az.ratedocs.webservice.PConstants;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class DoctorProfileDisplay extends DisplayHelper {

	private Activity activity;
	private RatingBar ratingBar;
	private String value;
	TextView text_rating;
	private String id;
	private String username;
	TextView text_name;
	TextView text_gender;
	TextView text_speciality;
	TextView text_address;
	ParseFile fileObject;
	ImageView pic;
	ParseObject doctor;
	Bitmap bmp;

	public DoctorProfileDisplay(Activity activity, String value) {
		super(activity);
		this.activity = activity;
		this.value = value;

		ParseQuery<ParseObject> query = ParseQuery
				.getQuery(PConstants.PARSE_DOCTOR);
		query.whereEqualTo("ID", value);

		query.getFirstInBackground(new GetCallback<ParseObject>() {
			public void done(ParseObject object, com.parse.ParseException e) {
				if (object == null) {
					Log.d("score", "The getFirst request failed.");
				} else {
					doctor = object;
					display();
					getTotalRating();
					id = object.getString("ID");
					username = object.getString("name");
				}
			}
		});
	}

	/* this method is used to display all the auto-generated views */
	public void display() {
		Runtime.getRuntime().gc();
		if (bmp != null && !bmp.isRecycled()) {
			bmp.recycle();
			bmp = null;
		}
		System.gc();

		ParseDoctor object = new ParseDoctor(doctor);
		Log.d("activity is whwat", activity.getClass().getName());
		text_name = (TextView) activity.findViewById(R.id.text_name);
		text_name.setText(object.getName());
		text_gender = (TextView) activity.findViewById(R.id.text_gender);
		text_gender.setText(object.getSex());
		text_speciality = (TextView) activity
				.findViewById(R.id.text_speciality);
		text_speciality.setText(object.getSpecialization());
		text_address = (TextView) activity.findViewById(R.id.text_address);
		text_address.setText(object.getAddress());

		try {
			fileObject = (ParseFile) object.getPhoto();
			fileObject.getDataInBackground(new GetDataCallback() {

				@Override
				public void done(byte[] data, com.parse.ParseException e) {

					if (e == null) {
						try {
							bmp = BitmapFactory.decodeByteArray(data, 0,
									data.length);
						} catch (OutOfMemoryError error) {

						}

						pic = (ImageView) activity.findViewById(R.id.image);
						pic.setImageBitmap(bmp);

					} else {
						Toast.makeText(activity.getApplicationContext(),
								e.getMessage(), Toast.LENGTH_LONG).show();
					}
				}
			});
		} catch (Exception e) {

		}

		ratingBar = (RatingBar) activity.findViewById(R.id.ratingBar1);
		// Set ChangeListener to Rating Bar
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {

				ParseObject newrating = new ParseObject("ratings");
				newrating.put("rating", rating);
				newrating.put("doctorID", id);
				newrating.put("username", username);
				newrating.saveInBackground();

				Toast.makeText(activity.getApplicationContext(),
						"Your Selected Ratings  : " + String.valueOf(rating),
						Toast.LENGTH_LONG).show();
			}
		});
	}

	public void getTotalRating() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("ratings");
		query.whereEqualTo("doctorID", value);

		query.findInBackground(new FindCallback<ParseObject>() {

			public void done(List<ParseObject> ratinglist,
					com.parse.ParseException e) {
				if (e == null) {
					ArrayList<Double> scores = new ArrayList<Double>();
					ArrayList<String> comments = new ArrayList<String>();

					for (ParseObject d : ratinglist) {
						double rating = d.getDouble("rating");
						if (rating > 0) {
							scores.add(rating);
						}
						String comment = d.getString("comment");
						if (comment != null && comment.trim().length() > 0) {
							comments.add(comment);
						}
					}

					double sum = 0.0;
					for (int i = 0; i < scores.size(); i++) {
						sum += scores.get(i);
					}

					String averageRating = String.format("%.2f",
							sum / scores.size());
					text_rating = (TextView) activity
							.findViewById(R.id.text_rating);
					text_rating.setText(averageRating);

				} else {
					Log.d("rating", "The getRatings request failed.");
				}
			}
		});
	}
}
