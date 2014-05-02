package com.az.ratedocs.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.StrictMode;
import android.util.Log;
import com.az.ratedocs.entities.DoctorInterface;
import com.az.ratedocs.entities.EntitiesHandler;
import com.az.ratedocs.entities.UserInfoInterface;
import com.az.ratedocs.exceptionhandler.WebServiceException;
import com.az.ratedocs.model.ParseDoctor;
import com.az.ratedocs.model.ParseInfoUser;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ParseHandler implements EntitiesHandler{
	private static DoctorInterface currentDoctor;
	private static ArrayList<DoctorInterface> currentDoctorList;

	public ParseHandler() {
	}
	
	@SuppressLint("NewApi")
	public ParseHandler(Activity activity){
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		Parse.initialize(activity, PConstants.APPLICATION_ID, PConstants.CLIENT_KEY);
		ParseAnalytics.trackAppOpened(activity.getIntent());
	}

	/* Attempt to log a user in in the background using a callback */
	@Override
	public void logIn(String uname, String pword, Activity activity, Class<?> class1) {
		ParseUser.logInInBackground(uname, pword,  new ParseLoginCallback(activity, class1));
	}

	/* Return a new empty user object */
	public UserInfoInterface getUser() {
		return new ParseInfoUser();
	}

	/* Attempt to register a new user to the server */
	@Override
	public void signUp(UserInfoInterface user, Activity activity, Class<?> class1) {
		user.signUp(activity, class1);
	}

	/* Request a password reset in the background using a callback */
	@Override
	public void resetPassword(String email, Activity activity) {
		ParseUser.requestPasswordResetInBackground(email, new ParseRequestPasswordResetCallback(activity));
	}

	/* Return a new empty Doctor object */
	@Override
	public DoctorInterface getDoctor() {
		return new ParseDoctor();
	}

	/* Get the current user, if no user is logged in throw an exception */
	@Override
	public UserInfoInterface getCurrentUser() throws WebServiceException {
		if(ParseUser.getCurrentUser() == null){
			throw new WebServiceException("Please log in");
		}
		return new ParseInfoUser(ParseUser.getCurrentUser());
	}

	/* Query the database using the supplied title and hashmap of advanced search terms */
	@SuppressLint("NewApi")
	@Override
	public void query(String specialization, Activity activity, HashMap<String, String> advSearch) throws WebServiceException {
		ParseQuery<ParseObject> query;

		List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();

		/* If there was a specialization put it in the query list */
		if(specialization != null) {
			query = ParseQuery.getQuery(PConstants.PARSE_DOCTOR);
			query.whereEqualTo(PConstants.PARSE_SPECIALIZATION, specialization);
			queries.add(query);
		}

		/* If there were advanced search terms add them to the query list */
		if(advSearch != null) {
			Iterator<String> iter = advSearch.keySet().iterator();
			while(iter.hasNext()) {
				String key = iter.next();
				String value = advSearch.get(key);
				if(!value.isEmpty()) {
					query = ParseQuery.getQuery(PConstants.PARSE_RATING);
					query.whereEqualTo("doctorID", value);
					queries.add(query);
				}
			}
		}

		/* Search for all of the queries in the query list */
		query = ParseQuery.or(queries);
		ArrayList<DoctorInterface> Doctorlist = new ArrayList<DoctorInterface>();
		try {
			/* Retrieve the search results and convert them from parseobjects to Doctor objects */
			List<ParseObject> list = query.find();
			Iterator<ParseObject> iter = list.iterator();
			while(iter.hasNext()) {
				Doctorlist.add(new ParseDoctor(iter.next()));
			}
			
			/* Set the current Doctor list to the search results */
			currentDoctorList = Doctorlist;
		} catch (ParseException e) {
			throw new WebServiceException("Search Failed");
		}
	}

	/* Get the currently saved doctor */
	@Override
	public DoctorInterface getCurrentDoctor(Activity activity) {
		return currentDoctor;
	}
	
	/* Get the currently saved Doctor list */
	@Override
	public ArrayList<DoctorInterface> getCurrentList(Activity activity) {
		return currentDoctorList;
	}

	/* Set the saved took to the supplied Doctor */
	@Override
	public void setCurrentDoctor(DoctorInterface doc) {
		currentDoctor = doc;
	}

	/* Get the ratings */
	@Override
	public void getRatings(Activity activity) throws WebServiceException {
		ParseQuery<ParseObject> query = ParseQuery.getQuery(PConstants.PARSE_RATING);
    	if(ParseUser.getCurrentUser() == null) {
    		throw new WebServiceException("Please log in");
    	}
    	query.whereEqualTo("User", ParseUser.getCurrentUser());
    	try {
			List<ParseObject> ratinglist = query.find();
			if(ratinglist==null || ratinglist.isEmpty()) {
				throw new WebServiceException("You don not have any ratings");
			} else {
				Iterator<ParseObject> iter = ratinglist.iterator();
				currentDoctorList = new ArrayList<DoctorInterface>();
				while(iter.hasNext()) {
					currentDoctorList.add(new ParseDoctor(iter.next()));
				}
			}
		} catch (ParseException e) {
			throw new WebServiceException("Could retrieve Doctor list");
		}
    }
}
