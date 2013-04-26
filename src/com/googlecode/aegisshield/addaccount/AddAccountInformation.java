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
package com.googlecode.aegisshield.addaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.googlecode.aegisshield.R;
import com.googlecode.aegisshield.accountoverview.AccountInfoOverview;
import com.googlecode.aegisshield.domain.AccountInformation;
import com.googlecode.aegisshield.domain.AccountInformationRepository;
import com.googlecode.aegisshield.password.utils.PasswordGenerator;
import com.googlecode.aegisshield.password.utils.PasswordStrength;
import com.googlecode.aegisshield.security.crypto.CryptoService;
import com.googlecode.aegisshield.utils.app.Constants;
import com.googlecode.aegisshield.utils.ui.Alerts;
import com.googlecode.aegisshield.utils.ui.CustomGradient;

/**
 * 	Activity for adding a new user account password information.
 * 
 * @author Mihai Campean
 */
public class AddAccountInformation extends Activity {
	/**
	 * 	Intent action for the AddAccountInformation activity.
	 */
	public static final String ADD_ACCT_INFO_ACTION = "com.googlecode.aegisshield.action.ADD_ACCT_INFO_ACTION";
	
	/**
	 * 	Encryption key, coming from the calling intent - this is the master password, used to encrypt password data.
	 */
	private String encryptionKey = "";
	
	/**
	 * non-linear gradient.
	 */
	private CustomGradient gradient = new CustomGradient();
	/**
	 * @param savedInstanceState
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_account);
		
		Button addAccount = (Button) findViewById(R.id.add_account_button);
		final EditText acctPassEdit = (EditText) findViewById(R.id.account_password_edit);
		final EditText acctNameEdit = (EditText) findViewById(R.id.account_name_edit);
		final EditText acctUserEdit = (EditText) findViewById(R.id.account_user_edit);
		final EditText acctDescEdit = (EditText) findViewById(R.id.account_description_edit);
		final Button generatePassword = (Button) findViewById(R.id.generate_button);
		final TextView passStrength = (TextView) findViewById(R.id.password_strength);
		
		Intent intent = getIntent();
		if (ADD_ACCT_INFO_ACTION.equals(intent.getAction())) {
			encryptionKey = intent.getExtras().getString(Constants.HASHED_PASSWORD);
		} else {
			//TODO, we should throw a runtime error here, since we cannot do anything much without a password.
		}
		
		navigateToOverview();
		
		//register password strength check
		acctPassEdit.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction() == KeyEvent.ACTION_UP) {
					verifyPassword(acctPassEdit.getText().toString(), passStrength);
				}
				return false;
			}

			
        });
		
		generatePassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String genPwd = PasswordGenerator.getPassword(Constants.GENERATED_PASSWORD_LENGTH);
				acctPassEdit.setText(genPwd);
				verifyPassword(genPwd, passStrength);
			}
		});
		
		addAccount.setOnClickListener(new OnClickListener() {
			/**
			 * 	Add the account information in the the database when the "add account" button
			 * is pressed.
			 * 
			 * @param view
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View view) {
				// add account button was pressed, we try to save the data
				if (R.id.add_account_button == view.getId()) {
					AccountInformationRepository acctRepository = 
							new AccountInformationRepository(getContentResolver());
					AccountInformation acctInformation = new AccountInformation();
					
					String acctName = acctNameEdit.getText().toString();
					String acctUser = acctUserEdit.getText().toString();
					String acctPass = acctPassEdit.getText().toString();
					String acctDesc = acctDescEdit.getText().toString();
					
					boolean emptyField = false;
					if (acctName == null || "".equals(acctName.trim())) {
						emptyField = true;
					}
					if (acctUser == null || "".equals(acctUser.trim())) {
						emptyField = true;
					}
					if (acctPass == null || "".equals(acctPass.trim())) {
						emptyField = true;
					}
					
					if (emptyField) {
						Alerts.showAlert(getString(R.string.message_alert_empty_fields), returnSelf());
					} else {
						acctInformation.setAccountName(acctName);
						acctInformation.setUserName(acctUser);
						acctInformation.setPassword(CryptoService.encrypt(acctPass, encryptionKey));
						acctInformation.setDescription(acctDesc);
						
						acctRepository.save(acctInformation);
						
						// empty the controls...
						acctNameEdit.setText("");
						acctUserEdit.setText("");
						acctPassEdit.setText("");
						acctDescEdit.setText("");
					}
				}
			}
		});
	}
	
	/**
	 * Activity returns itself privately to use in alert dialogs.
	 * 
	 * @return
	 */
	private Activity returnSelf() {
		return this;
	}
	
	/**
	 * 	Navigate back to the overview screen.
	 */
	private void navigateToOverview() {
		Button overview = (Button) findViewById(R.id.overview_button);
		
		overview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent acctOverview = new Intent(AccountInfoOverview.ACCT_INFO_OVERVIEW_ACTION);
				acctOverview.putExtra(Constants.HASHED_PASSWORD, encryptionKey);
				startActivity(acctOverview);
				finish();
			}
		});
	}
	
	private void verifyPassword(String password, final TextView passStrength) {
		int strength = PasswordStrength.evaluate(password);
		CharSequence[] strengthLabels = getResources().getTextArray(R.array.password_strength);
		if(strength >= 8) {
			passStrength.setText(strengthLabels[2]);
		}else if (strength >= 5){
			passStrength.setText(strengthLabels[1]);
		}else {
			passStrength.setText(strengthLabels[0]);
		}
		
		gradient.moveCenter(1 - (float) strength/10);
		passStrength.setBackgroundDrawable(gradient);
	}
}
