package com.az.ratedocs.exceptionhandler;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.util.Arrays;

public class PasswordMatchException extends Exception implements Serializable{
	private static final long serialVersionUID = 7422174183778789067L;

	public PasswordMatchException(String string) {
		super(string);
	}

	@SuppressLint("NewApi")
	@Override
	public String toString() {
		return "PasswordMatchException [fillInStackTrace()="
				+ fillInStackTrace() + ", getMessage()=" + getMessage()
				+ ", getLocalizedMessage()=" + getLocalizedMessage()
				+ ", getStackTrace()=" + Arrays.toString(getStackTrace())
				+ ", toString()=" + super.toString() + ", getCause()="
				+ getCause() + ", getSuppressed()="
				+ Arrays.toString(getSuppressed()) + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
