package com.bulain.jasypt;

import static org.junit.Assert.assertEquals;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Before;
import org.junit.Test;

public class StringEncryptorTest {

	private StringEncryptor stringEncryptor;

	@Before
	public void setUp() {
		StandardPBEStringEncryptor impl = new StandardPBEStringEncryptor();
		impl.setPassword("bulain");
		stringEncryptor = impl;
	}

	@Test
	public void testEncryptAndDecrypt() {
		String password = "envision";
		String hashPassword = stringEncryptor.encrypt(password);
		String decrypt = stringEncryptor.decrypt(hashPassword);
		assertEquals(password, decrypt);
	}

}
