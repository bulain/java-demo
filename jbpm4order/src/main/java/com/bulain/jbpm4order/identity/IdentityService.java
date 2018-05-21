package com.bulain.jbpm4order.identity;

import java.util.List;

public interface IdentityService {
    void saveUser(UserImpl user);
    UserImpl findUserById(String userId);
    List<UserImpl> findUsers();
    List<UserImpl> findUsersById(String[] userIds);
    List<UserImpl> findUsersByGroup(String groupId);
    void deleteUser(String userId);

    void saveGroup(GroupImpl group);
    GroupImpl findGroupById(String groupId);
    List<GroupImpl> findGroups();
    List<GroupImpl> findGroupsByUser(String userId);
    List<GroupImpl> findGroupsByUserAndGroupType(String userId, String groupType);
    void deleteGroup(String groupId);

    void saveMembership(String userId, String groupId, String role);
    void deleteMembership(String userId, String groupId, String role);
}
