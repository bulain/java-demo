package com.bulain.gwt.form.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("mbeanService.rpc")
public interface MBeanService extends RemoteService {

    List<String> queryMBeanInfo();

    MbeanInfo getMBeanInfo(String objectName);

    String submitOperation(String objectName, String operationName, String[] params);

}
