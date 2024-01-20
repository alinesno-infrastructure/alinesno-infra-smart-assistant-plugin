package com.alinesno.infra.smart.assistant.plugin.product.article;

import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.smart.assistant.role.PlatformExpert;
import com.alinesno.infra.smart.assistant.role.context.RoleChainContext;
import com.alinesno.infra.smart.assistant.role.utils.YAMLMapper;
import com.alinesno.infra.smart.assistant.role.utils.YamlUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 将内容存入CMS内容
 */
@Slf4j
@Component
public class ProductArticleChapterContentToCmsSpecialist extends PlatformExpert {

    private static final String promptId = "OyezG1Hk" ;
    private List<String> resultMap ;

    @LiteflowComponent(value = "PACC_TC_STEP_01", name="角色内容写入CMS系统")
    public class PACC_TC_STEP_01 extends NodeComponent {

        @SneakyThrows
        @Override
        public void process() {
            resultMap = new ArrayList<>();

            // 获取上下文
            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            roleContext.setStartTime(System.currentTimeMillis());

            List<ChapterContentBean> chapterContentBeans = YAMLMapper.listFromYAML(roleContext.getAssistantYamlContent() , ChapterContentBean.class) ;
            log.debug("chapterContentBeans = {}" , chapterContentBeans);

            // TODO 请求发送到CMS平台

            resultMap.add(YAMLMapper.toYAML(chapterContentBeans)) ;
        }
    }

    // 根据情况，进行并行处理
    @LiteflowComponent(value = "PACC_TC_STEP_02", name="解析解决内容服务")
    public class PACC_TC_STEP_02 extends NodeComponent {

        @SneakyThrows
        @Override
        public void process() {

            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            String businessId = roleContext.getBusinessId() ; // 获取到业务Id

            log.debug("YamlUtils.mergedYamlList(resultMap) = \r\n{}" , YAMLMapper.toYAML(resultMap));

            // 将聚合生成的内容保存到内容数据库中
            saveToBusinessResult(businessId , YAMLMapper.toYAML(resultMap)) ;
        }
    }

    @Data
    public static class ChapterContentBean {
        private String sub ; // 子章节内容
        private String chapter ; // 章节名称
        private String content ; // 保存内容
    }

}
