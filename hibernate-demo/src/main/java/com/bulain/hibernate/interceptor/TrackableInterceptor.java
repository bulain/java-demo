package com.bulain.hibernate.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.common.model.Trackable;
import com.bulain.common.util.SystemClock;

public class TrackableInterceptor extends EmptyInterceptor {
    
    private static final long serialVersionUID = -2845222092059478050L;
    private static final Logger logger = LoggerFactory.getLogger(TrackableInterceptor.class);
    
    private static final String UPDATED_BY = "updatedBy";
    private static final String CREATED_BY = "createdBy";
    private static final String UPDATED_AT = "updatedAt";
    private static final String CREATED_AT = "createdAt";

    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        logger.debug("onSave() - start");
        
        boolean changed = false;
        if (entity instanceof Trackable) {
            Date date = SystemClock.getDate();
            for (int i = 0; i < propertyNames.length; i++) {
                String name = propertyNames[i];
                if (CREATED_AT.equals(name) || UPDATED_AT.equals(name)) {
                    state[i] = date;
                } else if (CREATED_BY.equals(name) || UPDATED_BY.equals(name)) {
                    state[i] = "createdBy";
                }
            }
            changed = true;
        }
        
        logger.debug("onSave() - end");
        
        return changed;
    }

    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) {
        logger.debug("onFlushDirty() - start");
        
        boolean changed = false;
        if (entity instanceof Trackable) {
            Date date = SystemClock.getDate();
            for (int i = 0; i < propertyNames.length; i++) {
                String name = propertyNames[i];
                if (UPDATED_AT.equals(name)) {
                    currentState[i] = date;
                } else if (UPDATED_BY.equals(name)) {
                    currentState[i] = "updatedBy";
                }
            }
            changed =  true;
        }
        
        logger.debug("onFlushDirty() - end");
        
        return changed;
    }
}
