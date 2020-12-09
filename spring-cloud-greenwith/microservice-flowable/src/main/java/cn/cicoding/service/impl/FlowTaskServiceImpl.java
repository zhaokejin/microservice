package cn.cicoding.service.impl;

import com.alibaba.fastjson.JSONObject;
import cn.cicoding.service.FlowTastService;
import com.google.common.collect.Maps;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FlowTaskServiceImpl implements FlowTastService {

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    /**
     *
     * @param userId                用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
     * @param processDefinitionKey  流程定义KEY
     * @param businessId            业务表编号
     * @param variables             流程变量
     * @return
     */
    @Override
    public JSONObject startProcessInstanceByKey(String userId,String processDefinitionKey, String businessId, Map<String, Object> variables) {


        // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(userId);

        // 设置流程变量
        if (variables == null){
            variables = Maps.newHashMap();
        }

        // 设置流程标题
//		if (StringUtils.isNotBlank(title)){
//			vars.put("title", title);
//		}

        // 启动流程
        ProcessInstance procIns = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessId, variables);
        String procInsId = procIns.getId();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("procInsId",procInsId);
        return jsonObject;
    }
}
