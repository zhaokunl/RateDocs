package com.az.ratedocs.onclick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.az.ratedocs.R;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class OnClickAddComments implements OnClickInterface {

	Activity activity;
	private String username;
	private String id;
	private int number;
	private ListView list;
	private EditText edittext;
	private Button comment;
	private Context context;

	/* Associate the on click methods with our buttons */
	public OnClickAddComments(Activity activity, Context context) {
		this.activity = activity;
		this.context = context;
		
		populateComments();
		onComment();
		
	}

	public void onComment() {
		comment = (Button) activity.findViewById(R.id.button);
		edittext = (EditText) activity.findViewById(R.id.input);

		comment.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ParseObject newrating = new ParseObject("ratings");
				newrating.put("comment", edittext.getText().toString());
				newrating.put("username", username);
				newrating.put("doctorID", id);
				newrating.saveInBackground();
			}
		});
	}

	private void populateComments() {
		String doc_id = "";
		String name = "";
		Bundle extras = activity.getIntent().getExtras();

		if (extras != null) {
			doc_id = extras.getString("id");
			name = extras.getString("username");
		}

		username = name;
		id = doc_id;

		ParseQuery<ParseObject> query = ParseQuery.getQuery("ratings");
		query.whereEqualTo("doctorID", doc_id);

		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> commentsList,
					com.parse.ParseException e) {
				if (e == null) {
					ArrayList<String> usernames = new ArrayList<String>();
					ArrayList<String> comments = new ArrayList<String>();

					for (ParseObject d : commentsList) {
						usernames.add(d.getString("username"));
						comments.add(d.getString("comment"));
					}

					number = usernames.size();

					String[] myusernames = new String[usernames.size()];

					for (int i = 0; i < usernames.size(); i++) {
						myusernames[i] = usernames.get(i);
					}

					String[] mycomments = new String[usernames.size()];

					for (int i = 0; i < usernames.size(); i++) {
						mycomments[i] = comments.get(i);
					}

					list = (ListView) activity.findViewById(R.id.listView1);

					ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

					for (int i = 0; i < usernames.size(); i++) {
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("username", myusernames[i]);
						map.put("colon", " : ");
						map.put("comment", mycomments[i]);
						mylist.add(map);
					}
                   
					SimpleAdapter adapter = new SimpleAdapter(
						    context,
							mylist,
							R.layout.activity_doctor_comments,
							new String[] { "username", "colon", "comment" },
							new int[] { R.id.username, R.id.colon, R.id.comment });
					list.setAdapter(adapter);
				} else {
					Log.d("score", "Error: " + e.getMessage());
				}
			}
		});
	}
}
