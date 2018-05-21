package com.bulain.json;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.WrapDynaBean;
import org.junit.Test;


public class RequestTest {
    @Test
    public void testToJSONBean(){
        Request request = new Request();
        request.setRepository("http://localhost/svn/demo");
        
        JSON json = JSONSerializer.toJSON(request);
        System.out.println(json);
    }
    
    @Test
    public void testToJSONBeanWithConfig(){
        Request request = new Request();
        request.setRepository("http://localhost/svn/demo");
        
        JsonConfig config  = new JsonConfig();
        
        JSON json = JSONSerializer.toJSON(request, config);
        System.out.println(json);
    }
    
    @Test
    public void testToJSONLazyDynaBean(){
        DynaBean bean = new LazyDynaBean();
        bean.set("repository", "http://localhost/svn/demo");
        
        JSON json = JSONSerializer.toJSON(bean);
        System.out.println(json);
    }
    
    @Test
    public void testToJSONWrapDynaBean(){
        Request request = new Request();
        DynaBean bean = new WrapDynaBean(request);
        bean.set("repository", "http://localhost/svn/demo");
        
        JSON json = JSONSerializer.toJSON(bean);
        System.out.println(json);
    }
    
    @Test
    public void testToJava(){
        String str = "{'repository':'http://localhost/svn/demo'}";
        
        JSON json = JSONSerializer.toJSON(str);
        DynaBean bean = (DynaBean)JSONSerializer.toJava(json);
        System.out.println(bean);
    }
    
    @Test
    public void testToJavaWithConfig(){
        String str = "{'repository':'http://localhost/svn/demo'}";
        
        JsonConfig config  = new JsonConfig();
        config.setRootClass(Request.class);
        
        JSON json = JSONSerializer.toJSON(str);
        Request bean = (Request)JSONSerializer.toJava(json, config);
        System.out.println(bean);
    }
}
