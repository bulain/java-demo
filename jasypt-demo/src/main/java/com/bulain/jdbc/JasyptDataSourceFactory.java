package com.bulain.jdbc;

import java.util.Properties;

import javax.naming.Context;
import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class JasyptDataSourceFactory extends DataSourceFactory {

	private char[] password = new char[]{'b', 'u', 'l', 'a', 'i', 'n'};
	private StringEncryptor stringEncryptor;

	public JasyptDataSourceFactory() {
		StandardPBEStringEncryptor impl = new StandardPBEStringEncryptor();
		impl.setPasswordCharArray(password);
		stringEncryptor = impl;
	}

	@Override
	public DataSource createDataSource(Properties properties, Context context, boolean XA) throws Exception {
		if (!(properties instanceof EncryptableProperties)) {
			properties = new EncryptableProperties(properties, stringEncryptor);
		}
		return super.createDataSource(properties, context, XA);
	}

}
