package com.az.ratedocs.onclick;

import com.az.ratedocs.AddCommentsActivity;
import com.az.ratedocs.utilities.StartIntent;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OnClickAddComments implements OnClickInterface{

    Activity activity;
	
	/* Associate the on click methods with our buttons */
	public OnClickAddComments(Activity activity) {
		this.activity = activity;
//		Button button = (Button) activity.findViewById(R.id.add_lend_btn_my_books);
//		button.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				clickedAddLend();
//			}
//			
//		});
	}

	/* Start the edit book details activity */
	public void clickedAddLend() {
		StartIntent.startIntent(activity, AddCommentsActivity.class);
	}
}
