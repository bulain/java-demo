package com.bulain.activiti.bpo;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;

import com.bulain.activiti.pojo.ProcessDefinitionSearch;
import com.bulain.activiti.pojo.ProcessInstanceSearch;
import com.bulain.activiti.pojo.TaskSearch;
import com.bulain.common.page.Page;

public class ActivitiBpoImpl implements ActivitiBpo {
    private RepositoryService repositoryService;
    private RuntimeService runtimeService;
    private TaskService taskService;

    public List<ProcessDefinition> pageProcessDefinition(ProcessDefinitionSearch search, Page page) {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        if (StringUtils.isNotBlank(search.getProcessDefinitionCategory())) {
            query.processDefinitionCategory(search.getProcessDefinitionCategory());
        }
        if (StringUtils.isNotBlank(search.getProcessDefinitionName())) {
            query.processDefinitionName(search.getProcessDefinitionName());
        }
        if (StringUtils.isNotBlank(search.getProcessDefinitionKey())) {
            query.processDefinitionKey(search.getProcessDefinitionKey());
        }
        if (StringUtils.isNotBlank(search.getProcessDefinitionResourceName())) {
            query.processDefinitionResourceName(search.getProcessDefinitionResourceName());
        }
        if ("active".equals(search.getStatus())) {
            query.active();
        } else if ("suspended".equals(search.getStatus())) {
            query.suspended();
        }

        long cnt = query.count();
        page.setCount(cnt);

        String orderBy = search.getOrderBy();
        if ("processDefinitionCategory".equals(orderBy)) {
            query.orderByProcessDefinitionCategory();
        } else if ("processDefinitionKey".equals(orderBy)) {
            query.orderByProcessDefinitionKey();
        } else if ("processDefinitionName".equals(orderBy)) {
            query.orderByProcessDefinitionName();
        } else {
            query.orderByProcessDefinitionId();
        }
        query.latestVersion();

        String sequance = search.getSequance();
        if ("desc".equals(sequance)) {
            query.desc();
        } else {
            query.asc();
        }

        List<ProcessDefinition> listPage = query.listPage((int) page.getLow(), (int) page.getHigh());

        return listPage;
    }

    public List<ProcessInstance> pageProcessInstance(ProcessInstanceSearch search, Page page) {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();

        if (StringUtils.isNotBlank(search.getProcessInstanceBusinessKey())) {
            query.processInstanceBusinessKey(search.getProcessInstanceBusinessKey());
        }
        if (StringUtils.isNotBlank(search.getProcessDefinitionKey())) {
            query.processDefinitionKey(search.getProcessDefinitionKey());
        }
        if ("active".equals(search.getStatus())) {
            query.active();
        } else if ("suspended".equals(search.getStatus())) {
            query.suspended();
        }

        long cnt = query.count();
        page.setCount(cnt);

        String orderBy = search.getOrderBy();
        if ("processDefinitionKey".equals(orderBy)) {
            query.orderByProcessDefinitionKey();
        } else {
            query.orderByProcessInstanceId();
        }

        String sequance = search.getSequance();
        if ("desc".equals(sequance)) {
            query.desc();
        } else {
            query.asc();
        }

        List<ProcessInstance> listPage = query.listPage((int) page.getLow(), (int) page.getHigh());

        return listPage;
    }

    public List<Task> pageTask(TaskSearch search, Page page) {
        TaskQuery query = taskService.createTaskQuery();

        if (StringUtils.isNotBlank(search.getTaskName())) {
            query.taskNameLike(search.getTaskName());
        }
        if (StringUtils.isNotBlank(search.getTaskDescription())) {
            query.taskDescriptionLike(search.getTaskDescription());
        }
        if (StringUtils.isNotBlank(search.getTaskAssignee())) {
            query.taskAssignee(search.getTaskAssignee());
        }
        if (StringUtils.isNotBlank(search.getTaskCandidateUser())) {
            query.taskCandidateUser(search.getTaskCandidateUser());
        }
        if (StringUtils.isNotBlank(search.getTaskCandidateGroup())) {
            query.taskCandidateGroup(search.getTaskCandidateGroup());
        }
        if (StringUtils.isNotBlank(search.getTaskDefinitionKey())) {
            query.taskDefinitionKeyLike(search.getTaskDefinitionKey());
        }
        if (search.getTaskCreateTimeStart() != null) {
            query.taskCreatedAfter(search.getTaskCreateTimeStart());
        }
        if (search.getTaskCreateTimeEnd() != null) {
            query.taskCreatedBefore(search.getTaskCreateTimeEnd());
        }
        if (StringUtils.isNotBlank(search.getProcessInstanceBusinessKey())) {
            query.processInstanceBusinessKey(search.getProcessInstanceBusinessKey());
        }
        if (StringUtils.isNotBlank(search.getProcessDefinitionKey())) {
            query.processDefinitionKey(search.getProcessDefinitionKey());
        }
        if (StringUtils.isNotBlank(search.getProcessDefinitionName())) {
            query.processDefinitionName(search.getProcessDefinitionName());
        }

        long cnt = query.count();
        page.setCount(cnt);

        String orderBy = search.getOrderBy();
        if ("taskName".equals(orderBy)) {
            query.orderByTaskName();
        } else if ("taskDescription".equals(orderBy)) {
            query.orderByTaskDescription();
        } else if ("taskAssignee".equals(orderBy)) {
            query.orderByTaskAssignee();
        } else if ("taskCreateTime".equals(orderBy)) {
            query.orderByTaskCreateTime();
        } else if ("processInstanceId".equals(orderBy)) {
            query.orderByProcessInstanceId();
        } else if ("executionId".equals(orderBy)) {
            query.orderByExecutionId();
        } else {
            query.orderByTaskId();
        }

        String sequance = search.getSequance();
        if ("desc".equals(sequance)) {
            query.desc();
        } else {
            query.asc();
        }

        List<Task> listPage = query.listPage((int) page.getLow(), (int) page.getHigh());

        return listPage;
    }

    public void setRepositoryService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

}
