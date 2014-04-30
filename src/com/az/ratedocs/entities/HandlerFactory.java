package com.az.ratedocs.entities;

import android.app.Activity;
import com.az.ratedocs.webservice.ParseHandler;

public class HandlerFactory {
	public static EntitiesHandler getHandler(Activity activity){
		ParseHandler ph;
		if(activity == null) ph = new ParseHandler();
		else ph = new ParseHandler(activity);
		return ph;
	}
}
