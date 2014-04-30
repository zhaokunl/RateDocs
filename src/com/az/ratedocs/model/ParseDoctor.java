package com.az.ratedocs.model;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
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

public class ParseDoctor implements DoctorInterface {

	private ParseObject doctor;

	/* Create a Parse doctor from an existing doctor retrieved from the server */
	public ParseDoctor(ParseObject doctor) {
		this.doctor = doctor;
	}

	/* Create an empty parse doctor */
	public ParseDoctor() {
		this.doctor = new ParseObject(PConstants.PARSE_DOCTOR);
		doctor.put("User", ParseUser.getCurrentUser());
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

	public ArrayList<Integer> getRating() {
		return rating;
	}

	public ArrayList<String> getComments() {
		return comments;
	}

	/* get the doctor image */
	@Override
	public Bitmap getdoctorImage() {
		ParseFile img = doctor.getParseFile(PConstants.PARSE_PICTURE);
		Bitmap bMap;
		try {
			byte[] data = img.getData();
			bMap = BitmapFactory.decodeByteArray(data, 0, data.length);
		} catch (ParseException e) {
			bMap = null;
		}
		return bMap;
	}

	/* set the doctor title, author name, ISBN, message, phone, emailID, image */
	@Override
	public void setId(String id) {
		doctor.put(PConstants.PARSE_TITLE, title);
	}

	@Override
	public void setName(String name) {
		doctor.put(PConstants.PARSE_AUTHOR, authorName);
	}

	@Override
	public void setSex(String sex) {
		doctor.put(PConstants.PARSE_ISBN, ISBN);
	}

	@Override
	public void setSpecialization(String specialization) {
		doctor.put(PConstants.PARSE_NOTE, message);
	}

	@Override
	public void setAddress(String address) {
		doctor.put(PConstants.PARSE_PHONE, phone);
	}

	@Override
	public void setLatitude(double latitude) {
		doctor.put(PConstants.PARSE_EMAIL, email);
	}

	@Override
	public void setLongtitude(double longtitude) {
		doctor.put(PConstants.PARSE_EMAIL, email);
	}

	@Override
	public void setEmail(String email) {
		doctor.put(PConstants.PARSE_EMAIL, email);
	}

	@Override
	public void setRating(ArrayList<Integer> rating) {
		doctor.put(PConstants.PARSE_EMAIL, email);
	}

	public void setComments(ArrayList<String> comments) {
		doctor.put(PConstants.PARSE_EMAIL, email);
	}

	@Override
	public void setdoctorImage(Bitmap bMap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bMap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] array = stream.toByteArray();
		ParseFile file = new ParseFile("smartlendTemp.jpg", array);
		file.saveInBackground();
		doctor.put(PConstants.PARSE_PICTURE, file);
	}

	/* method to save the doctor to the database */
	@Override
	public void save(Activity activity) {
		doctor.saveInBackground(new ParseSaveCallback(activity));
	}

	@Override
	public void delete() throws WebServiceException {
		try {
			doctor.delete();
		} catch (ParseException e) {
			throw new WebServiceException("Failed to delete doctor");
		}
	}

	public void setdoctorImage(Bitmap bMap) {

	}

	public Bitmap getBookImage() {
		return null;
	}

	public void setBookImage(Bitmap bMap) {

	}

	public void setPhone(String phone) {

	}
}
