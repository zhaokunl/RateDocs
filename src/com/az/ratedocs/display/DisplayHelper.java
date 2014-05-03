package com.az.ratedocs.display;

/* This abstract class enables activities to display from our servers without knowning
 * the certain server connected to.
 * */

import android.app.Activity;

public abstract class DisplayHelper {
	protected Activity activity;

	public DisplayHelper(Activity activity) {
		this.activity = activity;
	}
	
	public abstract void display();
}
