package com.alinesno.infra.smart.assistant.plugin.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 运行任务
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface ChainStep {

    String title() default "" ;
    String taskType() default "" ; // 任务类型(步骤step|内容content)
    String desc() default "" ;
    String status() default "" ; // 任务运行状态
    String businessId() default "" ; // 所属业务ID
    String channel() default "" ; // 所属频道ID
}
