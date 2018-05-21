package com.bulain.activiti.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.struts2.dispatcher.DefaultActionSupport;

public class ActivitiAction extends DefaultActionSupport {
    private static final long serialVersionUID = 2150996113887310905L;

    private List<ProcessDefinition> listProcessDefinition;
    private List<ProcessInstance> listProcessInstance;
    private List<Task> listPersonTask;
    private List<Task> listGroupTask;
    private List<HistoricProcessInstance> listHistoryProcessInstance;
    private List<HistoricTaskInstance> listHistoryTask;
    private List<HistoricActivityInstance> listHistoryActivityInstance;

    private String processDefinitionId;
    private String executionId;
    private String deploymentId;
    private String taskId;
    private String render;

    private transient RepositoryService repositoryService;
    private transient RuntimeService runtimeService;
    private transient TaskService taskService;
    private transient HistoryService historyService;
    private transient FormService formService;

    public String list() {
        listProcessDefinition = repositoryService.createProcessDefinitionQuery().list();
        listProcessInstance = runtimeService.createProcessInstanceQuery().list();
        listPersonTask = taskService.createTaskQuery().taskAssignee("bulain").list();
        listGroupTask = taskService.createTaskQuery().taskCandidateUser("bulain").list();
        listHistoryProcessInstance = historyService.createHistoricProcessInstanceQuery().list();

        return SUCCESS;
    }

    public String deploy() {
        repositoryService.createDeployment().addClasspathResource("diagrams/order.bpmn20.xml").deploy();
        return SUCCESS;
    }

    public String destroy() {
        repositoryService.deleteDeployment(deploymentId, true);
        return SUCCESS;
    }

    public String start() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("owner", "bulain");
        runtimeService.startProcessInstanceById(processDefinitionId, variables);

        return SUCCESS;
    }

    public String active() {
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        render = taskFormData.getFormKey();
        return SUCCESS;
    }

    public String view() {
        listHistoryTask = historyService.createHistoricTaskInstanceQuery().executionId(executionId).list();
        listHistoryActivityInstance = historyService.createHistoricActivityInstanceQuery().executionId(executionId)
                .list();

        return SUCCESS;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }
    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }
    public String getExecutionId() {
        return executionId;
    }
    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }
    public String getDeploymentId() {
        return deploymentId;
    }
    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }
    public List<ProcessDefinition> getListProcessDefinition() {
        return listProcessDefinition;
    }
    public void setListProcessDefinition(List<ProcessDefinition> listProcessDefinition) {
        this.listProcessDefinition = listProcessDefinition;
    }
    public List<ProcessInstance> getListProcessInstance() {
        return listProcessInstance;
    }
    public void setListProcessInstance(List<ProcessInstance> listProcessInstance) {
        this.listProcessInstance = listProcessInstance;
    }
    public List<Task> getListPersonTask() {
        return listPersonTask;
    }
    public void setListPersonTask(List<Task> listPersonTask) {
        this.listPersonTask = listPersonTask;
    }
    public List<Task> getListGroupTask() {
        return listGroupTask;
    }
    public void setListGroupTask(List<Task> listGroupTask) {
        this.listGroupTask = listGroupTask;
    }
    public void setRepositoryService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }
    public List<HistoricTaskInstance> getListHistoryTask() {
        return listHistoryTask;
    }
    public void setListHistoryTask(List<HistoricTaskInstance> listHistoryTask) {
        this.listHistoryTask = listHistoryTask;
    }
    public List<HistoricActivityInstance> getListHistoryActivityInstance() {
        return listHistoryActivityInstance;
    }
    public void setListHistoryActivityInstance(List<HistoricActivityInstance> listHistoryActivityInstance) {
        this.listHistoryActivityInstance = listHistoryActivityInstance;
    }
    public List<HistoricProcessInstance> getListHistoryProcessInstance() {
        return listHistoryProcessInstance;
    }
    public void setListHistoryProcessInstance(List<HistoricProcessInstance> listHistoryProcessInstance) {
        this.listHistoryProcessInstance = listHistoryProcessInstance;
    }
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }
    public void setFormService(FormService formService) {
        this.formService = formService;
    }
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getRender() {
        return render;
    }
    public void setRender(String render) {
        this.render = render;
    }
}
