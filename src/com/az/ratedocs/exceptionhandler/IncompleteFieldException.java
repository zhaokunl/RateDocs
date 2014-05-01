/************************************************************************************************
 *	18-641 Java for Smart Phone Development
 * 	Authors: 		Shubhang Chaudhary (shubhanc)
 * 					Fiona Britto (fbritto)
 * 					Kyle Verma (ktv)
 * 	Application: 	SmartLend
 * 	Date:			November 30th, 2013 
 ************************************************************************************************/

package com.az.ratedocs.exceptionhandler;

/* All the android imports */

/************************************************************************************************
 * ClassName: IncompleteFieldException.java
 * Description: This Exception is for when a user has not filled in the required number of fields
 * 				but has clicked the button associated with those fields
 ************************************************************************************************/

public class IncompleteFieldException extends Exception {

	public IncompleteFieldException(String string) {
		super(string);
	}
	private static final long serialVersionUID = 2455272108926169077L;

}
