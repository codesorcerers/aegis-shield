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
 * No letters - 1 
 * All lower case - 7
 * Upper case and lower case - 10 
 * 
 * @author Rares Barbantan
 *
 */
public class LettersRule extends Rule{

	@Override
	public int evaluate(String password) {
		int score = 1;
		int lowerCase = contains(password, "[a-z]");
		int upperCase = contains(password, "[A-Z]");
		if(lowerCase > 0) {
			if(upperCase > 0) {
				score = 10;
			}else {
				score = 7;
			}
		}
		return score;
	}
}
