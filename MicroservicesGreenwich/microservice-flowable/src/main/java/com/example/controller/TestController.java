package com.example.controller;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;

    @GetMapping("test")
    public long test(){
        long count = repositoryService.createProcessDefinitionQuery().count();
        System.out.println("Number of process definitions : " + count);
        System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
        runtimeService.startProcessInstanceByKey("oneTaskProcess");
        System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());

        return count;
    }
}
