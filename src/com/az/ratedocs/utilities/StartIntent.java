package com.az.ratedocs.utilities;

import android.app.Activity;
import android.content.Intent;

public abstract class StartIntent {
	
	/* Start the activity class1 from activity */
	public static void startIntent(Activity activity, Class<?> class1) {
		
		Intent intent = new Intent(activity, class1);
		activity.startActivity(intent);
	}
}