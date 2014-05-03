package com.az.ratedocs.display;

/* This factory assigns display classes to their corresponding activity classes.
 * */

import android.app.Activity;
import android.content.Context;
import com.az.ratedocs.DoctorProfileActivity;
import com.az.ratedocs.LocalDoctorListActivity;

public class DisplayFactory {
	/* make a static object of type DisplayHelper */
	public static DisplayHelper getDisplayHelper(Activity activity, String value, Context context) {
		Class<?> class1 = activity.getClass();
		DisplayHelper dh;
		
		/* Determine the class for which the display function should be called and then display elements in the view*/
		if(class1 == DoctorProfileActivity.class) {
			dh = new DoctorProfileDisplay(activity, value);
		}  else if (class1 == LocalDoctorListActivity.class) {
			dh = new LocalDoctorListDisplay(activity, value, context);
		}
			else {
			dh = null;
		}
		return dh;
	}
}
