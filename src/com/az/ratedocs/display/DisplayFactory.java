package com.az.ratedocs.display;

import android.app.Activity;

public class DisplayFactory {
	/* make a static object of type DisplayHelper */
	public static DisplayHelper getDisplayHelper(Activity activity) {
		Class<?> class1 = activity.getClass();
		DisplayHelper dh = null;
		
//		/* Determine the class for which the display function should be called and then display elements in the view*/
//		if(class1 == SearchResults.class) {
//			dh = new ResultsDisplay(activity);
//		} else if(class1 == BookInfo.class) {
//			dh = new BookDisplay(activity);
//		} else if(class1 == MyBooks.class) {
//			dh = new MyBooksDisplay(activity);
//		} else {
//			dh = null;
//		}
		return dh;
	}
}
