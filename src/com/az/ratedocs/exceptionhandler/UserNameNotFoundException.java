package com.az.ratedocs.exceptionhandler;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.util.Arrays;

public class UserNameNotFoundException extends Exception implements Serializable{
	private static final long serialVersionUID = -6360007358669798718L;

	public UserNameNotFoundException(String string) {
		super(string);
	}

	@SuppressLint("NewApi")
	@Override
	public String toString() {
		return "UserNameNotFoundException [fillInStackTrace()="
				+ fillInStackTrace() + ", getMessage()=" + getMessage()
				+ ", getLocalizedMessage()=" + getLocalizedMessage()
				+ ", getStackTrace()=" + Arrays.toString(getStackTrace())
				+ ", toString()=" + super.toString() + ", getCause()="
				+ getCause() + ", getSuppressed()="
				+ Arrays.toString(getSuppressed()) + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
