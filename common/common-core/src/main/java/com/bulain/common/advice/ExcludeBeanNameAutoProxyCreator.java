package com.bulain.common.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class ExcludeBeanNameAutoProxyCreator extends BeanNameAutoProxyCreator {
    private static final long serialVersionUID = -8216694474370082748L;

    private List<String> excludeBeanNames;

    @Override
    protected boolean isMatch(String beanName, String mappedName) {
        if (this.excludeBeanNames != null) {
            for (String excludeBeanName : this.excludeBeanNames) {
                if (super.isMatch(beanName, excludeBeanName)) {
                    return false;
                }
            }
        }

        return super.isMatch(beanName, mappedName);
    }

    public void setExcludeBeanNames(String[] excludeBeanNames) {
        Assert.notEmpty(excludeBeanNames, "'excludeBeanNames' must not be empty");
        this.excludeBeanNames = new ArrayList<String>(excludeBeanNames.length);
        for (String mappedName : excludeBeanNames) {
            this.excludeBeanNames.add(StringUtils.trimWhitespace(mappedName));
        }
    }

}
