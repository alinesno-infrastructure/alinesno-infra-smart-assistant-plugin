package com.alinesno.infra.smart.assistant.plugin.demo;

import cn.hutool.core.util.IdUtil;
import com.alinesno.infra.smart.assistant.api.adapter.TaskContentDto;
import com.alinesno.infra.smart.assistant.im.dto.NoticeDto;
import com.alinesno.infra.smart.assistant.plugin.common.annotation.ChainStep;
import com.alinesno.infra.smart.assistant.role.PlatformExpert;
import com.alinesno.infra.smart.assistant.role.context.RoleChainContext;
import com.alinesno.infra.smart.assistant.role.utils.YamlUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 示例的Chain配置
 */
@Slf4j
@Component
public class DemoPluginChainExpert extends PlatformExpert {

    private static final String promptId = "0GSheQ31" ;
    private List<String> resultMap ;

    @LiteflowComponent(value = "DemoPlugin_a" , name="插件测试执行节点A")
    public class ACmp extends NodeComponent {

        @ChainStep
        @Override
        public void process() {
            resultMap = new ArrayList<>() ;

            System.out.println("ACmp executed!");

            // 获取上下文
            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            roleContext.setStartTime(System.currentTimeMillis());

            NoticeDto noticeDto = roleContext.getNoticeDto() ;

            // 通过上下文传入
            String businessId = generatorId() ;

            // 获取到参数
            Map<String , Object> params = this.getRequestData();

            log.debug("params = {}" , params);
            log.debug("businessId = {}" , businessId);
            log.debug("noticeDto = {}" , noticeDto);

            resultMap.add(businessId) ;
            resultMap.add(roleContext.getAssistantYamlContent()) ;

        }
    }

    @LiteflowComponent(value = "DemoPlugin_b" , name="插件测试执行节点B")
    public class BCmp extends NodeComponent {

        @ChainStep
        @Override
        public void process() {
            System.out.println("BCmp executed!");
            TaskContentDto taskContentDto =  brainRemoteService.chatContent("1750338340780298240") ;
            log.debug("TaskContentDto = {}" , taskContentDto);
        }

    }

    @LiteflowComponent(value = "DemoPlugin_c" , name="插件测试执行节点C")
    public class CCmp extends NodeComponent {

        @ChainStep
        @Override
        public void process() {
            System.out.println("CCmp executed!");
            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            String businessId = roleContext.getBusinessId() ; // 获取到业务Id

            log.debug("YamlUtils.mergedYamlList(resultMap) = \r\n{}" , YamlUtils.mergedYamlList(resultMap));

            // 将聚合生成的内容保存到内容数据库中
            saveToBusinessResult(businessId , YamlUtils.mergedYamlList(resultMap)) ;

        }

    }
}
