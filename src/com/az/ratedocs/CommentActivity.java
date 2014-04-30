package com.az.ratedocs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CommentActivity extends Activity{
	private String username;
	private int number;
	private String id;
	private ListView list;
	private EditText edittext;
	private Button comment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment_items);

		populateComments();
		onComment();
	}

	public void onComment() {
		comment = (Button) findViewById(R.id.button);
		edittext = (EditText) findViewById(R.id.input);
		
		comment.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ParseObject newrating = new ParseObject("ratings");
				newrating.put("comment", edittext.getText().toString());
				newrating.put("username",  username);
				newrating.put("doctorID", id);
				newrating.saveInBackground();
			}
		});
	}
	
	
	private void populateComments() {
		String doc_id = "";
		String name = "";
		Bundle extras = getIntent().getExtras();

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

					list = (ListView) findViewById(R.id.listView1);

					ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

					for (int i = 0; i < usernames.size(); i++) {
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("username", myusernames[i]);
						map.put("colon", " : ");
						map.put("comment", mycomments[i]);
						mylist.add(map);
					}

					SimpleAdapter adapter = new SimpleAdapter(
							CommentActivity.this, mylist, R.layout.activity_doctor_comments,
							new String[] { "username", "colon", "comment" },
							new int[] { R.id.username, R.id.colon, R.id.comment });
					list.setAdapter(adapter);

					Log.d("score", "Retrieved " + usernames.size() + " scores");
				} else {
					Log.d("score", "Error: " + e.getMessage());
				}
			}
		});
	}
}
