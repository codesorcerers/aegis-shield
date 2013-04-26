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

import java.util.ArrayList;
import java.util.List;

/**
 * 	Utility for computing a password's strength based on weighted algorithms.
 * 
 * @author Rares Barbantan
 */
public class PasswordStrength {
	
	private static List<Rule> rules = new ArrayList<Rule>();
	
	static {
		rules.add(new PasswordLengthRule());
		rules.add(new LettersRule());
		rules.add(new DigitsRule());
		rules.add(new ExtraCharsRule());
	}
	
	/**
	 * Evaluates the strength of a given password going by a list of weighted rules.
	 * @param password the password to be checked
	 * @return a grade for the strength (should be between 1..10)
	 */
	public static int evaluate(String password) {
		int result = 0;
		double weights = 0;
		
		for(Rule rule: rules) {
			result += rule.getWeight() * rule.evaluate(password);
			weights += rule.getWeight();
		}
		int finalResult = (int) ((weights == 0) ? 0 : result/weights);
		return finalResult;
	}
}
