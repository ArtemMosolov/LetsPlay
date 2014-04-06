package com.epam.core.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encrypter {

	public String encrypt(String text) {
		String hashPass = null;

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(text.getBytes());
			byte[] digest = messageDigest.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			hashPass = bigInt.toString(16);

			while (hashPass.length() < 32) {
				hashPass = "0" + hashPass;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		return hashPass;
	}
}
