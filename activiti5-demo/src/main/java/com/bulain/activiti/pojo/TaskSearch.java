package com.bulain.activiti.pojo;

import java.util.Date;

import com.bulain.common.page.Search;

public class TaskSearch extends Search {
    private static final long serialVersionUID = -5197008445122447863L;

    private String taskName;
    private String taskDescription;
    private String taskAssignee;
    private String taskCandidateUser;
    private String taskCandidateGroup;
    private String taskDefinitionKey;
    private Date taskCreateTimeStart;
    private Date taskCreateTimeEnd;

    private String processInstanceBusinessKey;

    private String processDefinitionKey;
    private String processDefinitionName;

    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public String getTaskAssignee() {
        return taskAssignee;
    }
    public void setTaskAssignee(String taskAssignee) {
        this.taskAssignee = taskAssignee;
    }
    public String getTaskCandidateUser() {
        return taskCandidateUser;
    }
    public void setTaskCandidateUser(String taskCandidateUser) {
        this.taskCandidateUser = taskCandidateUser;
    }
    public String getTaskCandidateGroup() {
        return taskCandidateGroup;
    }
    public void setTaskCandidateGroup(String taskCandidateGroup) {
        this.taskCandidateGroup = taskCandidateGroup;
    }
    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }
    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }
    public Date getTaskCreateTimeStart() {
        return taskCreateTimeStart;
    }
    public void setTaskCreateTimeStart(Date taskCreateTimeStart) {
        this.taskCreateTimeStart = taskCreateTimeStart;
    }
    public Date getTaskCreateTimeEnd() {
        return taskCreateTimeEnd;
    }
    public void setTaskCreateTimeEnd(Date taskCreateTimeEnd) {
        this.taskCreateTimeEnd = taskCreateTimeEnd;
    }
    public String getProcessInstanceBusinessKey() {
        return processInstanceBusinessKey;
    }
    public void setProcessInstanceBusinessKey(String processInstanceBusinessKey) {
        this.processInstanceBusinessKey = processInstanceBusinessKey;
    }
    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }
    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }
    public String getProcessDefinitionName() {
        return processDefinitionName;
    }
    public void setProcessDefinitionName(String processDefinitionName) {
        this.processDefinitionName = processDefinitionName;
    }

}
