package com.az.ratedocs.sqlite;

/* This helper handles all the local user database related methods.
 * */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper {

	//initial declarations
	public static final String KEY_ID="_id";
	public static final String pat_user="patientusername";
	public static final String pat_name="patname";
	public static final String pat_pass="patpass";
	public static final String pat_cpass="patconfirmpass";
	public static final String pat_email="patemail";

	//table and database initilizations
	private static final String TABLE_NAME="patientTable";
	private static final String DATA_BASE_NAME="patientDatabase";
	private static final int DATABASE_VERSION=1;

	//table creation
	public static final String TABLE_CREATE="CREATE TABLE " + TABLE_NAME + " (" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			pat_user +" TEXT NOT NULL, " +
			pat_name + " TEXT NOT NULL, "+
			pat_email + " TEXT NOT NULL, "+
			pat_pass + " TEXT NOT NULL, "+
			pat_cpass + " TEXT NOT NULL);";


	private DataBaseHelper dbhelper;
	private Context ctx;
	public SQLiteDatabase db;

	public UserDatabaseHelper(Context c) 
	{
		ctx=c;
	}

	private static class DataBaseHelper extends SQLiteOpenHelper
	{

		public DataBaseHelper(Context ctx)
		{
			super(ctx,DATA_BASE_NAME,null,DATABASE_VERSION);

		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			try{
				db.execSQL(TABLE_CREATE);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS studentTable");
			onCreate(db);
		}

	}
	public UserDatabaseHelper open() throws SQLException
	{
		dbhelper=new DataBaseHelper(ctx);
		db=dbhelper.getWritableDatabase();
		return this;

	}
	public void close()
	{
		dbhelper.close();
	}
	public long insertData(String patuser1,String patname1,String patemail1,String password1,String confirmpass1)
	{
		ContentValues content =new ContentValues();
		//inputing data in the table
		content.put(pat_user, patuser1);
		content.put(pat_name, patname1);
		content.put(pat_email, patemail1);
		content.put(pat_pass, password1);
		content.put(pat_cpass, confirmpass1);


		return db.insert(TABLE_NAME,null, content);

	}
	public String getData() {
		String[] columns=new String[]{pat_user,pat_name,pat_pass,pat_cpass,pat_email};
		Cursor c=db.query(TABLE_NAME, columns, null, null, null, null, null);
		String result=" ";

		if(c==null)
			return result;
		else
		{
			int iuser=c.getColumnIndex(pat_user);
			int iname=c.getColumnIndex(pat_name);
			int ipass=c.getColumnIndex(pat_pass);
			int icpass=c.getColumnIndex(pat_cpass);
			int iemail=c.getColumnIndex(pat_email);



			for(c.moveToFirst();!c.isAfterLast();c.moveToNext() )
			{
				result=result +"      "+c.getString(iuser)+ "    "+c.getString(iname)+ "     "+c.getString(ipass)+ " 	  "+c.getString(icpass)+ "    "+c.getString(iemail)+ "\n"; 

			}

			return result;
		}
	}

	public String Exists(String user,String pass)
	{
		String[] columns=new String[]{pat_user,pat_name,pat_pass,pat_cpass,pat_email};
		Cursor c=db.query(TABLE_NAME, columns, null, null, null, null, null);
		String result=" ";

		if(c==null)
			return result;	
		else
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext() )
			{
				if(user.equals(c.getString(c.getColumnIndex(pat_user))))
				{
					if(pass.equals(c.getString(c.getColumnIndex(pat_pass))))
					{
						result="Valid user";
					}
					else
						result="Password not found";
				}
				else
					result="Username not found.Please Register";
			}

		return result;
	}
	
	public int SearchExists(String user)
	{
		String[] columns=new String[]{pat_user,pat_name,pat_pass,pat_cpass,pat_email};
		Cursor c=db.query(TABLE_NAME, columns, null, null, null, null, null);
		String result=" ";
		int b=-1;
		if(c==null)
			return b;	
		else
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext() )
			{
				if(user.equals(c.getString(c.getColumnIndex(pat_user))))
				{
					b=1;
				}
				else
					b=0;
			}
		return b;
	}
}