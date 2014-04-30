package com.az.ratedocs.exceptionhandler;

import java.io.Serializable;

public class WebServiceException extends Exception implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8613188134003633675L;

	public WebServiceException(String string) {
		super(string);
	}
}
