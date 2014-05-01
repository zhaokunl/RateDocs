package com.az.ratedocs.exceptionhandler;

import android.annotation.SuppressLint;
import java.util.Arrays;

public class IncompleteFieldException extends Exception {
	private static final long serialVersionUID = 2455272108926169077L;
	
	public IncompleteFieldException(String string) {
		super(string);
	}
	
	@SuppressLint("NewApi")
	@Override
	public String toString() {
		return "IncompleteFieldException [fillInStackTrace()="
				+ fillInStackTrace() + ", getMessage()=" + getMessage()
				+ ", getLocalizedMessage()=" + getLocalizedMessage()
				+ ", getStackTrace()=" + Arrays.toString(getStackTrace())
				+ ", toString()=" + super.toString() + ", getCause()="
				+ getCause() + ", getSuppressed()="
				+ Arrays.toString(getSuppressed()) + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
