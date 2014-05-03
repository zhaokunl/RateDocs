package com.az.ratedocs.model;

/* Parse web database rating object.
 * */

import com.az.ratedocs.entities.RatingInterface;
import com.az.ratedocs.webservice.PConstants;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseRating implements RatingInterface {

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
}
