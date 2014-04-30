package com.az.ratedocs.exceptionhandler;

import java.io.Serializable;

public class UserNameNotFoundException extends Exception implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6360007358669798718L;

	public UserNameNotFoundException(String string) {
		super(string);
	}
}
