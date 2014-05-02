package com.az.ratedocs.onclick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.az.ratedocs.DoctorProfileActivity;
import com.az.ratedocs.R;
import com.az.ratedocs.utilities.CalculateDistance;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class OnClickDoctorList implements OnClickInterface {
	Activity activity;
	private int number = 0;
	private ArrayList<String> ids = new ArrayList<String>();
	private String username;
	private ListView list;
	private Context context;

	/* Associate the on click methods with our buttons */
	public OnClickDoctorList(Activity activity, Context context) {
		this.activity = activity;
		this.context = context;

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

						CalculateDistance cal = new CalculateDistance(activity, context);
						myDocDistance[i] = cal.calculateLocation(a, b);
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
						map.put("distance", String.format("%.3f",myDocDistance[i]));
						map.put("phone", myDocPhone[i]);
						mylist.add(map);
					}
					
					Collections.sort(mylist, new Comparator<HashMap<String, String>>() {
						@Override
						public int compare(HashMap<String, String> a, HashMap<String, String> b) {
							double dist1 = Double.parseDouble(a.get("distance"));
							double dist2 = Double.parseDouble(b.get("distance"));
							double delta = dist1 - dist2;
							if(delta == 0) {
								return 0;
							} else if(delta < 0) {
								return -1;
							} else {
								return 1;
							}
						}
					});

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
}
