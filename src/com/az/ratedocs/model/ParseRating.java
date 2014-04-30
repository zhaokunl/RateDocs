package com.az.ratedocs.model;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.az.ratedocs.entities.DoctorInterface;
import com.az.ratedocs.exceptionhandler.WebServiceException;
import com.az.ratedocs.webservice.PConstants;
import com.az.ratedocs.webservice.ParseSaveCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseRating {

	private List<ParseObject> rating;

	/* Create a Parse doctor from an existing doctor retrieved from the server */
	public ParseRating(List<ParseObject> rating) {
		this.rating = rating;
	}

//	/* Create an empty parse doctor */
//	public ParseRating() {
//		this.rating = new ParseObject(PConstants.PARSE_RATING);
//		rating.put("User", ParseUser.getCurrentUser());
//	}
//
//	public ArrayList<Double> getRating() {
//		ArrayList<Double> r = new ArrayList<Double>();
//		for (ParseObject e : rating) {
//			r.add(e.getDouble(PConstants.PARSE_RATING));
//		}
//		return r;
//	}
//
//	public void setRating(ArrayList<Integer> rating) {
//		this.rating = rating;
//	}
//
//	public ArrayList<String> getComments() {
//		return comments;
//	}
//
//	public void setComments(ArrayList<String> comments) {
//		this.comments = comments;
//	}
//
//	/* get the title of the doctor */
//	public String getRatings() {
//		return doctor.getString(PConstants.PARSE_ID);
//	}
//
//	/* get the author name of the doctor */
//	public String getName() {
//		return doctor.getString(PConstants.PARSE_NAME);
//	}
//
//	public void setId(String id) {
//		doctor.put(PConstants.PARSE_TITLE, title);
//	}
//
//	public void setName(String name) {
//		doctor.put(PConstants.PARSE_AUTHOR, authorName);
//	}

}
