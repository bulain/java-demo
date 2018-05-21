package com.bulain.common.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import com.bulain.common.model.Versionable;
import com.bulain.common.util.SystemClock;

public class VersionBeforeAdvice implements MethodBeforeAdvice {

    public void before(Method method, Object[] args, Object target){
        if (args == null) {
            return;
        }
        for (Object arg : args) {
            if (arg instanceof Versionable) {
                Versionable entity = (Versionable) arg;
                long timeInMillis = SystemClock.getTimeInMillis();
                Long version = Long.valueOf(timeInMillis);
                entity.setVersion(version);
            }
        }
    }
}
