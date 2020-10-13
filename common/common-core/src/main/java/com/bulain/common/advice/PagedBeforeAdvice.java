package com.bulain.common.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import com.bulain.common.page.Search;

public class PagedBeforeAdvice implements MethodBeforeAdvice {
    private static final String COLUMN_PATTERN = "^[a-zA-Z_0-9]*$";

    public void before(Method method, Object[] args, Object target){
        if (args == null) {
            return;
        }
        for (Object arg : args) {
            if (arg instanceof Search) {
                Search search = (Search) arg;
                String orderBy = search.getOrderBy();
                
                boolean matches = orderBy.matches(COLUMN_PATTERN);
                if(!matches){
                    search.setOrderBy(null);
                    search.setSequance("asc");
                }
            }
        }
    }
}
