package com.alinesno.infra.smart.assistant.plugin.common.annotation;

import lombok.Data;
import lombok.ToString;

/**
 * 步骤执行信息
 */
@Data
@ToString
public class ChainStepBean {

    private String taskStatus;  // 任务状态
    private String businessId;  // 业务id
    private String channel ;  // 频道id
    private String taskType;  // 任务类型
    private String taskName;  // 任务名称
    private String assistantContent;  // 生成内容
    private String usageTime;  // 使用时间

}