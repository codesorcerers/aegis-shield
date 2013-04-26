/*
 * Copyright (C) 2008 - 2009 Rares Barbantan
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

/**
 * If the length of the password is less than 4 chars, password is weak (say a 4) else,
 * unless it has over 8 chars (excellent), it is considered good (say a 7).
 * 
 * @author Rares Barbantan
 *
 */
public class PasswordLengthRule extends Rule {

	@Override
	public int evaluate(String password) {
		int result = 1;
		if(password != null) {
			if(password.length() <= 4) {
				result = 4;
			}else if (password.length() <= 7) {
				result = 8;
			}else {
				result = 10;
			}
		}
		return result;
	}

}
