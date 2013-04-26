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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract rule for determining a password's strength.
 * 
 * @author Rares Barbantan
 *
 */
public abstract class Rule {

	private double weight = 1;
	
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(final double weight) {
		this.weight = weight;
	}
	
	/**
	 * Count how many time the regex appears in the password.
	 * @param password the string to be parsed
	 * @param regex the regular expression to count
	 * @return the number of appearances
	 */
	protected int contains(final String password, final  String regex) {
		int result = 0;
		Matcher m = Pattern.compile(regex).matcher(password);
		while(m.find()) {
			result++;
		}
		return result; 
	}
	
	/**
	 * Implementation for the strength evaluation algorithm.
	 * @param password the password to be evaluated
	 * @return password's strength (1..10)
	 */
	public abstract int evaluate (String password);
}
