/*
 * Copyright (C) 2008 - 2009 Christian Stefanescu
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
package com.googlecode.aegisshield.password.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.googlecode.aegisshield.utils.app.Constants;

/**
 * Utility class for generating passwords.
 * 
 * @author Christian Stefanescu
 */
public class PasswordGenerator {

	final private static char[] lower_chars = { 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z' };

	final private static char[] upper_chars = { 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	final private static char[] digits = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9' };

	final private static char[] extra_chars = { '[', '!', '@', '#', '$', '%',
			'^', '&', '?', '_', '~', ']' };

	/**
	 * Generates a password of specified length.
	 * 
	 * @param length
	 *            of the password
	 * @return String with generated password
	 */
	public static String getPassword(int length) {
		// re-seed the Random generator at every call
		final Random rand = new Random(new Date().getTime());

		// fill the initial password with lower case characters
		final StringBuffer initialPassword = new StringBuffer();
		for (int i = 0; i < length; i++) {
			initialPassword
					.append(lower_chars[rand.nextInt(lower_chars.length)]);
		}

		// the positions where lower characters have been replaced already
		final ArrayList<Integer> filledPositions = new ArrayList<Integer>();

		// randomly replace the lower case characters with upper case, digits
		// and extra characters.
		char[] password = initialPassword.toString().toCharArray();
		for (int i = 0; i < Constants.GENERATED_PASSWORD_UPPER; i++) {
			int pos = rand.nextInt(length);
			while (filledPositions.contains(pos)) {
				pos = rand.nextInt(length);
			}
			filledPositions.add(pos);
			password[pos] = upper_chars[rand.nextInt(upper_chars.length)];
		}
		for (int i = 0; i < Constants.GENERATED_PASSWORD_DIGITS; i++) {
			int pos = rand.nextInt(length);
			while (filledPositions.contains(pos)) {
				pos = rand.nextInt(length);
			}
			filledPositions.add(pos);
			password[pos] = digits[rand.nextInt(digits.length)];
		}
		for (int i = 0; i < Constants.GENERATED_PASSWORD_EXTRAS; i++) {
			int pos = rand.nextInt(length);
			while (filledPositions.contains(pos)) {
				pos = rand.nextInt(length);
			}
			filledPositions.add(pos);
			password[pos] = extra_chars[rand.nextInt(extra_chars.length)];
		}
		return new String(password);
	}
}
