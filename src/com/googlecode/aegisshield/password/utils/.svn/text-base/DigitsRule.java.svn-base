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
 * Score:
 * 	No digit - 1
 * 	Less than 3 digits - 7
 * 	More than 3 digits - 10
 * 
 * @author Rares Barbantan
 *
 */
public class DigitsRule extends Rule {

	@Override
	public int evaluate(String password) {
		int score = 10;
		int digits  = contains(password, "\\d");
		if(digits == 0) {
			score = 1;
		}else if (digits < 3) {
			score = 7;
		}
		return score;
	}
}
