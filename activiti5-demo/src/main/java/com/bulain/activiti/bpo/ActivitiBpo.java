package com.bulain.activiti.bpo;

import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.bulain.activiti.pojo.ProcessDefinitionSearch;
import com.bulain.activiti.pojo.ProcessInstanceSearch;
import com.bulain.activiti.pojo.TaskSearch;
import com.bulain.common.page.Page;

public interface ActivitiBpo {
    List<ProcessDefinition> pageProcessDefinition(ProcessDefinitionSearch search, Page page);;
    List<ProcessInstance> pageProcessInstance(ProcessInstanceSearch search, Page page);;
    List<Task> pageTask(TaskSearch search, Page page);
}
