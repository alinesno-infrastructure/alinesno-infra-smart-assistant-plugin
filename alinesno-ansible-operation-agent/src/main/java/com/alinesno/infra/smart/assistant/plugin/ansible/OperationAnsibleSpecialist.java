package com.alinesno.infra.smart.assistant.plugin.ansible;

import com.alinesno.infra.smart.assistant.api.adapter.TaskContentDto;
import com.alinesno.infra.smart.assistant.role.PlatformExpert;
import com.alinesno.infra.smart.assistant.role.context.RoleChainContext;
import com.alinesno.infra.smart.assistant.role.utils.YamlUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自动化运维Ansible生成专家
 */
@Slf4j
@Component
public class OperationAnsibleSpecialist extends PlatformExpert {

    private static final String promptId = "Zo5ueZ34" ;
    private List<String> resultMap ;

    @LiteflowComponent(value = "OAS_STEP_01", name="服务接口生成专家")
    public class OAS_STEP_01 extends NodeComponent {

        @SneakyThrows
        @Override
        public void process() {
            resultMap = new ArrayList<>();

            // 获取上下文
            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            roleContext.setStartTime(System.currentTimeMillis());

            // 通过上下文传入
            String businessId = roleContext.getBusinessId() ;
            Map<String , Object> params = this.getRequestData();

            brainRemoteService.chatTask(params , businessId , promptId);

            // >>>>>>>>>>>>>>>>>>>>>>> 获取结果并解析 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
            int retryCount = 0 ;
            while (retryCount <= MAX_RETRY_COUNT) {

                Thread.sleep(DEFAULT_SLEEP_TIME);

                TaskContentDto content = brainRemoteService.chatContent(businessId);
                log.debug("promptId = {} , content = {}" , promptId , content);

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

    // 根据情况，进行并行处理
    @LiteflowComponent(value = "OAS_STEP_02", name="生成内容并发送到测试平台")
    public class OAS_STEP_02 extends NodeComponent {

        @SneakyThrows
        @Override
        public void process() {

            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            String businessId = roleContext.getBusinessId() ; // 获取到业务Id

            log.debug("YamlUtils.mergedYamlList(resultMap) = \r\n{}" , YamlUtils.mergedYamlList(resultMap));

            // 将聚合生成的内容保存到内容数据库中
            saveToBusinessResult(businessId , YamlUtils.mergedYamlList(resultMap)) ;

        }
    }

}
