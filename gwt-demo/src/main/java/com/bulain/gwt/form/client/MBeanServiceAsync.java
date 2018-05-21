package com.bulain.gwt.form.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MBeanServiceAsync {

    void queryMBeanInfo(AsyncCallback<List<String>> callback);

    void getMBeanInfo(String objectName, AsyncCallback<MbeanInfo> callback);

    void submitOperation(String objectName, String operationName, String[] params, AsyncCallback<String> callback);

}
