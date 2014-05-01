package com.az.ratedocs.utilities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.az.ratedocs.R;
import com.az.ratedocs.model.ParseDoctor;

public class EmailDoctor implements OnItemSelectedListener{
	
	private List<String> days = new ArrayList<String>();
	private List<String> months = new ArrayList<String>();
	private String selected_day, selected_month;
	private Spinner s1, s2;
    private Activity activity;
    private Context context;
	private Button request;
	private ParseDoctor doc;
	private EditText t;
	
	public EmailDoctor(Activity activity, Context context){
		this.activity = activity;
		this.context = context;
		s1 = (Spinner) activity.findViewById(R.id.spinner1);
		s2 = (Spinner) activity.findViewById(R.id.spinner2);
		t = (EditText) activity.findViewById(R.id.editText6);
	}

	public void requestAppointment() {
		for (int i = 1; i <= 31; i++) {
			days.add("" + i);
		}

		for (int i = 1; i <= 12; i++) {
			months.add("" + i);
		}

		ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, months);
		adapter_month
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s1.setAdapter(adapter_month);
		s1.setOnItemSelectedListener(this);

		ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, days);
		adapter_year
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s2.setAdapter(adapter_year);
		s2.setOnItemSelectedListener(this);

		request = (Button) activity.findViewById(R.id.req);
		request.setOnClickListener(new View.OnClickListener() {

			String subject = Constants.SUBJECT;
			String month = s1.getSelectedItem().toString();
			String day = s2.getSelectedItem().toString();
			String message = month + " " + day + " " + t.getText().toString();

			@Override
			public void onClick(View arg0) {
				Intent intentEmail = new Intent(Intent.ACTION_SEND);
				intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[] { doc.getEmail() });
				intentEmail.putExtra(Intent.EXTRA_SUBJECT, subject.toString());
				intentEmail.putExtra(Intent.EXTRA_TEXT, message.toString());
				intentEmail.setType(Constants.EMAIL_TYPE);
				activity.startActivity(Intent.createChooser(intentEmail,
						Constants.EMAIL_MESSAGE));
			}
		});
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
			long arg3) {
		switch (arg0.getId()) {
		case R.id.spinner1:
			selected_month = arg0.getItemAtPosition(pos).toString();

			break;
		case R.id.spinner2:
			selected_day = arg0.getItemAtPosition(pos).toString();
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
}
