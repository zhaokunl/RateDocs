package com.az.ratedocs.utilities;

/* This class enables the user to get the current location of the device.
 * */

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class GeoLocation implements LocationListener {

	private Context context;

	public GeoLocation(Context context) {
		this.context = context;
	}

	@Override
	public void onStatusChanged(String s, int i, Bundle b) {
		Toast.makeText(context, Constants.STATUS_CHANGED, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onProviderDisabled(String s) {
		Toast.makeText(context, Constants.PROVIDER_DISABLED, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onProviderEnabled(String s) {
		Toast.makeText(context, Constants.PROVIDER_ENABLED, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onLocationChanged(Location location) {
	}
}
