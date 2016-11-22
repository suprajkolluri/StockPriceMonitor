package com.logicmonitor.spm.util.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;

@Component
public class PasswordDecryptUtil {
	/*
	 * public static void main(String[] args) { StandardPBEStringEncryptor
	 * encryptor = new StandardPBEStringEncryptor();
	 * encryptor.setPassword("dev"); String encryptedText =
	 * encryptor.encrypt("password"); System.out.println("Encrypted text is: " +
	 * encryptedText); }
	 */

	public String decrypt(String encryptedText) {
		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
		decryptor.setPassword("dev");
		String decryptedText = decryptor.decrypt(encryptedText);
		return decryptedText;
	}
}
