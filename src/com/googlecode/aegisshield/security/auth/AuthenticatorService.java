/*
 * Copyright (C) 2009 Mihai Campean
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
package com.googlecode.aegisshield.security.auth;

import java.util.logging.LoggingPermission;

import android.content.Context;
import android.util.Log;

import com.googlecode.aegisshield.security.crypto.CannotDecryptCheckTextException;
import com.googlecode.aegisshield.security.crypto.CryptoService;
import com.googlecode.aegisshield.utils.app.ApplicationPreferenceManager;


/**
 *  Service for authenticating the master password and allowing the user to view sensitive information.
 * I'm using a static verification token, which will have an encrypted form once the master password is 
 * introduced for the first time (the 2 introduced master passwords match). After that authentication
 * will be done based on the clear token text being the same as the decrypted token, using the entered 
 * password.
 * 
 * @author Mihai Campean
 */
public class AuthenticatorService {
	
	/**
	 * 	Android context.
	 */
	private Context context;
	
	/**
	 * 	Constructor taking an Android context as argument.
	 * 
	 * @param context
	 */
	public AuthenticatorService(Context context) {
		this.context = context;
	}
	
	/**
	 * 	Method used for authentication of the user. The idea behind this procedure is pretty simple. We save a security
	 * text in clear and in encrypted form when the application is first run, then each time a user needs to see the
	 * application private data, he or she must use the password entered when the application first ran. If the password is
	 * correct, it means that the clear security token should be equal to the decrypted security token, that was decrypted
	 * using the password.
	 * 
	 * @param password
	 * @return
	 */
	public boolean authenticate(String password) {
		boolean auth = false;
		ApplicationPreferenceManager prefsManager = new ApplicationPreferenceManager(context);
		String clearToken = prefsManager.loadClearSecurityToken();
		String encryptedToken = prefsManager.loadEncryptedSecurityToken();
		String decryptedToken = "";
		try {
			decryptedToken = CryptoService.decrypt(encryptedToken, password);
		} catch (CannotDecryptCheckTextException e) {
			Log.w("aegis", e);
		}
		
		if (clearToken.equals(decryptedToken)) {
			auth = true;
		}
		
		return auth;
	}
}
