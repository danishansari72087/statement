package com.nagarro.statements.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author 
 * Danish Ansari
 *
 */

public class HashUtil {

	static Logger logger = LoggerFactory.getLogger(HashUtil.class);

	public static String getMd5(String input) {
		logger.debug("inside getMd5 method");
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} 
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
