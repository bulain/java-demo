package com.bulain.jdbc;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
import javax.sql.DataSource;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.util.PropertyBeanSetter;

public class JasyptHikariJNDIFactory implements ObjectFactory {
    
    private char[] password = new char[]{'b', 'u', 'l', 'a', 'i', 'n'};
    private StringEncryptor stringEncryptor;

    public JasyptHikariJNDIFactory() {
        StandardPBEStringEncryptor impl = new StandardPBEStringEncryptor();
        impl.setPasswordCharArray(password);
        stringEncryptor = impl;
    }
    
    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
            throws Exception {
        // We only know how to deal with <code>javax.naming.Reference</code> that specify a class name of "javax.sql.DataSource"
        if ((obj == null) || !(obj instanceof Reference)) {
            return null;
        }

        Reference ref = (Reference) obj;
        if (!"javax.sql.DataSource".equals(ref.getClassName())) {
            throw new NamingException(ref.getClassName() + " is not a valid class name/type for this JNDI factory.");
        }

        Properties properties = new Properties();
        for (String propertyName : PropertyBeanSetter.getPropertyNames(HikariConfig.class)) {
            RefAddr ra = ref.get(propertyName);
            if (ra != null) {
                String propertyValue = ra.getContent().toString();
                
                if (PropertyValueEncryptionUtils.isEncryptedValue(propertyValue)) {
                    propertyValue = PropertyValueEncryptionUtils.decrypt(propertyValue, stringEncryptor);
                }
                
                properties.setProperty(propertyName, propertyValue);
            }
        }

        return createDataSource(properties, nameCtx);
    }

    private DataSource createDataSource(Properties properties, Context context) {
        if (properties.getProperty("dataSourceJNDI") != null) {
            return lookupJndiDataSource(properties, context);
        }

        return new HikariDataSource(new HikariConfig(properties));
    }

    private DataSource lookupJndiDataSource(Properties properties, Context context) {
        DataSource jndiDS = null;
        String jndiName = properties.getProperty("dataSourceJNDI");
        try {
            if (context != null) {
                jndiDS = (DataSource) context.lookup(jndiName);
            } else {
                throw new RuntimeException("dataSourceJNDI property is configued, but local JNDI context is null.");
            }
        } catch (NamingException e) {
            throw new RuntimeException("The name \"" + jndiName + "\" can not be found in the local context.");
        }

        if (jndiDS == null) {
            try {
                context = (Context) (new InitialContext());
                jndiDS = (DataSource) context.lookup(jndiName);
            } catch (NamingException e) {
                throw new RuntimeException("The name \"" + jndiName + "\" can not be found in the InitialContext.");
            }
        }

        if (jndiDS != null) {
            HikariConfig config = new HikariConfig(properties);
            config.setDataSource(jndiDS);
            return new HikariDataSource(config);
        }

        return null;
    }
}
