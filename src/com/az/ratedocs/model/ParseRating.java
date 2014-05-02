package com.az.ratedocs.model;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.az.ratedocs.entities.RatingInterface;
import com.az.ratedocs.webservice.PConstants;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ParseRating implements RatingInterface {

	private String averageRating;
	private ParseObject doctor_rating;
	private String value;

	/* Create a Parse doctor from an existing doctor retrieved from the server */
	public ParseRating(ParseObject doctor_rating) {
		this.doctor_rating = doctor_rating;
	}
	
	public ParseRating(String value) {
		this.value = value;
	}

	/* Create an empty parse doctor */
	public ParseRating() {
		this.doctor_rating = new ParseObject(PConstants.PARSE_RATING);
		doctor_rating.put("username", ParseUser.getCurrentUser());
	}

	public Double getRating() {
		return doctor_rating.getDouble(PConstants.PARSE_RATING);
	}

	public String getComment() {
		return doctor_rating.getString(PConstants.PARSE_RATING_COMMENT);
	}

	public String getDoctorID() {
		return doctor_rating.getString(PConstants.PARSE_RATING_DOCID);
	}

	public String getUserName() {
		return doctor_rating.getString(PConstants.PARSE_USERNAME);
	}

	public void setRating(Double score) {
		doctor_rating.put(PConstants.PARSE_RATING, score);
	}

	public void setComment(String comment) {
		doctor_rating.put(PConstants.PARSE_RATING_COMMENT, comment);
	}

	public void setDoctorID(String id) {
		doctor_rating.put(PConstants.PARSE_RATING_DOCID, id);
	}

	public void setUserName(String name) {
		doctor_rating.put(PConstants.PARSE_USERNAME, name);
	}

	public String getTotalRating(String value) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("ratings");
		query.whereEqualTo("doctorID", value);

		query.findInBackground(new FindCallback<ParseObject>() {
			
			public void done(List<ParseObject> ratinglist,
					com.parse.ParseException e) {
				if (e == null) {
					ArrayList<Double> scores = new ArrayList<Double>();
					ArrayList<String> comments = new ArrayList<String>();

					for (ParseObject d : ratinglist) {
						scores.add(d.getDouble("rating"));
						comments.add(d.getString("comment"));
					}

					double sum = 0.0;
					for (int i = 0; i < scores.size(); i++) {
						sum += scores.get(i);
					}

					averageRating = String.format("%.2f", sum / scores.size());
				} else {
					Log.d("rating", "The getRatings request failed.");
				}
			}
		});
		return averageRating;
	}
}
