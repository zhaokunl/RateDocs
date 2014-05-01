package com.az.ratedocs.utilities;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class GeoLocation implements LocationListener{

	private Context context;
	
	public GeoLocation(Context context) {
		this.context = context;
	}
	
	@Override
	public void onStatusChanged(String s, int i, Bundle b) {
		Toast.makeText(context, "Provider status changed",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onProviderDisabled(String s) {
		Toast.makeText(context,
				"Provider disabled by the user. GPS turned off",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onProviderEnabled(String s) {
		Toast.makeText(context,
				"Provider enabled by the user. GPS turned on",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onLocationChanged(Location location) {
	}

}
