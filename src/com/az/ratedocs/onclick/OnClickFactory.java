package com.az.ratedocs.onclick;

import android.app.Activity;
import android.content.Context;

import com.az.ratedocs.CommentActivity;
import com.az.ratedocs.CreateAccountActivity;
import com.az.ratedocs.DoctListActivity;
import com.az.ratedocs.DoctorProfileActivity;
import com.az.ratedocs.ForgetPasswordActivity;
import com.az.ratedocs.MainActivity;
import com.az.ratedocs.SelectSpecialityActivity;

public class OnClickFactory {
	public static OnClickInterface getOnClick(Activity activity, Context context) {
		OnClickInterface onClickInterface = null;
		Class<?> class1 = activity.getClass();
		
		/* Figures out which activity called this method and returns the corresponding OnClick object */
		if (class1.equals(CommentActivity.class)) onClickInterface = new OnClickAddComments(activity, context);
		else if(class1.equals(CreateAccountActivity.class)) onClickInterface = new OnClickCreateAccount(activity);
		else if(class1.equals(MainActivity.class)) onClickInterface = new OnClickMainActivity(activity);
		else if(class1.equals(DoctListActivity.class)) onClickInterface = new OnClickDoctorList(activity, context);
		else if(class1.equals(ForgetPasswordActivity.class)) onClickInterface = new OnClickForgetPassword(activity);
		else if(class1.equals(SelectSpecialityActivity.class)) onClickInterface = new OnClickSpeciality(activity, context);
		else if(class1.equals(DoctorProfileActivity.class)) onClickInterface = new OnClickDoctorProfile(activity, context);
		return onClickInterface;
	}
}
