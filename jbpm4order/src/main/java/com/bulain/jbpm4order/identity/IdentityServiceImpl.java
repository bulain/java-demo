package com.bulain.jbpm4order.identity;

import java.util.ArrayList;
import java.util.List;

import com.bulain.common.dao.GroupLoginMapper;
import com.bulain.common.dao.GroupMapper;
import com.bulain.common.dao.LoginMapper;
import com.bulain.common.dao.PersonMapper;
import com.bulain.common.model.Group;
import com.bulain.common.model.GroupLogin;
import com.bulain.common.model.Login;
import com.bulain.common.model.Person;
import com.bulain.common.pojo.GroupSearch;
import com.bulain.common.pojo.LoginSearch;
import com.bulain.common.pojo.PersonSearch;

public class IdentityServiceImpl implements IdentityService {
    private PersonMapper personMapper;
    private LoginMapper loginMapper;
    private GroupMapper groupMapper;
    private GroupLoginMapper groupLoginMapper;

    public void saveUser(UserImpl user) {
        Person person = new Person();
        person.setFirstName(user.getGivenName());
        person.setLastName(user.getFamilyName());
        personMapper.insert(person);

        PersonSearch search = new PersonSearch();
        search.setFirstName(person.getFirstName());
        search.setLastName(person.getLastName());
        List<Person> find = personMapper.find(search);
        if (find.size() > 0) {
            Person exist = find.get(find.size() - 1);
            Login login = new Login();
            login.setLoginName(user.getId());
            login.setEmail(user.getBusinessEmail());
            login.setPersonId(exist.getId());
            loginMapper.insert(login);
        }
    }
    public UserImpl findUserById(String userId) {
        LoginSearch search = new LoginSearch();
        search.setLoginName(userId);
        List<Login> listLogin = loginMapper.find(search);
        if (listLogin.size() > 0) {
            Login login = listLogin.get(0);
            Person person = personMapper.selectByPrimaryKey(login.getPersonId());

            UserImpl user = new UserImpl(login.getLoginName(), person.getFirstName(), person.getLastName());
            user.setBusinessEmail(login.getEmail());
            return user;
        }

        return null;
    }
    public List<UserImpl> findUsers() {
        List<UserImpl> listUser = new ArrayList<UserImpl>();

        LoginSearch search = new LoginSearch();
        List<Login> listLogin = loginMapper.find(search);
        listUser = covertLogin2UserImpl(listLogin);

        return listUser;
    }
    public List<UserImpl> findUsersById(String[] userIds) {
        List<UserImpl> listUser = new ArrayList<UserImpl>();

        List<Login> listLogin = loginMapper.findLoginByLoginNames(userIds);
        listUser = covertLogin2UserImpl(listLogin);

        return listUser;
    }
    public List<UserImpl> findUsersByGroup(String groupId) {
        List<UserImpl> listUser = new ArrayList<UserImpl>();

        GroupSearch search = new GroupSearch();
        search.setName(groupId);
        List<Group> listGrp = groupMapper.find(search);
        if (listGrp.size() > 0) {
            Group group = listGrp.get(0);
            List<Login> listLogin = loginMapper.findLoginByGroupId(group.getId());
            listUser = covertLogin2UserImpl(listLogin);
        }
        return listUser;
    }
    public void deleteUser(String userId) {
        Login login = findLogin(userId);
        if (login != null) {
            groupLoginMapper.deleteGroupLoginByLoginId(login.getId());
            loginMapper.deleteByPrimaryKey(login.getId());
            personMapper.deleteByPrimaryKey(login.getPersonId());
        }
    }

    public void saveGroup(GroupImpl group) {
        Group grp = new Group();
        grp.setName(group.getId());
        groupMapper.insert(grp);
    }
    public GroupImpl findGroupById(String groupId) {
        Group grp = findGroup(groupId);
        if (grp != null) {
            GroupImpl group = new GroupImpl();
            group.setId(grp.getName());
            group.setName(grp.getName());
            return group;
        }
        return null;
    }
    public List<GroupImpl> findGroups() {
        List<GroupImpl> listGroup = new ArrayList<GroupImpl>();

        GroupSearch search = new GroupSearch();
        List<Group> listGrp = groupMapper.find(search);
        listGroup = covertGroup2GroupImpl(listGrp);

        return listGroup;
    }
    public List<GroupImpl> findGroupsByUser(String userId) {
        List<GroupImpl> listGroup = new ArrayList<GroupImpl>();

        Login login = findLogin(userId);
        if (login != null) {
            List<Group> listGrp = groupMapper.findGroupByLoginId(login.getId());
            listGroup = covertGroup2GroupImpl(listGrp);
        }

        return listGroup;
    }
    public List<GroupImpl> findGroupsByUserAndGroupType(String userId, String groupType) {
        return findGroupsByUser(userId);
    }
    public void deleteGroup(String groupId) {
        Group grp = findGroup(groupId);
        if (grp != null) {
            groupLoginMapper.deleteGroupLoginByGroupId(grp.getId());
            groupMapper.deleteByPrimaryKey(grp.getId());
        }
    }

    public void saveMembership(String userId, String groupId, String role) {
        Login login = findLogin(userId);
        Group grp = findGroup(groupId);

        if (login != null && grp != null) {
            GroupLogin groupLogin = new GroupLogin();
            groupLogin.setLoginId(login.getId());
            groupLogin.setGroupId(grp.getId());
            groupLoginMapper.insert(groupLogin);
        }

    }
    public void deleteMembership(String userId, String groupId, String role) {
        Login login = findLogin(userId);
        Group grp = findGroup(groupId);

        if (login != null && grp != null) {
            LoginSearch search = new LoginSearch();
            search.setLoginId(login.getId());
            search.setGroupId(grp.getId());
            groupLoginMapper.deleteGroupLogin(search);
        }
    }

    private Login findLogin(String loginName) {
        LoginSearch search = new LoginSearch();
        search.setLoginName(loginName);
        List<Login> listLogin = loginMapper.find(search);
        if (listLogin.size() > 0) {
            return listLogin.get(0);
        }
        return null;
    }
    private Group findGroup(String groupName) {
        GroupSearch search = new GroupSearch();
        search.setName(groupName);
        List<Group> listGroup = groupMapper.find(search);
        if (listGroup.size() > 0) {
            return listGroup.get(0);
        }
        return null;
    }
    private List<UserImpl> covertLogin2UserImpl(List<Login> listLogin) {
        List<UserImpl> listUser = new ArrayList<UserImpl>();
        for (Login login : listLogin) {
            Person person = personMapper.selectByPrimaryKey(login.getPersonId());

            UserImpl user = new UserImpl(login.getLoginName(), person.getFirstName(), person.getLastName());
            user.setBusinessEmail(login.getEmail());
            listUser.add(user);
        }
        return listUser;
    }
    private List<GroupImpl> covertGroup2GroupImpl(List<Group> listGrp) {
        List<GroupImpl> listGroup = new ArrayList<GroupImpl>();
        for (Group grp : listGrp) {
            GroupImpl group = new GroupImpl();
            group.setId(grp.getName());
            group.setName(grp.getName());

            listGroup.add(group);
        }
        return listGroup;
    }

    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }
    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }
    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }
    public void setGroupLoginMapper(GroupLoginMapper groupLoginMapper) {
        this.groupLoginMapper = groupLoginMapper;
    }

}
