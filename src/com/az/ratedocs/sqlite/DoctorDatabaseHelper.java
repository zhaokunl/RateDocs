package com.az.ratedocs.sqlite;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.az.ratedocs.model.Doctor;

public class DoctorDatabaseHelper extends SQLiteOpenHelper {

	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/com.example.ratedocs/databases/";
	private static String DB_NAME = "doctor_profile";
	private static String TABLE_DOCTOR = "doctor";
	private static String TABLE_RATING = "rating";
	
	private SQLiteDatabase myDataBase;

	private final Context myContext;

	public DoctorDatabaseHelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}
	
	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {
			this.getReadableDatabase();
			try {
				copyDataBase();

			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {
			// database does't exist yet.
		}

		if (checkDB != null) {
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}

	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {
		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public List<Doctor> getDoctorList(String specialization) {
		List<Doctor> doctorlist = new LinkedList<Doctor>();
		String query;

		if (specialization == "All") {
			query = "SELECT  * FROM " + TABLE_DOCTOR;
		} else {
			query = "SELECT  * FROM " + TABLE_DOCTOR + " WHERE Specialization = '" + specialization + "'";
		}
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
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
