package com.az.ratedocs.sqlite;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.az.ratedocs.model.Doctor;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DoctorDatabaseHelper extends SQLiteAssetHelper {

	// The Android's default system path of your application database.
	private static final String DATABASE_NAME = "doctor_profile.db";
	private static final int DATABASE_VERSION = 1;
	
	private static String TABLE_DOCTOR = "doctor";
	private static String TABLE_RATING = "rating";
	

	public DoctorDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public List<Doctor> getDoctorList() {
		SQLiteDatabase datab = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		List<Doctor> doctorlist = new LinkedList<Doctor>();

		//String [] sqlSelect = {"_id", "1"}; 
		String sqlTables = TABLE_DOCTOR;

		qb.setTables(sqlTables);
		Cursor cursor = qb.query(datab, null, null, null,
				null, null, null);

		Doctor doctor = null;
		if (cursor.moveToFirst()) {
			do {
				doctor = new Doctor();
				doctor.setId(cursor.getString(0));
				doctor.setName(cursor.getString(1));
				doctor.setSex(cursor.getString(2));
				doctor.setSpecialization(cursor.getString(3));
				doctor.setAddress(cursor.getString(4));
				doctor.setLatitude(Double.parseDouble(cursor.getString(5)));
				doctor.setLongtitude(Double.parseDouble(cursor.getString(6)));
				doctor.setEmail(cursor.getString(7));
				doctor.setPhone(cursor.getString(8));
				doctorlist.add(doctor);
			} while (cursor.moveToNext());
		}
		return doctorlist;
	}

	public Doctor getDoctorProfile(String id) {

		String query = "SELECT  * FROM " + TABLE_RATING + "WHERE _id =" + id;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		Doctor doctor = null;
		if (cursor.moveToFirst()) {
			do {
				doctor = new Doctor();
				doctor.addRatings(Integer.parseInt(cursor.getString(0)));
				doctor.addComments(cursor.getString(1));
			} while (cursor.moveToNext());
		}
		return doctor;
	}
}
