package com.alinesno.infra.smart.assistant.plugin.common.aspect;

import com.alinesno.infra.smart.assistant.plugin.common.annotation.ChainStep;
import com.alinesno.infra.smart.assistant.plugin.common.annotation.TaskStatusEnums;
import com.alinesno.infra.smart.assistant.role.common.TableItem;
import com.alinesno.infra.smart.assistant.role.context.RoleChainContext;
import com.alinesno.infra.smart.assistant.role.utils.RedisStreamPushUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 解析并获取到运行的事件相关信息
 */
@Slf4j
@Aspect
@Component
public class FlowChainAspect {

    @Autowired
    private RedisStreamPushUtils redisStreamPushUtils ;

    private final static ObjectMapper mapper = new ObjectMapper();

    /**
     * 拦截异常请求的方法
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.alinesno.infra.smart.assistant.plugin.common.annotation.ChainStep)")
    public Object aroundExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        TableItem tableItem = new TableItem();
        String taskStatus;  // 任务状态
        String businessId;  // 业务id
        String channel ;  // 频道id
        String taskType;  // 任务类型
        String taskName;  // 任务名称
        String assistantContent;  // 生成内容
        String usageTime;  // 使用时间

        String methodName = joinPoint.getSignature().toShortString();

        log.debug("--> 执行方法前: " + methodName);

        Object targetObject = joinPoint.getTarget(); // 获取目标对象
        Class<?> targetClass = targetObject.getClass();

        Method method = getMethod(joinPoint);

        ChainStep chainStep = method.getAnnotation(ChainStep.class);

        if (chainStep != null) {
            log.debug("--> 检测到 ChainStep 注解: {}", chainStep);
            LiteflowComponent liteflowComponent = joinPoint.getTarget().getClass().getAnnotation(LiteflowComponent.class) ;
            log.debug("--> 检测到 LiteflowComponent 注解: {}", liteflowComponent.name());

            // 获取流程上下文
            Method getContextBeanMethod = targetClass.getMethod("getContextBean", Class.class);
            RoleChainContext roleContext = (RoleChainContext) getContextBeanMethod.invoke(targetObject, RoleChainContext.class);
            log.debug("--> 获取到 RoleChainContext: {}", roleContext);

            taskStatus = TaskStatusEnums.PROCESSING.getValue() ;
            taskName = liteflowComponent.value() + ":" + liteflowComponent.name() ;
            businessId = roleContext.getBusinessId() ;
            channel = roleContext.getChannel() ;
            assistantContent = roleContext.getAssistantYamlContent() ;
            usageTime = (roleContext.getEndTime() - roleContext.getStartTime())/1000/1000 +"毫秒" ;
            taskType = "step" ;

            tableItem = new TableItem(taskStatus, businessId, taskType, taskName, assistantContent, usageTime) ;
            tableItem.setChannel(channel);

            redisStreamPushUtils.pushTask(mapper.writeValueAsString(tableItem));
        }

        try {
            // 执行原始方法
            Object result = joinPoint.proceed();

            log.debug("--> 执行方法结果: {}" , result);

            taskStatus = TaskStatusEnums.COMPLETED.getValue() ;
            if (chainStep != null) {
                tableItem.setTaskStatus(taskStatus);
                redisStreamPushUtils.pushTask(mapper.writeValueAsString(tableItem));
            }

            return result;
        } catch (Exception e) {
            log.error("--> 执行方法时发生异常: {}" , methodName);

            taskStatus = TaskStatusEnums.HANDLE_EXCEPTION.getValue() ;
            if (chainStep != null) {
                tableItem.setTaskStatus(taskStatus);
                redisStreamPushUtils.pushTask(mapper.writeValueAsString(tableItem));
            }

            throw e; // 继续向上抛出异常
        }

    }

    /**
     * 获取到方法信息
     * @param joinPoint
     * @return
     */
    private Method getMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }

}
