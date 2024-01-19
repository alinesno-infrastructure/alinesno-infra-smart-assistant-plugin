package com.alinesno.infra.smart.assistant.plugin.database;

import com.alinesno.infra.smart.assistant.api.prompt.PromptMessage;
import com.alinesno.infra.smart.assistant.role.PlatformExpert;
import com.alinesno.infra.smart.assistant.role.handle.BrainRequestTask;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 项目生成专家
 */
public class ProjectGenerationSpecialist extends PlatformExpert {

    @Override
    public void performSpecializedTask(List<PromptMessage> prompts) {

        // 创建三个HTTP请求任务
        Callable<String> httpRequestTask1 = new BrainRequestTask(getPlatformUrl() , "获取到表名称");
        Callable<String> httpRequestTask2 = new BrainRequestTask(getPlatformUrl() , "获取到表结构");

        // 提交任务并获取Future对象
        Future<String> future1 = getExecutor().submit(httpRequestTask1);
        Future<String> future2 = getExecutor().submit(httpRequestTask2);

        // 等待所有HTTP请求执行完成
        try {
            String response1 = future1.get();
            String response2 = future2.get();

            // 整合结果
            String combinedResponse = response1 + response2 ;

            System.out.println("Combined Response: " + combinedResponse);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}
