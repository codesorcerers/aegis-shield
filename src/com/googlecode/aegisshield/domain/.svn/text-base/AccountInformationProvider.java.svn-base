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

import com.googlecode.aegisshield.db.AegisDatabaseHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * 	Content provider for the AccountInformation data type.
 * 
 * @author Mihai Campean
 */
public class AccountInformationProvider extends ContentProvider {
	/**
	 * 	The Account Information URI provider.
	 */
	public static final Uri CONTENT_URI = 
			Uri.parse("content://com.googlecode.aegisshield.accountinformationprovider");
	
	public static final Uri CONTENT_ACCT_INFO_URI = 
			Uri.parse("content://com.googlecode.aegisshield.accountinformationprovider/accountinfo");
	
	/**
	 * Don't know how to use this yet...
	 */
	private static final int ALLROWS = 1;
	
	private static final int SINGLE_ROW = 2;
	
	private static final UriMatcher uriMatcher;
	
	//column names
	public static final String KEY_ID = "id";
	
	public static final String KEY_USER_NAME = "user_name";
	
	public static final String KEY_PASSWORD = "password";
	
	public static final String KEY_DESCRIPTION = "description";
	
	public static final String KEY_ACCOUNT_NAME = "account_name";
	
	//column indexes
	public static final int ID_COLUMN = 0;
	
	public static final int USER_NAME_COLUMN = 1;
	
	public static final int PASSWORD_COLUMN = 2;
	
	public static final int DESCRIPTION_COLUMN = 3;
	
	public static final int ACCOUNT_NAME_COLUMN = 4;
	
	/**
	 * 	The SQLiteDatabase instance used for database access in the Aegis Shield application.
	 */
	private SQLiteDatabase aegisDb;
	
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.googlecode.aegisshield.accountinformationprovider", "accountinfo", ALLROWS);
		uriMatcher.addURI("com.googlecode.aegisshield.accountinformationprovider", "accountinfo/#", SINGLE_ROW);
	}
	
	/**
	 * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count = 0;
		
		switch(uriMatcher.match(uri)) {
			case ALLROWS:
				count = aegisDb.delete(AegisDatabaseHelper.DB_TABLE_ACCT_INFO, selection, selectionArgs);
				break;
			case SINGLE_ROW:
				//TODO refactor this repetitive code into a private method
				String segment = uri.getPathSegments().get(1);
				String where = !TextUtils.isEmpty(selection) ? "AND (" + selection + ")" : "";
				count = aegisDb.delete(AegisDatabaseHelper.DB_TABLE_ACCT_INFO, 
						KEY_ID + "=" + segment + where,
						selectionArgs);
				break;
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
		
		return count;
	}

	/**
	 * 	Return the content mimetype for the account info data provider.
	 * 
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
			case ALLROWS: 
				return "vnd.android.cursor.dir/vnd.aegisshield.accountinfo";
			case SINGLE_ROW:
				return "vnd.android.cursor.item/vnd.aegisshield.accountinfo";
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	/**
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long rowId = aegisDb.insert(AegisDatabaseHelper.DB_TABLE_ACCT_INFO, "aegis", values);
		if (rowId > 0) {
			Uri insertUri = ContentUris.withAppendedId(CONTENT_ACCT_INFO_URI, rowId);
			getContext().getContentResolver().notifyChange(uri, null);
			return insertUri;
		} else {
			throw new SQLException("Failed to insert row into: " + uri);
		}
	}

	/**
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate() {
		Context context = getContext();
		AegisDatabaseHelper aegisHelper = new AegisDatabaseHelper(context, AegisDatabaseHelper.DB_NAME,
				null, AegisDatabaseHelper.DB_VERSION);
		aegisDb = aegisHelper.getWritableDatabase();
		
		return (aegisDb == null) ? false : true;
	}

	/**
	 * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		
		if (SINGLE_ROW == uriMatcher.match(uri)) {
			queryBuilder.appendWhere(KEY_ID + "=" + uri.getPathSegments().get(1)); // get the id from the uri
		}
		queryBuilder.setTables(AegisDatabaseHelper.DB_TABLE_ACCT_INFO);
		cursor = queryBuilder.query(aegisDb, projection, selection, selectionArgs, 
				null, null, sortOrder);
		// register content resolver for change notifications to the cursor result set
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return cursor;
	}

	/**
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count = 0;
		
		switch(uriMatcher.match(uri)) {
			case ALLROWS:
				count = aegisDb.update(AegisDatabaseHelper.DB_TABLE_ACCT_INFO, values, 
						selection, selectionArgs);
				break;
			case SINGLE_ROW:
				String segment = uri.getPathSegments().get(1);
				String where = !TextUtils.isEmpty(selection) ? "AND (" + selection + ")" : "";
				count = aegisDb.update(AegisDatabaseHelper.DB_TABLE_ACCT_INFO, values, 
						KEY_ID + "=" + segment + where,
						selectionArgs);
				break;
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		
		}
		
		return count;
	}

}
