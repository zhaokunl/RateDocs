package com.az.ratedocs.exceptionhandler;

import java.io.Serializable;
import java.util.Arrays;

public class WebServiceException extends Exception implements Serializable{
	private static final long serialVersionUID = 8613188134003633675L;

	public WebServiceException(String string) {
		super(string);
	}

	@Override
	public String toString() {
		return "WebServiceException [fillInStackTrace()=" + fillInStackTrace()
				+ ", getMessage()=" + getMessage() + ", getLocalizedMessage()="
				+ getLocalizedMessage() + ", getStackTrace()="
				+ Arrays.toString(getStackTrace()) + ", toString()="
				+ super.toString() + ", getCause()=" + getCause()
				+ ", getSuppressed()=" + Arrays.toString(getSuppressed())
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
}
