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
package com.googlecode.aegisshield.accountoverview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.googlecode.aegisshield.R;
import com.googlecode.aegisshield.domain.AccountInformation;

/**
 * 	Account Info adapter to manage the AccountInformation objects that are to be displayed in the 
 * overview list.
 * 
 * @author Mihai Campean
 */
public class AccountInfoAdapter extends BaseAdapter {
	/**
	 * 	An array list of AccountInformation objects.
	 */
	private List<AccountInformation> acctInfoList;
	
	/**
	 * 	The context object. (will describe more when I know more)
	 */
	private Context context;
	
	/**
	 * 	Public constructor taking an account information objects list.
	 * 
	 * @param acctInfoList
	 */
	public AccountInfoAdapter(Context context, List<AccountInformation> acctInfoList) {
		this.acctInfoList = acctInfoList;
		this.context = context;
	}
	
	/**
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return acctInfoList.size();
	}

	/**
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return acctInfoList.get(position);
	}

	/**
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return acctInfoList.get(position).getId();
	}

	/**
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(this.context);
			convertView = inflater.inflate(R.layout.list_item, parent, false);
		}
		
		AccountInformation info = acctInfoList.get(position);
		TextView acctName = (TextView) convertView.findViewById(R.id.acct_info_item_account);
		TextView acctDesc = (TextView) convertView.findViewById(R.id.acct_info_item_description);
		
		acctName.setText(info.getAccountName());
		acctDesc.setText(info.getDescription());
		
		return convertView;
	}
	/**
	 * 	Sort of speaks for itself. As a good fellow programmer once said, if you cannot understand this code,
	 * go flip burgers.
	 * 
	 * @param position
	 * @return
	 */
	public AccountInformation remove(int position) {
		return acctInfoList.remove(position);
	}

}
