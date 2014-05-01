package com.az.ratedocs.onclick;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;
import com.az.ratedocs.CommentActivity;
import com.az.ratedocs.R;
import com.az.ratedocs.model.ParseDoctor;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class OnClickDoctorProfile implements OnClickInterface {
	Activity activity;
	private Context context;
	private Button comment;
	private RatingBar ratingBar;
	private TextView text5;
	private ArrayList<Double> scores = new ArrayList<Double>();
	private ArrayList<String> comments = new ArrayList<String>();
	private double totalrating = 0.0;
	private String id;
	private String username;

	/* Associate the on click methods with our buttons */
	public OnClickDoctorProfile(final Activity activity, Context context) {
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
		ratingBar = (RatingBar) activity.findViewById(R.id.ratingBar1);

		ParseDoctor object = new ParseDoctor(value);

		ParseFile fileObject = (ParseFile) object.getPhoto();
		fileObject.getDataInBackground(new GetDataCallback() {
			@Override
			public void done(byte[] data, com.parse.ParseException e) {

				if (e == null) {
					Bitmap bmp = BitmapFactory.decodeByteArray(data, 0,
							data.length);
					ImageView pic;

					pic = (ImageView) activity.findViewById(R.id.image);
					pic.setImageBitmap(bmp);
				} else {
					Toast.makeText(activity.getApplicationContext(),
							e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});

		TextView text1 = (TextView) activity.findViewById(R.id.textView1);
		text1.setText(object.getName());
		TextView text2 = (TextView) activity.findViewById(R.id.textView2);
		text2.setText(object.getSex());
		TextView text3 = (TextView) activity.findViewById(R.id.textView3);
		text3.setText(object.getSpecialization());
		TextView text4 = (TextView) activity.findViewById(R.id.textView4);
		text4.setText(object.getAddress());

		text5 = (TextView) activity.findViewById(R.id.textView5);
		getRatings(id);

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

		gotoComments();
	}

	// get the ratings from the database and calculate the average score
	public void getRatings(String value) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("ratings");
		query.whereEqualTo("doctorID", value);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> ratinglist,
					com.parse.ParseException e) {
				if (e == null) {

					for (ParseObject d : ratinglist) {
						scores.add(d.getDouble("rating"));
						comments.add(d.getString("comment"));
					}

					double sum = 0.0;
					for (int i = 0; i < scores.size(); i++) {
						sum += scores.get(i);
					}

					totalrating = sum / scores.size();
					String total = String.format("%.2f", totalrating);
					text5.setText(total);
				} else {
					Log.d("rating", "The getRatings request failed.");
				}
			}
		});
	}

	public void gotoComments() {
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
