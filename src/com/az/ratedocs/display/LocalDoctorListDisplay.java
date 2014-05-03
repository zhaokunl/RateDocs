package com.az.ratedocs.display;

/* This class displays a list of doctors retrieved from the local database. 
 * */

import java.util.ArrayList;
import java.util.HashMap;
import com.az.ratedocs.R;
import com.az.ratedocs.model.Doctor;
import com.az.ratedocs.sqlite.DoctorDatabaseHelper;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LocalDoctorListDisplay extends DisplayHelper {
	private Activity activity;
	private String value;
	private Context context;
	private ListView list;
	private ArrayList<HashMap<String, String>> mylist;

	public LocalDoctorListDisplay(Activity activity, String value,
			Context context) {
		super(activity);
		this.activity = activity;
		this.value = value;
		this.context = context;
	}

	@Override
	public void display() {
		DoctorDatabaseHelper d = new DoctorDatabaseHelper(context);

		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> emails = new ArrayList<String>();
		ArrayList<String> phones = new ArrayList<String>();

		for (Doctor doc : d.getDoctorList()) {
			names.add(doc.getName());
			emails.add(doc.getEmail());
			phones.add(doc.getPhone());
			Log.d("ok", doc.getEmail());
		}

		list = (ListView) activity.findViewById(R.id.listView1);

		mylist = new ArrayList<HashMap<String, String>>();
		
		for (int i = 0; i < names.size(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", names.get(i));
            map.put("email", emails.get(i));
            map.put("phone", phones.get(i));
            mylist.add(map);
        }

		SimpleAdapter adapter = new SimpleAdapter(context, mylist,
				R.layout.activity_localdoctor_list, new String[] { "name",
						"email", "phone" }, new int[] { R.id.name,
						R.id.email, R.id.phone });
		list.setAdapter(adapter);
	}
}
