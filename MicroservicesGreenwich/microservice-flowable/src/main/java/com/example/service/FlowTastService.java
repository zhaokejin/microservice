package com.example.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface FlowTastService {

    /**
     * 启动流程
     * @param userId                用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
     * @param processDefinitionKey  流程定义KEY
     * @param businessKey           业务表编号
     * @param variables             流程变量
     * @return
     */
    JSONObject startProcessInstanceByKey(String userId,String processDefinitionKey, String businessKey, Map<String, Object> variables);
}
