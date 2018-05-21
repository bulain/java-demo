package com.bulain.activiti.bpo;

import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import com.bulain.activiti.model.Order;
import com.bulain.activiti.service.OrderService;

public class OrderBpoImpl implements OrderBpo {
    private OrderService orderService;
    private TaskService taskService;

    public Order claim(String taskId, String userId) {
        taskService.claim(taskId, userId);

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String executionId = task.getExecutionId();
        Order order = orderService.getByWfId(executionId);

        if (order == null) {
            order = new Order();
            order.setWfId(executionId);
        }

        return order;
    }

    public void complete(Order order, String taskId, Map<String, Object> variables) {
        if (order.getId() == null) {
            orderService.insert(order);
        } else {
            orderService.update(order, false);
        }
        taskService.complete(taskId, variables);
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
