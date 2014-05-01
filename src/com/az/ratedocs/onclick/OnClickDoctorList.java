package com.az.ratedocs.onclick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.az.ratedocs.DoctorProfileActivity;
import com.az.ratedocs.R;
import com.az.ratedocs.utilities.GeoLocation;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class OnClickDoctorList implements OnClickInterface {
	Activity activity;
	private int number = 0;
	private ArrayList<String> ids = new ArrayList<String>();
	private String username;
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 0; // Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;
	private LocationManager locationmanager;
	private ListView list;
	private Context context;

	/* Associate the on click methods with our buttons */
	public OnClickDoctorList(Activity activity, Context context) {
		this.activity = activity;
		this.context = context;

		locationmanager = (LocationManager) activity
				.getSystemService(Context.LOCATION_SERVICE);

		locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MINIMUM_TIME_BETWEEN_UPDATES,
				MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, new GeoLocation(context));

		populateDocts();
		registerclickCall();
	}

	private void populateDocts() {
		String value = "";
		String name = "";
		Bundle extras = activity.getIntent().getExtras();

		if (extras != null) {
			value = extras.getString("string");
			name = extras.getString("username");
		}

		username = name;

		ParseQuery<ParseObject> query = ParseQuery.getQuery("doctor_profile");

		if (value.equals("All")) {
		} else {
			query.whereEqualTo("specialization", value);
		}

		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> doctorList,
					com.parse.ParseException e) {
				if (e == null) {
					ArrayList<String> names = new ArrayList<String>();
					ArrayList<String> phones = new ArrayList<String>();
					ArrayList<Double> longitudes = new ArrayList<Double>();
					ArrayList<Double> latitudes = new ArrayList<Double>();

					for (ParseObject d : doctorList) {
						names.add(d.getString("name"));
						phones.add(d.getString("phone"));
						longitudes.add(d.getDouble("longtitude"));
						latitudes.add(d.getDouble("latitude"));
						ids.add(d.getString("ID"));
					}

					number = names.size();

					String[] myDocs = new String[names.size()];

					for (int i = 0; i < names.size(); i++) {
						myDocs[i] = names.get(i);
					}

					double[] myDocDistance = new double[names.size()];

					for (int i = 0; i < names.size(); i++) {
						myDocDistance[i] = 0;
					}

					for (int i = 0; i < names.size(); i++) {
						double a = latitudes.get(i);
						double b = longitudes.get(i);

						myDocDistance[i] = calculateLocation(a, b);
					}

					String[] myDocPhone = new String[names.size()];

					for (int i = 0; i < names.size(); i++) {
						myDocPhone[i] = phones.get(i);
					}

					list = (ListView) activity.findViewById(R.id.listView1);

					ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

					for (int i = 0; i < names.size(); i++) {
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("name", myDocs[i]);
						map.put("distance", Double.toString(myDocDistance[i]));
						map.put("phone", myDocPhone[i]);
						mylist.add(map);
					}

					SimpleAdapter adapter = new SimpleAdapter(context, mylist,
							R.layout.activity_doctor_list, new String[] {
									"name", "distance", "phone" }, new int[] {
									R.id.name, R.id.distance, R.id.phone });
					list.setAdapter(adapter);

					Log.d("score", "Retrieved " + names.size() + " scores");
				} else {
					Log.d("score", "Error: " + e.getMessage());
				}
			}
		});
	}

	private void registerclickCall() {
		list = (ListView) activity.findViewById(R.id.listView1);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				for (int i = 0; i < number; i++) {
					if (arg2 == i) {
						Intent i1 = new Intent(context,
								DoctorProfileActivity.class);
						i1.putExtra("id", ids.get(i));
						i1.putExtra("username", username);
						activity.startActivity(i1);
					}
				}
			}
		});
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

	protected double calculateLocation(double latitude, double longitude) {
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
