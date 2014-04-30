package com.az.ratedocs.utilities;

/************************************************************************************************
 *	18-641 Java for Smart Phone Development
 * 	Authors: 		Shubhang Chaudhary (shubhanc)
 * 					Fiona Britto (fbritto)
 * 					Kyle Verma (ktv)
 * 	Application: 	SmartLend
 * 	Date:			November 30th, 2013 
 ************************************************************************************************/


/* All the android imports */

import android.app.Activity;
import android.content.Intent;
/************************************************************************************************
 * ClassName: OnClickAdvancedSearch.java
 * Description: This class starts the activity that is given
 ************************************************************************************************/


public abstract class StartIntent {
	
	/* Start the activity class1 from activity */
	public static void startIntent(Activity activity, Class<?> class1) {
		
		Intent intent = new Intent(activity, class1);
		activity.startActivity(intent);
	
	}
}