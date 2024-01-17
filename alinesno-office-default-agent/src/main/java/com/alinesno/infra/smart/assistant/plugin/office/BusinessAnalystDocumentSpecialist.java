package com.alinesno.infra.smart.assistant.plugin.office;

import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.smart.assistant.api.adapter.TaskContentDto;
import com.alinesno.infra.smart.assistant.role.PlatformExpert;
import com.alinesno.infra.smart.assistant.role.context.RoleChainContext;
import com.alinesno.infra.smart.assistant.role.utils.ParserUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 需求分析专家
 */
@Slf4j
@Component
public class BusinessAnalystDocumentSpecialist extends PlatformExpert {

    private static final String STEP_01 = "mOO5Fq5s" ;

    // 内容容器
    private List<String> resultMap ;

    @LiteflowComponent(value = "BAD_STEP_01" , name="需求分析_需求文档分析")
    public class BAD_STEP_01 extends NodeComponent {

        @Override
        public void process() throws Exception {
            resultMap = new ArrayList<>() ;
            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            String businessId = generatorId() ;

            Map<String , Object> params = this.getRequestData();
            brainRemoteService.chatTask(params , businessId , STEP_01);

            // >>>>>>>>>>>>>>>>>>>>>>> 获取结果并解析 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
            int retryCount = 0 ;
            while (retryCount <= MAX_RETRY_COUNT) {

                Thread.sleep(DEFAULT_SLEEP_TIME);

                TaskContentDto content = brainRemoteService.chatContent(businessId);
                log.debug("promptId = {} , content = {}" , STEP_01 , content);

                if(content.getTaskStatus() == 2){
                    String yamlContent = content.getCodeContent().get(0).getContent() ;
                    log.debug("yamlContent = {}" , yamlContent);

                    resultMap.add(ParserUtils.convertYamlToJson(yamlContent)) ;

                    break ;
                }

                retryCount ++ ;
                log.debug("生效获取业务[{}]次数:{}" , businessId , retryCount);
            }

            roleContext.setBusinessId(businessId);
            roleContext.setUserContent(params.get("label1").toString());
        }
    }

    @LiteflowComponent(value = "BAD_STEP_02" , name="需求分析_聚合成文档")
    public class BAD_STEP_02 extends NodeComponent {

        @Override
        public void process() throws Exception {

            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            String businessId = roleContext.getBusinessId() ; // 获取到业务Id

            // 将聚合生成的内容保存到内容数据库中
            saveToBusinessResult(businessId , JSONObject.toJSONString(resultMap)) ;
        }
    }

}



















