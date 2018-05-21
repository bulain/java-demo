package com.bulain.common.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bulain.common.model.User;

public class UserMapperImpl implements UserMapper {
    private Map<Long, User> mapUser = new HashMap<Long, User>();
    
    public int deleteByPrimaryKey(Long id) {
        User remove = mapUser.remove(id);
        return remove == null ? 0 : 1;
    }
    
    public int insert(User record) {
        mapUser.put(record.getId(), record);
        return 1;
    }
    
    public int insertSelective(User record) {
        mapUser.put(record.getId(), record);
        return 1;
    }
    
    public User selectByPrimaryKey(Long id) {
        return mapUser.get(id);
    }
    
    public int updateByPrimaryKeySelective(User record) {
        if (mapUser.containsKey(record.getId())) {
            mapUser.put(record.getId(), record);
            return 1;
        }
        return 0;
    }
    
    public int updateByPrimaryKey(User record) {
        if (mapUser.containsKey(record.getId())) {
            mapUser.put(record.getId(), record);
            return 1;
        }
        return 0;
    }
    
    public Map<Long, User> getMapUser() {
        return mapUser;
    }
    
    public void setMapUser(Map<Long, User> mapUser) {
        this.mapUser = mapUser;
    }
    
    public void clearMapUser() {
        this.mapUser.clear();
    }
    
    public long countByDuplicate(User record) {
        String firstName = record.getFirstName();
        String lastName = record.getLastName();
        
        long cnt = 0;
        Collection<User> values = mapUser.values();
        for (User user : values) {
            if (firstName.equals(user.getFirstName()) && lastName.equals(user.getLastName())) {
                cnt++;
            }
        }
        
        return cnt;
    }
}
