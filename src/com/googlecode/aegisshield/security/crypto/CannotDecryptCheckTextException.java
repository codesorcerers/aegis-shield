/*
 * Copyright (C) 2008 - 2010 Mihai Campean
 *  
 *	This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.googlecode.aegisshield.security.crypto;

/**
 *
 * @author Mihai Campean
 */
public class CannotDecryptCheckTextException extends Exception {

	/**
	 * 	The generated serial version id.
	 */
	private static final long serialVersionUID = 1786096845444533790L;
	
	/**
	 * 	The check text cannot be decrypted due to some errors.
	 */
	public static final String DEFAULT_MESSAGE = "Cannot decrypt the check text.";
	
	/**
	 * Constructor taking the given string message as argument.
	 *  
	 * @param message
	 */
	public CannotDecryptCheckTextException(String message) {
		super(message);
	}
	
	/**
	 * Constructor taking the given throwable exception as argument as well as the default exception message.
	 * 
	 * @param throwable
	 */
	public CannotDecryptCheckTextException(Throwable throwable) {
		super(DEFAULT_MESSAGE, throwable);
	}
	
	/**
	 * Constructor taking a string message and a throwable cause as arguments.
	 * 
	 * @param message
	 * @param throwable
	 */
	public CannotDecryptCheckTextException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
