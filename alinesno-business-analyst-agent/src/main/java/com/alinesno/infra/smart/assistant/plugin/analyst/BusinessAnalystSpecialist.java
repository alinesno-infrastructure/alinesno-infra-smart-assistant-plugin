package com.alinesno.infra.smart.assistant.plugin.analyst;

import com.alinesno.infra.smart.assistant.api.adapter.TaskContentDto;
import com.alinesno.infra.smart.assistant.plugin.common.annotation.ChainStep;
import com.alinesno.infra.smart.assistant.role.PlatformExpert;
import com.alinesno.infra.smart.assistant.role.context.RoleChainContext;
import com.alinesno.infra.smart.assistant.role.utils.YAMLMapper;
import com.alinesno.infra.smart.assistant.role.utils.YamlUtils;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class BusinessAnalystSpecialist extends PlatformExpert {

    private static final String STEP_01 = "mOO5Fq5s" ;
    private static final String STEP_02 = "V5yZ1l3H" ;
    private static final String STEP_03 = "RgPSt3oS" ;
    private static final String STEP_04 = "3hmyCqhJ" ;
    private static final String STEP_05 = "wbqZCMp8" ;

    // 内容容器
    private List<String> resultMap = new ArrayList<>();
    private FunctionBean functionBean ;

    @LiteflowComponent(value = "BA_STEP_01" , name="需求分析_需求文档分析")
    public class BA_STEP_01 extends NodeComponent {

        @ChainStep
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

                    log.debug("需求分析获取内容:{}" , yamlContent);

                    DocumentIntroduction documentIntroduction = YAMLMapper.fromYAML(yamlContent , DocumentIntroduction.class) ;
                    log.debug("yamlContent = {}" , yamlContent);

                    resultMap.add(YAMLMapper.toYAML(documentIntroduction)) ;

                    break ;
                }

                retryCount ++ ;
                log.debug("生效获取业务[{}]次数:{}" , businessId , retryCount);
            }

            roleContext.setBusinessId(businessId);
            roleContext.setUserContent(params.get("label1").toString());
        }
    }

    @LiteflowComponent(value = "BA_STEP_02" , name="需求分析_项目介绍分析")
    public class BA_STEP_02 extends NodeComponent {

        @ChainStep
        @Override
        public void process() throws Exception {
            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            String businessId = generatorId() ;

            Map<String , Object> params = this.getRequestData();
            brainRemoteService.chatTask(params , businessId , STEP_02);

            // >>>>>>>>>>>>>>>>>>>>>>> 获取结果并解析 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
            int retryCount = 0 ;
            while (retryCount <= MAX_RETRY_COUNT) {

                Thread.sleep(DEFAULT_SLEEP_TIME);

                TaskContentDto content = brainRemoteService.chatContent(businessId);
                log.debug("promptId = {} , content = {}" , STEP_02 , content);

                if(content.getTaskStatus() == 2){
                    String yamlContent = content.getCodeContent().get(0).getContent() ;
                    log.debug("yamlContent = {}" , yamlContent);

                    ProjectIntroduction projectIntroduction = YAMLMapper.fromYAML(yamlContent , ProjectIntroduction.class) ;
                    resultMap.add(YAMLMapper.toYAML(projectIntroduction)) ;

                    break ;
                }

                retryCount ++ ;
                log.debug("生效获取业务[{}]次数:{}" , businessId , retryCount);
            }

            roleContext.setBusinessId(businessId);
            roleContext.setUserContent(params.get("label1").toString());
        }
    }

    @LiteflowComponent(value = "BA_STEP_03" , name="需求分析_项目功能分析")
    public class BA_STEP_03 extends NodeComponent {

        @ChainStep
        @Override
        public void process() throws Exception {
            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            String businessId = generatorId() ;

            Map<String , Object> params = this.getRequestData();
            brainRemoteService.chatTask(params , businessId , STEP_03);

            // >>>>>>>>>>>>>>>>>>>>>>> 获取结果并解析 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
            int retryCount = 0 ;
            while (retryCount <= MAX_RETRY_COUNT) {

                Thread.sleep(DEFAULT_SLEEP_TIME);

                TaskContentDto content = brainRemoteService.chatContent(businessId);
                log.debug("promptId = {} , content = {}" , STEP_03 , content);

                if(content.getTaskStatus() == 2){
                    String yamlContent = content.getCodeContent().get(0).getContent() ;
                    log.debug("yamlContent = {}" , yamlContent);

                    functionBean = YAMLMapper.fromYAML(yamlContent , FunctionBean.class) ;
                    log.debug("获取需求分析项目功能:{}" , YAMLMapper.toYAML(functionBean));

                    resultMap.add(YAMLMapper.toYAML(functionBean)) ;

                    break ;
                }

                retryCount ++ ;
                log.debug("生效获取业务[{}]次数:{}" , businessId , retryCount);
            }

        }
    }

    @LiteflowComponent(value = "BA_STEP_04" , name="需求分析_项目功能细化分析")
    public class BA_STEP_04 extends NodeComponent {

        @ChainStep
        @Override
        public void process() throws Exception {

            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;

            List<String> businessIdList = new ArrayList<>() ;
            String businessId = roleContext.getBusinessId() ;

            for(FunctionBean.Function function: functionBean.getFunction()){
                List<FunctionBean.FunctionModel> chapters = function.getModels() ;

                for(FunctionBean.FunctionModel fun: chapters){
                    String bId = generatorId() ;

                    Map<String , Object> paramsLabel = this.getRequestData();
                    paramsLabel.put("label1" , fun.getName()+":" + fun.getDesc()) ;
                    paramsLabel.put("label2" , roleContext.getAssistantYamlContent()) ;

                    brainRemoteService.chatTask(paramsLabel , bId , STEP_04);
                    log.debug("params = {}" , paramsLabel);

                    if(fun.getSubFunctionModel() != null && !fun.getSubFunctionModel().isEmpty()){

                        for(FunctionBean.SubFunctionModel subFun : fun.getSubFunctionModel()){

                            String subBId = generatorId() ;
                            Map<String , Object> subParamsLabel = this.getRequestData();
                            subParamsLabel.put("label1" , subFun.getName()+":" + subFun.getDesc()) ;
                            subParamsLabel.put("label2" , roleContext.getAssistantYamlContent()) ;

                            brainRemoteService.chatTask(subParamsLabel , subBId , STEP_04);
                            log.debug("params = {}" , paramsLabel);

                            businessIdList.add(subBId) ;
                        }
                    }

                    businessIdList.add(bId) ;
                }

            }

            // >>>>>>>>>>>>>>>>>>>>>>> 获取结果并解析 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
            int retryCount = 0 ;
            while (retryCount <= MAX_RETRY_COUNT) {

                Thread.sleep(DEFAULT_SLEEP_TIME);
                boolean isFinish = true ;

                for(String bId : businessIdList){
                    TaskContentDto content = brainRemoteService.chatContent(bId);

                    log.debug("获取需求业务:{}, 详情:{}" , bId , content);

                    if(content.getTaskStatus() != 2){
                        isFinish = false ;
                        break ;
                    }
                }

                if(isFinish){
                    for(String bId : businessIdList) {
                        TaskContentDto content = brainRemoteService.chatContent(bId);
                        String yamlContent = content.getCodeContent().get(0).getContent();


                        resultMap.add(yamlContent) ;
                    }
                    break ;
                }

                retryCount ++ ;
                log.debug("生效获取业务[{}]次数:{}" , businessId , retryCount);
            }
        }
    }

    @LiteflowComponent(value = "BA_STEP_05" , name="需求分析_项目非功能性")
    public class BA_STEP_05 extends NodeComponent {

        @ChainStep
        @Override
        public void process() throws Exception {
            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            String businessId = generatorId() ;

            Map<String , Object> params = this.getRequestData();
            brainRemoteService.chatTask(params , businessId , STEP_05);

            // >>>>>>>>>>>>>>>>>>>>>>> 获取结果并解析 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
            int retryCount = 0 ;
            while (retryCount <= MAX_RETRY_COUNT) {

                Thread.sleep(DEFAULT_SLEEP_TIME);

                TaskContentDto content = brainRemoteService.chatContent(businessId);
                log.debug("promptId = {} , content = {}" , STEP_05 , content);

                if(content.getTaskStatus() == 2){
                    // 解析获取非功能性需求
                    String yamlContent = content.getCodeContent().get(0).getContent() ;
                    log.debug("yamlContent = {}" , yamlContent);

                    resultMap.add(yamlContent) ;

                    break ;
                }

                retryCount ++ ;
                log.debug("生效获取业务[{}]次数:{}" , businessId , retryCount);
            }

            roleContext.setBusinessId(businessId);
            roleContext.setUserContent(params.get("label1").toString());
        }
    }

    @LiteflowComponent(value = "BA_STEP_AGG" , name="需求分析_聚合成文档")
    public class BA_STEP_AGG extends NodeComponent {

        @ChainStep
        @Override
        public void process() throws Exception {

            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            String businessId = roleContext.getBusinessId() ; // 获取到业务Id

            // 将聚合生成的内容保存到内容数据库中
            saveToBusinessResult(businessId , YamlUtils.mergedYamlList(resultMap)) ;
        }
    }

    // 表示需求文档的介绍部分。
    @Data
    public static class DocumentIntroduction {
        private String purpose; // 文档的目的。
        private String scope; // 文档的范围，包括文档介绍、项目概述和需求描述。
        private String audience;  // 文档的目标读者。
        private String terminology; // 包含在文档中的术语和缩写的解释。
        private String references; // 文档中相关材料的参考。
    }

    // 表示需求文档项目介绍部分
    @Data
    public static class ProjectIntroduction {
        private String description; // 产品的名称、任务提出者、开发者、用户群
        private String background; // 产品的背景，在什么样的背景下产生该产品
        private String goal; // 产品的目标与愿景，产品要能满足什么样的需求，要达到什么样一个效果
        private String users; // 产品的操作用户
    }

    // 表示需求文档功能列表部分
    @Data
    public static class FunctionBean {
        private List<Function> function;

        @Data
        public static class Function {
            private String title;
            private String summary;
            private List<FunctionModel> models;
        }

        @Data
        public static class FunctionModel{
            private String name ;
            private String desc ;
            private List<SubFunctionModel> subFunctionModel;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class SubFunctionModel {
            private String name;
            private String desc ;
        }
    }

}



















