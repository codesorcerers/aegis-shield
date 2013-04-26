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
 * 	No extra chars - 1
 * 	One extra char - 6
 * 	More than 1 extra char - 10
 *  
 * @author Rares Barbantan
 *
 */
public class ExtraCharsRule extends Rule {

	public ExtraCharsRule()	{
		// we can override the default(1) weight of the rule
		setWeight(0.5);
	}
	
	@Override
	public int evaluate(String password) {
		int score  = 1;
		int extraChars = contains(password, "[!@#$%^&*?_~]");
		if(extraChars == 1) {
			score = 6;
		}else if (extraChars > 1) {
			score = 10;
		}
		return score;
	}

}
