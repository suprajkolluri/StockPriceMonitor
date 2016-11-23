package com.logicmonitor.spm.util.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;

/**
 * Utility to decrpy passwords using Jasypt
 * 
 * @author Supraj
 *
 */
@Component
public class PasswordDecryptUtil {
	// code used for encrypting the password
	/*
	 * public static void main(String[] args) { StandardPBEStringEncryptor
	 * encryptor = new StandardPBEStringEncryptor();
	 * encryptor.setPassword("dev"); String encryptedText =
	 * encryptor.encrypt(""); System.out.println("Encrypted text is: " +
	 * encryptedText); }
	 */

	/**
	 * Decypt a given password using jasypt
	 * 
	 * @param encryptedText
	 *            The password that is encrypted
	 * 
	 * @return The decrypted password
	 */
	public String decrypt(String encryptedText) {
		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
		decryptor.setPassword("dev");
		String decryptedText = decryptor.decrypt(encryptedText);
		return decryptedText;
	}
}
