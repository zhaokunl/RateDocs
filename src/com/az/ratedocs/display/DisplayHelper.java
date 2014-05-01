package com.az.ratedocs.display;

import android.app.Activity;

public abstract class DisplayHelper {
	protected Activity activity;

	public DisplayHelper(Activity activity) {
		this.activity = activity;
	}
	
	public abstract void display();
}
