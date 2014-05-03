package com.az.ratedocs.utilities;

/* This class calculates the distance between user current location with a given location.
 * */

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class CalculateDistance {
	
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 0; // Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;
	private static LocationManager locationmanager;
	private Activity activity;
	private Context context;
	
	public CalculateDistance(Activity activity, Context context) {
		this.activity = activity;
		this.context = context;
		
		locationmanager = (LocationManager) activity
				.getSystemService(Context.LOCATION_SERVICE);

		locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MINIMUM_TIME_BETWEEN_UPDATES,
				MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, new GeoLocation(context));
	}
	
	public static double distFrom(double lat1, double lng1, double lat2,
			double lng2) {
		double earthRadius = 3958.75;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
				* Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;
		double kmConversion = 1.609;
		return (double) (dist * kmConversion);
	}

	public static double calculateLocation(double latitude, double longitude) {
		Location location = locationmanager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (location != null) {
			String message = String.format(
					"Current Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude());
			return distFrom(location.getLatitude(), location.getLongitude(),
					latitude, longitude);
		}
		return 0;
	}
}
