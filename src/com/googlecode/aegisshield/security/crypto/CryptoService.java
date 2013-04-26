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
package com.googlecode.aegisshield.security.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import android.util.Log;

/**
 * 	Utility class providing encryption and digest functionality for working with sensitive data, such as the
 * account passwords. Service class is final and with static methods.
 * 
 * @author Mihai Campean
 */
public final class CryptoService {
	/**
	 * PBE encryption algorithm.
	 */
	private static final String PBE_ENC_ALGORITHM = "PBEWITHSHA-256AND256BITAES-CBC-BC";

	/**
	 * 	8 byte salt used for encryption/decryption.
	 */
	private static final byte[] salt = {
		(byte) 0xc1, (byte) 0xe2, (byte) 0xae, (byte) 0x91,
		(byte) 0xdf, (byte) 0x11, (byte) 0x1f, (byte) 0xaa
	};
	
	/**
	 * 	PBE iteration count.
	 */
	private static final int count = 21;
	
	/**
	 *  Class cannot be instantiated.
	 */
	private CryptoService() {
	}
	
	/**
	 * 	Init the cipher for password based encryption or decryption, based on the mode given as
	 * argument.
	 * 
	 * @param mode
	 * @param password
	 * @return
	 */
	private static Cipher initCipher(int mode, String password) {
		Cipher pbeCipher = null;
		try {
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, count, 32);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBE_ENC_ALGORITHM);
			SecretKey key = keyFactory.generateSecret(keySpec);
			AlgorithmParameterSpec aps = new PBEParameterSpec(salt, count);
			pbeCipher = Cipher.getInstance(PBE_ENC_ALGORITHM);
			pbeCipher.init(mode, key, aps);
		} catch (NoSuchAlgorithmException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		} catch (InvalidKeySpecException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		} catch (InvalidKeyException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		} catch (InvalidAlgorithmParameterException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		} catch (NoSuchPaddingException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		}
		return pbeCipher;
	}
	
	/**
	 * 	Convert an array of bytes representing a string to a hex encoded string.
	 * 
	 * @param bytes
	 * @return
	 */
	private static String toHexString(byte bytes[]) {
		
		StringBuffer retString = new StringBuffer();
		for (int i = 0; i < bytes.length; ++i) {
		    retString.append(Integer
			    .toHexString(0x0100 + (bytes[i] & 0x00FF))
			    .substring(1));
		}
		return retString.toString();
    }
	
	/**
	 *  Convert a hex encoded string into an array of bytes.
	 * 
	 * @param hex
	 * @return
	 */
	private static byte[] hexStringToBytes(String hex) {
    	
    	byte [] bytes = new byte [hex.length() / 2];
		int j = 0;
		for (int i = 0; i < hex.length(); i += 2) {
			try {
				String hexByte = hex.substring(i, i + 2);

				Integer I = new Integer (0);
				I = Integer.decode("0x" + hexByte);
				int k = I.intValue();
				bytes[j++] = new Integer(k).byteValue();
			} catch (NumberFormatException e) {
				Log.i("aegis", e.getLocalizedMessage());
				return bytes;
			} catch (StringIndexOutOfBoundsException e) {
				Log.i("aegis", "StringIndexOutOfBoundsException");
				return bytes;
			}
		}
    	return bytes;
    }
	
	/**
	 * 	Uses an MD5 algorithm in order to hash the string given as argument, returning the resulted
	 * hashed string.
	 *  
	 * @param msg
	 * @return the hashed message
	 */
	public static String buildMessageDigest(String msg) {
		String hashCodedMsg = null;
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(msg.getBytes());
			hashCodedMsg = new String(md5.digest());
		} catch (NoSuchAlgorithmException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		}
		
		return hashCodedMsg;
	}
	
	/**
	 *  Encrypts a given string.
	 * 
	 * @param data
	 * @param password
	 */
	public static String encrypt(String data, String password) {
		byte[] encrypted = new byte[] {};
		Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, password);
		
		try {
			encrypted = cipher.doFinal(data.getBytes());
		} catch (IllegalBlockSizeException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		} catch (BadPaddingException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		}
		
		return toHexString(encrypted);
	}
	
	/**
	 *  Decrypts a given string. The string to be decrypted must be hex encoded, otherwise the method won't work.
	 * This should always be used with the output of the encrypt method, which returns a hex encoded encrypted string.
	 * 	This method throws a CannodDecryptCheckTextException in most cases when the given password key is invalid. 
	 * According to the way encryption/decryption is implemented, the BadPaddingException occurs if the private encryption
	 * key with which you try to decrypt data is invalid. However, in some freak cases the data may be decrypted so
	 * extra checks are needed to see if the decryption result is rubbish or not.
	 * 
	 * @param encData
	 * @param password
	 * @return
	 * @throws CannotDecryptCheckTextException this exception occurs in most cases when the key is invalid.
	 */
	public static String decrypt(String encData, String password) throws CannotDecryptCheckTextException {
		byte[] decrypted = new byte[] {};
		Cipher cipher = initCipher(Cipher.DECRYPT_MODE, password);
		
		try {
			decrypted = cipher.doFinal(hexStringToBytes(encData));
		} catch (IllegalBlockSizeException e) {
			Log.e("aegis", e.getMessage());
			throw new CryptoServiceException(e);
		} catch (BadPaddingException e) {
			Log.e("aegis", e.getMessage());
			throw new CannotDecryptCheckTextException(e);
		}
		
		return new String(decrypted);
	}

}
