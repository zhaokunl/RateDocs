/************************************************************************************************
 *	18-641 Java for Smart Phone Development
 * 	Authors: 		Shubhang Chaudhary (shubhanc)
 * 					Fiona Britto (fbritto)
 * 					Kyle Verma (ktv)
 * 	Application: 	SmartLend
 * 	Date:			November 30th, 2013 
 ************************************************************************************************/

package com.az.ratedocs.webservice;

import android.app.Activity;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.SaveCallback;

/************************************************************************************************
 * ClassName: ParseSaveCallback.java
 * Description: Parse callback for saving a book to the server
 ************************************************************************************************/

public class ParseSaveCallback extends SaveCallback {

	Activity activity;

	public ParseSaveCallback(Activity activity) {
		this.activity = activity;
	}

	/* Callback's cannot throw exceptions so we have to do error handling here */
	@Override
	public void done(ParseException e) {
		if (e == null) {
			Toast.makeText(activity.getBaseContext(), "Saved Successfully",
					Toast.LENGTH_SHORT).show();
		} 
		else {
			Toast.makeText(activity.getBaseContext(), "Failed to Save",
					Toast.LENGTH_SHORT).show();
		}
	}
}

