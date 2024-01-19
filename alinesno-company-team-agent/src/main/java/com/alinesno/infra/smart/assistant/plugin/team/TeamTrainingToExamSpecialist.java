package com.alinesno.infra.smart.assistant.plugin.team;

import com.alinesno.infra.smart.assistant.api.adapter.TaskContentDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 推送题目到考试系统
 */
@Slf4j
@Component
public class TeamTrainingToExamSpecialist extends PlatformExpert {

    private static final String promptId = "5l1jPKV8" ;
    private static final String STEP_01 = "team_train" ;

    // 内容容器
    private String resultMap ;

    @LiteflowComponent(value = "TT_TE_01", name="生成试卷相关内容")
    public class TT_TE_01 extends NodeComponent {

        @SneakyThrows
        @Override
        public void process() {

            // 获取上下文
            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            roleContext.setStartTime(System.currentTimeMillis());

            // 通过上下文传入
            String businessId = roleContext.getBusinessId() ;

            // 获取到参数
            Map<String , Object> params = this.getRequestData();
            params.put("label1" , roleContext.getAssistantYamlContent()) ;

            brainRemoteService.chatTask(params , businessId , promptId);

            log.debug("params = {}" , params);

            // >>>>>>>>>>>>>>>>>>>>>>> 获取结果并解析 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
            int retryCount = 0 ;
            while (retryCount <= MAX_RETRY_COUNT) {

                Thread.sleep(DEFAULT_SLEEP_TIME);

                TaskContentDto content = brainRemoteService.chatContent(businessId);
                log.debug("promptId = {} , content = {}" , STEP_01 , content);

                if(content.getTaskStatus() == 2){
                    String yamlContent = content.getCodeContent().get(0).getContent() ;
                    log.debug("yamlContent = {}" , yamlContent);

                    resultMap = yamlContent ;
                    break ;
                }

                retryCount ++ ;
                log.debug("生效获取业务[{}]次数:{}" , businessId , retryCount);
            }

            roleContext.setUserContent(params.get("label1").toString());
        }
    }

    // 根据情况，进行并行处理
    @LiteflowComponent(value = "TT_TE_02", name="解析试题内容并发送到试题库")
    public class TT_TE_02 extends NodeComponent {

        @SneakyThrows
        @Override
        public void process() {

            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            String businessId = roleContext.getBusinessId() ; // 获取到业务Id

            String assistantYamlContent = roleContext.getAssistantYamlContent() ;

            List<QuestionBean> questionBeans = YAMLMapper.listFromYAML(assistantYamlContent , QuestionBean.class) ;
            ExamPagerBean pagerBean = YAMLMapper.fromYAML(resultMap , ExamPagerBean.class) ;

            log.debug("YamlUtils.mergedYamlList(resultMap) = \r\n{}" , resultMap);

            // 将聚合生成的内容保存到内容数据库中
            saveToBusinessResult(businessId , resultMap) ;
        }
    }

    // 试卷信息
    @Data
    public static class ExamPagerBean {
        private String paperName; // 试卷名称
        private String pagerDesc ; // 试卷描述
    }

    // 问题记录实体
    @Data
    public static class QuestionBean {
        private String title;
        private String desc;
        private String type;
        private int score;
        private List<String> answers;
        private String rightAnswer;
    }

}
