package com.az.ratedocs.exceptionhandler;

import java.io.Serializable;

public class PasswordMatchException extends Exception implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7422174183778789067L;

	public PasswordMatchException(String string) {
		super(string);
	}
}
