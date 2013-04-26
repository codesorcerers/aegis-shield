/*
 * Copyright (C) 2008 - 2009 Mihai Campean
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
public class CryptoServiceException extends RuntimeException {

	/**
	 *  Generated version serial id.
	 */
	private static final long serialVersionUID = -8337736714023409477L;
	
	/**
	 *	Default exception message. 
	 */
	public static final String DEFAULT_MESSAGE = "Error in encryption/decryption service.";

	/**
	 * 	No-arg provided constructor.
	 */
	public CryptoServiceException() {
		super(DEFAULT_MESSAGE);
	}

	/**
	 *  Constructor with a String message argument.
	 * 
	 * @param detailMessage
	 */
	public CryptoServiceException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * 	Constructor with a Throwable cause argument.
	 * 
	 * @param throwable
	 */
	public CryptoServiceException(Throwable throwable) {
		super(DEFAULT_MESSAGE, throwable);
	}

	/**
	 * 	Constructor with a String message and a Throwable cause as arguments.
	 * 
	 * @param detailMessage
	 * @param throwable
	 */
	public CryptoServiceException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

}
