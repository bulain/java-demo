package com.bulain.common.advice;

import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.aop.MethodBeforeAdvice;

import com.bulain.common.model.Trackable;
import com.bulain.common.util.SystemClock;

public class UpdateBeforeAdvice implements MethodBeforeAdvice {
    public void before(Method method, Object[] args, Object target){
        if (args == null) {
            return;
        }
        for (Object arg : args) {
            if (arg instanceof Trackable) {
                Trackable entity = (Trackable) arg;
                Date date = SystemClock.getDate();
                entity.setUpdatedAt(date);
                entity.setUpdatedBy("updatedBy");
            }
        }
    }
}
