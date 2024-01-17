package com.alinesno.infra.smart.assistant.plugin.office;

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
 * API接口生成Java代码专家
 */
@Slf4j
@Component
public class TestApiSpringBootSpecialist extends PlatformExpert {

    private static final String promptId = "RUABZdu2" ;
    private List<String> resultMap ;

    @LiteflowComponent(value = "TASB_STEP_01", name="生成测试并返回")
    public class TASB_STEP_01 extends NodeComponent {

        @SneakyThrows
        @Override
        public void process() {
            resultMap = new ArrayList<>() ;

            // 获取上下文
            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            roleContext.setStartTime(System.currentTimeMillis());

            // 通过上下文传入
            String businessId = roleContext.getBusinessId() ;

            List<String> bIdList = new ArrayList<>() ;

            // 获取到解析内容
            RestControllerBean restControllerBean = getRestControllerBean(roleContext.getAssistantYamlContent()) ;

            for(RestControllerBean.ControllerApiBean controllerApiBean : restControllerBean.getControllers()){
                List<RestControllerBean.ApiBean> apis = controllerApiBean.getApis() ;

                for(RestControllerBean.ApiBean api : apis){
                    String bId = generatorId() ;

                    List<RestControllerBean.ApiBean> apiBeans = new ArrayList<>() ;
                    apiBeans.add(api) ;

                    controllerApiBean.setApis(apiBeans);

                    Map<String , Object> paramsLabel = this.getRequestData();
                    paramsLabel.put("label1" , YAMLMapper.toYAML(controllerApiBean)) ;

                    brainRemoteService.chatTask(paramsLabel , bId , promptId);
                    log.debug("params = {}" , paramsLabel);

                    bIdList.add(bId) ;
                }

            }

            // >>>>>>>>>>>>>>>>>>>>>>> 获取结果并解析 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
            int retryCount = 0 ;
            while (retryCount <= MAX_RETRY_COUNT) {

                Thread.sleep(DEFAULT_SLEEP_TIME);
                boolean isFinish = true ;

                for(String bId : bIdList){
                    TaskContentDto content = brainRemoteService.chatContent(bId);
                    if(content.getTaskStatus() != 2){
                        isFinish = false ;
                        break ;
                    }
                }

                if(isFinish){
                    for(String bId : bIdList) {
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

    // 根据情况，进行并行处理
    @LiteflowComponent(value = "TASB_STEP_02", name="生成内容并发送到测试平台")
    public class TASB_STEP_02 extends NodeComponent {

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

    /**
     * 生成结果用例
     * @param assistantYamlContent
     * @return
     */
    @SneakyThrows
    private RestControllerBean getRestControllerBean(String assistantYamlContent) {
        return YAMLMapper.fromYAML(assistantYamlContent , RestControllerBean.class);
    }

    /**
     * 接口控制层类实现
     */
    @Data
    public static class RestControllerBean {

        private List<ControllerApiBean> controllers ; // 控制层Controller接口

        // 控制层API接口类
        @Data
        public static class ControllerApiBean {
            private String name ; // 控制层的名称
            private String desc ; // 控制层描述，主要描述这个接口控制层的作用
            private String endPoint ; // 控制层的Request请求地址
            private List<ApiBean> apis ; // 接口列表对象
        }

        // 接口信息
        @Data
        public static class ApiBean {
            private String name ;  // 接口名称
            private String desc ; // 接口描述

            private String methodName ; // 方法名称
            private String requestType ; // 方法请求类型(POST/GET/DELETE/PUT)
            private String endPoint ; // 请求地址

            private List<RequestParamDtoBean> requestParams ; // 请求参数
            private List<ResponseParamDtoBean> responseParams ; // 返回请求参数

            private String successMsg ; // 成功返回提示
            private String errorMsg ; // 异常返回提示
        }

        // 接口请求参数
        @Data
        public static class RequestParamDtoBean {
            private String type ; // 参数类型
            private String name ; // 参数名称
            private String desc ; // 参数描述
        }

        // 接口请求参数
        @Data
        public static class ResponseParamDtoBean {
            private String type ; // 参数类型
            private String name ; // 参数名称
            private String desc ; // 参数描述
        }
    }

}
