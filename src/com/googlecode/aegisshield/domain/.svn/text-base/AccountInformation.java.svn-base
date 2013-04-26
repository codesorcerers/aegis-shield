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
package com.googlecode.aegisshield.domain;

import java.io.Serializable;

/**
 * 	Domain object holding the information about the user accounts.
 * 
 * @author Mihai Campean
 */
public class AccountInformation implements Serializable {
	/**
	 * 	Generated serial version id.
	 */
	private static final long serialVersionUID = 7358580701503426857L;

	/**
	 * 	The technical account id for this account information object.
	 */
	private int id;
	
	/**
	 * 	The user name for this account information.
	 */
	private String userName;
	
	/**
	 * 	The password of the account entered as account information.
	 */
	private String password;
	
	/**
	 * 	The description of this account information.
	 */
	private String description;
	
	/**
	 * 	The name of the account for which the password information is saved.
	 */
	private String accountName;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id + "-" + accountName + "-" + userName;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		boolean isEqual = false;
		
		if (o instanceof AccountInformation) {
			AccountInformation other = (AccountInformation) o;
			isEqual = this.id == other.getId();
		}
		
		return isEqual;
	}
	
}
