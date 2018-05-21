package com.bulain.gwt.form.server;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.QueryExp;
import javax.management.ReflectionException;

import com.bulain.gwt.form.client.MBeanService;
import com.bulain.gwt.form.client.MbeanAttr;
import com.bulain.gwt.form.client.MbeanInfo;
import com.bulain.gwt.form.client.MbeanOper;
import com.bulain.gwt.form.client.MbeanParam;

public class MBeanServiceImpl implements MBeanService {

    public List<String> queryMBeanInfo() {
        List<String> listMBeanInfo = new ArrayList<String>();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = null;
        QueryExp query = null;
        Set<ObjectName> queryNames = mbs.queryNames(name, query);
        for (ObjectName objectName : queryNames) {
            listMBeanInfo.add(objectName.getCanonicalName());
        }
        return listMBeanInfo;
    }

    public MbeanInfo getMBeanInfo(String objectName) {
        MbeanInfo bean = new MbeanInfo();;
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName objName = ObjectName.getInstance(objectName);
            MBeanInfo mBeanInfo = mbs.getMBeanInfo(objName);

            bean.setObjectName(objName.getCanonicalName());
            bean.setClassName(mBeanInfo.getClassName());
            bean.setDescription(mBeanInfo.getDescription());

            List<MbeanAttr> attrs = new ArrayList<MbeanAttr>();
            MBeanAttributeInfo[] attributes = mBeanInfo.getAttributes();
            for (MBeanAttributeInfo attrInfo : attributes) {
                MbeanAttr attr = new MbeanAttr();
                attr.setName(attrInfo.getName());
                attr.setType(attrInfo.getType());
                attr.setDescription(attrInfo.getDescription());
                Object value = mbs.getAttribute(objName, attrInfo.getName());
                attr.setValue(String.valueOf(value));

                attrs.add(attr);
            }
            bean.setAttrs(attrs.toArray(new MbeanAttr[0]));

            List<MbeanOper> opers = new ArrayList<MbeanOper>();
            MBeanOperationInfo[] operations = mBeanInfo.getOperations();
            for (MBeanOperationInfo operInfo : operations) {
                MbeanOper oper = new MbeanOper();
                oper.setInfo("Operation:");
                oper.setName(operInfo.getName());
                oper.setType(operInfo.getReturnType());
                oper.setDescription(operInfo.getDescription());

                List<MbeanParam> params = new ArrayList<MbeanParam>();
                MBeanParameterInfo[] signature = operInfo.getSignature();
                int index = 1;
                for (MBeanParameterInfo paramInfo : signature) {
                    MbeanParam param = new MbeanParam();
                    param.setInfo("Parameter-" + index++);
                    param.setName(paramInfo.getName());
                    param.setType(paramInfo.getType());
                    param.setDescription(paramInfo.getDescription());
                    params.add(param);
                }
                oper.setParams(params.toArray(new MbeanParam[0]));
                opers.add(oper);
            }
            bean.setOpers(opers.toArray(new MbeanOper[0]));
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (ReflectionException e) {
            e.printStackTrace();
        } catch (AttributeNotFoundException e) {
            e.printStackTrace();
        } catch (MBeanException e) {
            e.printStackTrace();
        }

        return bean;
    }

    public String submitOperation(String objectName, String operationName, String[] params) {
        Object invoke = null;
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName objName = ObjectName.getInstance(objectName);
            MBeanInfo mBeanInfo = mbs.getMBeanInfo(objName);
            MBeanOperationInfo[] operations = mBeanInfo.getOperations();
            List<String> signature = new ArrayList<String>();
            for (MBeanOperationInfo oper : operations) {
                if (!oper.getName().equals(operationName)) {
                    continue;
                }
                MBeanParameterInfo[] parameterInfos = oper.getSignature();
                for (MBeanParameterInfo param : parameterInfos) {
                    signature.add(param.getType());
                }
            }

            invoke = mbs.invoke(objName, operationName, params, signature.toArray(new String[0]));
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (ReflectionException e) {
            e.printStackTrace();
        } catch (MBeanException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        return invoke == null ? null : String.valueOf(invoke);
    }
}
