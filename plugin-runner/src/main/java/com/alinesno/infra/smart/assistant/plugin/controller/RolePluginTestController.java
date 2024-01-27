package com.alinesno.infra.smart.assistant.plugin.controller;

import cn.hutool.core.util.IdUtil;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.smart.assistant.entity.MessageQueueEntity;
import com.alinesno.infra.smart.assistant.enums.MessageStatus;
import com.alinesno.infra.smart.assistant.role.context.RoleChainContext;
import com.alinesno.infra.smart.assistant.service.IMessageQueueService;
import com.yomahub.liteflow.builder.el.LiteFlowChainELBuilder;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/api/assistant/role/plugin")
@RestController
public class RolePluginTestController {

    @Resource
    private FlowExecutor flowExecutor;

    /**
     * 测试运行插件
     * @return
     */
    @GetMapping("/testPlugin")
    public AjaxResult testPlugin(){

        String bId = IdUtil.getSnowflakeNextIdStr() ;

        MessageQueueEntity messageQueue = new MessageQueueEntity() ;

        messageQueue.setBusinessId(bId);  ;
        messageQueue.setContent("学生管理系统");
        messageQueue.setStatus(MessageStatus.SENT.getValue());

//        LiteFlowChainELBuilder.createChain().setChainId("DemoPluginChain").setEL(
//                "THEN(DemoPlugin_a,DemoPlugin_b,DemoPlugin_c)"
//        ).build();

        LiteFlowChainELBuilder.createChain().setChainId("DemoPluginChain").setEL(
                "THEN(BA_STEP_01,BA_STEP_02,BA_STEP_03,BA_STEP_04,BA_STEP_05,BA_STEP_AGG)"
        ).build();

        flowExecutor.reloadRule();

        RoleChainContext roleContext = new RoleChainContext() ;
        roleContext.setBusinessId(bId) ;
        Map<String , Object> params = new HashMap<>() ;

        params.put("label1" , "学生管理系统") ;
        params.put("businessId" , IdUtil.getSnowflakeNextIdStr()) ;
        roleContext.setParams(params);

        LiteflowResponse response = flowExecutor.execute2Resp("DemoPluginChain" , params , roleContext);
        log.debug("response = {}" , response);

        return AjaxResult.success() ;
    }

}

