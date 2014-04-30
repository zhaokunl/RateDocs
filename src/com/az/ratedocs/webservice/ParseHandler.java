package com.az.ratedocs.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;

import com.az.ratedocs.entities.DoctorInterface;
import com.az.ratedocs.entities.EntitiesHandler;
import com.az.ratedocs.entities.UserInfoInterface;
import com.az.ratedocs.exceptionhandler.WebServiceException;
import com.az.ratedocs.model.ParseDoctor;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ParseHandler implements EntitiesHandler{
	private static DoctorInterface currentBook;
	private static ArrayList<DoctorInterface> currentBookList;

	public ParseHandler() {
	}
	
	public ParseHandler(Activity activity){
		Parse.initialize(activity, PConstants.APPLICATION_ID, PConstants.CLIENT_KEY);
		ParseAnalytics.trackAppOpened(activity.getIntent());
	}

	/* Log out the currently logged in user */
	@Override
	public void logOut() {
		ParseUser.logOut();
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

	/* Return a new empty book object */
	@Override
	public DoctorInterface getBook() {
		return new ParseDoctor();
	}

//	/* Get the current user, if no user is logged in throw an exception */
//	@Override
//	public UserInfoInterface getCurrentUser() throws WebServiceException {
//		if(ParseUser.getCurrentUser() == null){
//			throw new WebServiceException("Please log in");
//		}
//		return new ParseInfoUser(ParseUser.getCurrentUser());
//	}
//
//	/* Query the database using the supplied title and hashmap of advanced search terms */
//	@Override
//	public void query(String title, Activity activity, HashMap<String, String> advSearch) throws WebServiceException {
//		ParseQuery<ParseObject> query;
//
//		List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
//
//		/* If there was a title put it in the query list */
//		if(title != null) {
//			query = ParseQuery.getQuery(PConstants.PARSE_BOOK);
//			query.whereEqualTo(PConstants.PARSE_TITLE, title);
//			queries.add(query);
//		}
//
//		/* If there were advanced search terms add them to the query list */
//		if(advSearch != null) {
//			Iterator<String> iter = advSearch.keySet().iterator();
//			while(iter.hasNext()) {
//				String key = iter.next();
//				String value = advSearch.get(key);
//				if(!value.isEmpty()) {
//					query = ParseQuery.getQuery(PConstants.PARSE_BOOK);
//					query.whereEqualTo(PConstants.convertAdvSearch(key), value);
//					queries.add(query);
//				}
//			}
//		}
//
//		/* Search for all of the queries in the query list */
//		query = ParseQuery.or(queries);
//		ArrayList<BookInterface> booklist = new ArrayList<BookInterface>();
//		try {
//			/* Retrieve the search results and convert them from parseobjects to book objects */
//			List<ParseObject> list = query.find();
//			Iterator<ParseObject> iter = list.iterator();
//			while(iter.hasNext()) {
//				booklist.add(new ParseBook(iter.next()));
//			}
//			
//			/* Set the current book list to the search results */
//			currentBookList = booklist;
//		} catch (ParseException e) {
//			throw new WebServiceException("Search Failed");
//		}
//	}

	@Override
	public void setCurrentBook(DoctorInterface book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DoctorInterface getCurrentBook(Activity activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DoctorInterface> getCurrentList(Activity activity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* Get the list of books that this user has added to the database */
//	@Override
//	public void getUserBooks(Activity activity) throws WebServiceException {
//		ParseQuery<ParseObject> query = ParseQuery.getQuery("Book");
//    	if(ParseUser.getCurrentUser() == null) {
//    		throw new WebServiceException("Please log in");
//    	}
//    	query.whereEqualTo("User", ParseUser.getCurrentUser());
//    	try {
//			List<ParseObject> booklist = query.find();
//			if(booklist==null || booklist.isEmpty()) {
//				throw new WebServiceException("You don not have any books");
//			} else {
//				Iterator<ParseObject> iter = booklist.iterator();
//				currentBookList = new ArrayList<BookInterface>();
//				while(iter.hasNext()) {
//					currentBookList.add(new ParseBook(iter.next()));
//				}
//			}
//		} catch (ParseException e) {
//			throw new WebServiceException("Could retrieve book list");
//		}
//    }
}
