package com.bulain.jbpm4order.identity;

import java.util.Arrays;
import java.util.List;

import org.jbpm.api.JbpmException;
import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.identity.spi.IdentitySession;
import org.jbpm.pvm.internal.util.CollectionUtil;

public class IdentitySessionImpl implements IdentitySession {
    private IdentityService identityService;

    public String createUser(String userName, String givenName, String familyName, String businessEmail) {
        try {
            User user = findUserById(userName);
            if (user != null) {
                throw new JbpmException("Cannot create user, userId: [" + userName + "] has been used");
            }
        } catch (Exception ex) {
            throw new JbpmException("Cannot create user, error while validating", ex);
        }
        UserImpl user = new UserImpl(userName, givenName, familyName);
        user.setBusinessEmail(businessEmail);

        identityService.saveUser(user);

        return user.getId();
    }

    public User findUserById(String userId) {
        return identityService.findUserById(userId);
    }

    public List<User> findUsersById(String... userIds) {
        List<?> users = identityService.findUsersById(userIds);
        if (userIds.length != users.size()) {
            throw new JbpmException("not all users were found: " + Arrays.toString(userIds));
        }
        return CollectionUtil.checkList(users, User.class);
    }

    public List<User> findUsers() {
        List<?> users = identityService.findUsers();
        return CollectionUtil.checkList(users, User.class);
    }

    public void deleteUser(String userId) {
        identityService.deleteUser(userId);
    }

    public String createGroup(String groupName, String groupType, String parentGroupId) {
        try {
            Group group = findGroupById(groupName);
            if (group != null) {
                throw new JbpmException("Cannot create group, groupId: [" + groupName + "] has been used");
            }
        } catch (Exception ex) {
            throw new JbpmException("Cannot create group, error while validating", ex);
        }
        GroupImpl group = new GroupImpl();
        String groupId = groupType != null ? groupType + "." + groupName : groupName;
        group.setId(groupId);
        group.setName(groupName);
        group.setType(groupType);

        if (parentGroupId != null) {
            group.setParentId(parentGroupId);
        }

        identityService.saveGroup(group);

        return group.getId();
    }

    public List<User> findUsersByGroup(String groupId) {
        List<?> users = identityService.findUsersByGroup(groupId);
        return CollectionUtil.checkList(users, User.class);
    }

    public Group findGroupById(String groupId) {
        return identityService.findGroupById(groupId);
    }

    public List<Group> findGroupsByUserAndGroupType(String userId, String groupType) {
        List<?> groups = identityService.findGroupsByUserAndGroupType(userId, groupType);
        return CollectionUtil.checkList(groups, Group.class);
    }

    public List<Group> findGroupsByUser(String userId) {
        List<?> groups = identityService.findGroupsByUser(userId);
        return CollectionUtil.checkList(groups, Group.class);
    }

    public List<Group> findGroups() {
        List<?> groups = identityService.findGroups();
        return CollectionUtil.checkList(groups, Group.class);
    }

    public void deleteGroup(String groupId) {
        identityService.deleteGroup(groupId);
    }

    public void createMembership(String userId, String groupId, String role) {
        User user = findUserById(userId);
        if (user == null) {
            throw new JbpmException("user " + userId + " doesn't exist");
        }
        Group group = findGroupById(groupId);
        if (group == null) {
            throw new JbpmException("group " + groupId + " doesn't exist");
        }

        identityService.saveMembership(userId, groupId, role);
    }

    public void deleteMembership(String userId, String groupId, String role) {
        identityService.deleteMembership(userId, groupId, role);
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }

}
