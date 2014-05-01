package com.az.ratedocs.model;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;

import com.az.ratedocs.entities.DoctorInterface;
import com.az.ratedocs.exceptionhandler.WebServiceException;
import com.az.ratedocs.webservice.PConstants;
import com.az.ratedocs.webservice.ParseSaveCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ParseDoctor implements DoctorInterface {

	private ParseObject doctor;

	/* Create a Parse doctor from an existing doctor retrieved from the server */
	public ParseDoctor(String value) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery(PConstants.PARSE_DOCTOR);
		query.whereEqualTo("ID", value);
		query.getFirstInBackground(new GetCallback<ParseObject>() {
			public void done(ParseObject object, com.parse.ParseException e) {
				if (object == null) {
					Log.d("score", "The getFirst request failed.");
				} else {

					doctor = object;
				}
				
			}
	    });
	}

	public ParseDoctor() {
		
	}
	public ParseDoctor(ParseObject doctor) {
		this.doctor = doctor;
	}

	/* get the title of the doctor */
	@Override
	public String getId() {
		return doctor.getString(PConstants.PARSE_ID);
	}

	/* get the author name of the doctor */
	@Override
	public String getName() {
		return doctor.getString(PConstants.PARSE_NAME);
	}

	/* get the ISBN of the doctor */
	@Override
	public String getSex() {
		return doctor.getString(PConstants.PARSE_SEX);
	}

	/* get the message from the owner of the doctor */
	@Override
	public String getSpecialization() {
		return doctor.getString(PConstants.PARSE_SPECIALIZATION);
	}

	/* get the phone number of the doctor's owner */
	@Override
	public String getAddress() {
		return doctor.getString(PConstants.PARSE_ADDRESS);
	}

	public double getLatitude() {
		return doctor.getDouble(PConstants.PARSE_LATITUDE);
	}

	public double getLongtitude() {
		return doctor.getDouble(PConstants.PARSE_LONGITUDE);
	}

	public String getEmail() {
		return doctor.getString(PConstants.PARSE_EMAIL);
	}

	public String getPhone() {
		return doctor.getString(PConstants.PARSE_PHONE);
	}
	
	public ParseFile getPhoto() {
		return doctor.getParseFile(PConstants.PARSE_PHOTO);
	}


	/* set the doctor title, author name, ISBN, message, phone, emailID, image */
	@Override
	public void setId(String id) {
		doctor.put(PConstants.PARSE_ID, id);
	}

	@Override
	public void setName(String name) {
		doctor.put(PConstants.PARSE_NAME, name);
	}

	@Override
	public void setSex(String sex) {
		doctor.put(PConstants.PARSE_SEX, sex);
	}

	@Override
	public void setSpecialization(String specialization) {
		doctor.put(PConstants.PARSE_SPECIALIZATION, specialization);
	}

	@Override
	public void setAddress(String address) {
		doctor.put(PConstants.PARSE_PHONE, address);
	}

	@Override
	public void setLatitude(double latitude) {
		doctor.put(PConstants.PARSE_EMAIL, latitude);
	}

	@Override
	public void setLongtitude(double longitude) {
		doctor.put(PConstants.PARSE_EMAIL, longitude);
	}

	@Override
	public void setEmail(String email) {
		doctor.put(PConstants.PARSE_EMAIL, email);
	}

	@Override
	public void setRating(ArrayList<Integer> rating) {
		doctor.put(PConstants.PARSE_EMAIL, rating);
	}

	public void setComments(ArrayList<String> comments) {
		doctor.put(PConstants.PARSE_EMAIL, comments);
	}

	@Override
	public void setPhone(String phone) {
		doctor.put(PConstants.PARSE_PHONE, phone);
	}
	
	public void setPhoto(ParseFile file) {
		doctor.put(PConstants.PARSE_PHOTO, file);
	}
	
	/* method to save the doctor to the database */
	public void save(Activity activity) {
		doctor.saveInBackground(new ParseSaveCallback(activity));
	}

	public void delete() throws WebServiceException {
		try {
			doctor.delete();
		} catch (ParseException e) {
			throw new WebServiceException("Failed to delete doctor");
		}
	}

	@Override
	public ArrayList<Integer> getRating() {
		return null;
	}

	@Override
	public ArrayList<String> getComments() {
		return null;
	}
}
