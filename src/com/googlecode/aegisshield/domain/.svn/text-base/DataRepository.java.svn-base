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

import java.util.List;

import android.net.Uri;

/**
 * 	Interface for defining the contract of domain repositories. All repositories must implement this
 * interface. This is also a generic type interface, so it can be used for all types of domain objects
 * 
 * @author Mihai Campean
 */
public interface DataRepository<E> {
	/**
	 * 	The save method for data repositories.
	 * 
	 * @param domainObject
	 * @return
	 */
	public Uri save(E domainObject);
	
	/**
	 * 	Loads all the objects from the database.
	 * 
	 * @return
	 */
	public List<E> loadAll();
	
	/**
	 * 	Deletes the specified domain object from the database.
	 * 
	 * @param domainObject
	 * @return the the number of deleted rows
	 */
	public int delete(E domainObject);
	
	/**
	 * 	Updates the specified domain object in the database.
	 * @param domainObject
	 * @return
	 */
	public int update(E domainObject);
}
