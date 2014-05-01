package com.az.ratedocs.onclick;

import com.az.ratedocs.R;
import com.az.ratedocs.SignInActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OnClickMainActivity implements OnClickInterface {
    private Activity activity;
    private Context context;
	private Button button;
    
	public OnClickMainActivity(final Activity activity, final Context context) {
		this.activity = activity;
		this.context = context;
		
		button = (Button) activity.findViewById(R.id.button);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent in =  new Intent(context,SignInActivity.class);
				activity.startActivity(in);
			}
		});
	}
}
