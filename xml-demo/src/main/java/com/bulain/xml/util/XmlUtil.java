package com.bulain.xml.util;

import java.beans.Introspector;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class XmlUtil {
    private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    public static <T> String marshal(T obj, boolean format, boolean fragment) {
        if (obj == null) {
            return null;
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        String xml = null;
        try {
            Map<String, Object> props = new HashMap<String, Object>();
            props.put(Marshaller.JAXB_FORMATTED_OUTPUT, format);
            props.put(Marshaller.JAXB_FRAGMENT, fragment);

            _marshal(obj, os, props);

            xml = os.toString();
        } catch (Exception e) {
            logger.error("marshal()-", e);
        } finally {
            IOUtils.closeQuietly(os);
        }

        return xml;
    }

    public static <T> T unmarshal(String xml, Class<T> clazz) {

        if (!StringUtils.hasText(xml)) {
            return null;
        }

        InputStream is = new ByteArrayInputStream(xml.getBytes());

        T obj = null;
        try {
            obj = JAXB.unmarshal(is, clazz);
        } catch (Exception e) {
            logger.error("unmarshal()-", e);
        } finally {
            IOUtils.closeQuietly(is);
        }

        return obj;

    }

    //==========内部实现参考javax.xml.bind.JAXB===============
    private static final class Cache {
        final Class<?> type;
        final JAXBContext context;

        public Cache(Class<?> type) throws JAXBException {
            this.type = type;
            this.context = JAXBContext.newInstance(type);
        }
    }
    private static volatile WeakReference<Cache> cache;
    private static <T> JAXBContext getContext(Class<T> type) throws JAXBException {
        WeakReference<Cache> c = cache;
        if (c != null) {
            Cache d = c.get();
            if (d != null && d.type == type)
                return d.context;
        }

        Cache d = new Cache(type);
        cache = new WeakReference<Cache>(d);

        return d.context;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void _marshal(Object jaxbObject, Object xml, Map<String, Object> props) {
        try {
            JAXBContext context;

            if (jaxbObject instanceof JAXBElement) {
                context = getContext(((JAXBElement<?>) jaxbObject).getDeclaredType());
            } else {
                Class<?> clazz = jaxbObject.getClass();
                XmlRootElement r = clazz.getAnnotation(XmlRootElement.class);
                context = getContext(clazz);
                if (r == null) {
                    jaxbObject = new JAXBElement(new QName(inferName(clazz)), clazz, jaxbObject);
                }
            }

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Set<Entry<String, Object>> entrySet = props.entrySet();
            for (Entry<String, Object> entry : entrySet) {
                m.setProperty(entry.getKey(), entry.getValue());
            }
            m.marshal(jaxbObject, toResult(xml));
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        } catch (IOException e) {
            throw new DataBindingException(e);
        }
    }

    private static String inferName(Class<?> clazz) {
        return Introspector.decapitalize(clazz.getSimpleName());
    }

    private static Result toResult(Object xml) throws IOException {
        if (xml == null)
            throw new IllegalArgumentException("no XML is given");

        if (xml instanceof String) {
            try {
                xml = new URI((String) xml);
            } catch (URISyntaxException e) {
                xml = new File((String) xml);
            }
        }
        if (xml instanceof File) {
            File file = (File) xml;
            return new StreamResult(file);
        }
        if (xml instanceof URI) {
            URI uri = (URI) xml;
            xml = uri.toURL();
        }
        if (xml instanceof URL) {
            URL url = (URL) xml;
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(false);
            con.connect();
            return new StreamResult(con.getOutputStream());
        }
        if (xml instanceof OutputStream) {
            OutputStream os = (OutputStream) xml;
            return new StreamResult(os);
        }
        if (xml instanceof Writer) {
            Writer w = (Writer) xml;
            return new StreamResult(w);
        }
        if (xml instanceof Result) {
            return (Result) xml;
        }
        throw new IllegalArgumentException("I don't understand how to handle " + xml.getClass());
    }
}
