package com.alinesno.infra.smart.assistant.plugin.demo;

import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.smart.assistant.api.adapter.TaskContentDto;
import com.alinesno.infra.smart.assistant.role.PlatformExpert;
import com.alinesno.infra.smart.assistant.role.context.RoleChainContext;
import com.alinesno.infra.smart.assistant.role.utils.YamlUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务工程生成专家
 */
@Slf4j
@Component
public class ServiceProjectGenSpecialist extends PlatformExpert {

    private static final String SPG_STEP_01 = "gDT9coiQ" ;
    private List<String> resultMap ;


    @LiteflowComponent(value = "SPG_STEP_01" , name="需求分析_需求文档分析")
    public class SPG_STEP_01 extends NodeComponent {

        @Override
        public void process() throws Exception {

            resultMap = new ArrayList<>() ;

            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class);
            String businessId = roleContext.getBusinessId();

            Map<String , Object> params = this.getRequestData();
            brainRemoteService.chatTask(params , businessId , SPG_STEP_01);

            // >>>>>>>>>>>>>>>>>>>>>>> 获取结果并解析 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
            int retryCount = 0 ;
            while (retryCount <= MAX_RETRY_COUNT) {

                Thread.sleep(DEFAULT_SLEEP_TIME);

                TaskContentDto content = brainRemoteService.chatContent(businessId);
                log.debug("promptId = {} , content = {}" , SPG_STEP_01 , content);

                if(content.getTaskStatus() == 2){
                    String yamlContent = content.getCodeContent().get(0).getContent() ;
                    log.debug("yamlContent = {}" , yamlContent);

                    resultMap.add(yamlContent) ;
                    break ;
                }

                retryCount ++ ;
                log.debug("生效获取业务[{}]次数:{}" , businessId , retryCount);
            }

        }
    }

    @LiteflowComponent(value = "SPG_STEP_AGG" , name="代码生成内容_聚合成文档")
    public class SPG_STEP_AGG extends NodeComponent {

        @Override
        public void process() throws Exception {

            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            String businessId = roleContext.getBusinessId() ; // 获取到业务Id

            // 将聚合生成的内容保存到内容数据库中
            saveToBusinessResult(businessId , YamlUtils.mergedYamlList(resultMap)) ;
        }
    }

}
