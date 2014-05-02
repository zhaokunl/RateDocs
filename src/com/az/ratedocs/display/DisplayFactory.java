package com.az.ratedocs.display;

import android.app.Activity;
import com.az.ratedocs.DoctorProfileActivity;

public class DisplayFactory {
	/* make a static object of type DisplayHelper */
	public static DisplayHelper getDisplayHelper(Activity activity, String value) {
		Class<?> class1 = activity.getClass();
		DisplayHelper dh;
		
		/* Determine the class for which the display function should be called and then display elements in the view*/
		if(class1 == DoctorProfileActivity.class) {
			dh = new DoctorProfileDisplay(activity, value);
		}  else {
			dh = null;
		}
		return dh;
	}
}
