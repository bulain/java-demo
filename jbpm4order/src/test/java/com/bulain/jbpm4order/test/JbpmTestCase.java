package com.bulain.jbpm4order.test;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.test.ServiceTestCase;

public abstract class JbpmTestCase extends ServiceTestCase {
    @Autowired
    protected RepositoryService repositoryService;
    @Autowired
    protected ExecutionService executionService;
    @Autowired
    protected TaskService taskService;
    @Autowired
    protected HistoryService historyService;
    @Autowired
    protected IdentityService identityService;
    @Autowired
    protected ManagementService managementService;
}
