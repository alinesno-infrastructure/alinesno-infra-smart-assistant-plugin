package com.alinesno.infra.smart.assistant.plugin.office;

import com.alinesno.infra.smart.assistant.api.adapter.TaskContentDto;
import com.alinesno.infra.smart.assistant.role.PlatformExpert;
import com.alinesno.infra.smart.assistant.role.context.RoleChainContext;
import com.alinesno.infra.smart.assistant.role.utils.YAMLMapper;
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
 * 数据库设计专家
 */
@Slf4j
@Component
public class DatabaseDesignDDLExpert extends PlatformExpert {

    private static final String promptId = "YOFieUPq" ; // 数据库DDL设计专家
    private List<TableDDLDefinitionBean> resultMap ;

    @LiteflowComponent(value = "DD_DDL_STEP_01", name="获取到表结构并生成表")
    public class DD_DDL_STEP_01 extends NodeComponent {

        @SneakyThrows
        @Override
        public void process() {
            resultMap = new ArrayList<>() ;

            // 获取上下文
            RoleChainContext roleContext = this.getContextBean(RoleChainContext.class) ;
            roleContext.setStartTime(System.currentTimeMillis());

            // 通过上下文传入
            String businessId = roleContext.getBusinessId() ;

            List<String> businessIdList = new ArrayList<>() ;

            // 获取到解析内容
            TableDefinitionBean tableDefinitionBean = getTableDefinitionBean(roleContext.getAssistantYamlContent()) ;

            for(String tableName : tableDefinitionBean.tables.keySet()){
                List<TableDefinitionBean.TableDetail> tableDetails = tableDefinitionBean.tables.get(tableName) ;

                for(TableDefinitionBean.TableDetail tableDetail : tableDetails){
                    String bId = generatorId() ;

                    Map<String , Object> paramsLabel = this.getRequestData();
                    paramsLabel.put("label2" , tableName + ":" + tableDetail.getDesc()+"," + tableDetail.getDesc()) ;

                    brainRemoteService.chatTask(paramsLabel , bId , promptId);
                    log.debug("params = {}" , paramsLabel);

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
                    if(content.getTaskStatus() != 2){
                        isFinish = false ;
                        break ;
                    }
                }

                if(isFinish){
                    for(String bId : businessIdList) {
                        TaskContentDto content = brainRemoteService.chatContent(bId);
                        String yamlContent = content.getCodeContent().get(0).getContent();

                        System.out.println(yamlContent);

                        TableDDLDefinitionBean table = YAMLMapper.fromYAML(yamlContent , TableDDLDefinitionBean.class) ;
                        resultMap.add(table) ;
                    }
                    break ;
                }

                retryCount ++ ;
                log.debug("生效获取业务[{}]次数:{}" , businessId , retryCount);
            }

        }
    }

    /**
     * 获取到表结构信息
     * @param assistantYamlContent
     * @return
     */
    @SneakyThrows
    private TableDefinitionBean getTableDefinitionBean(String assistantYamlContent) {
        return YAMLMapper.fromYAML(assistantYamlContent , TableDefinitionBean.class);
    }

    // 根据情况，进行并行处理
    @LiteflowComponent(value = "DD_DDL_STEP_02", name="保存数据库目录结构")
    public class DD_DDL_STEP_02 extends NodeComponent {

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

    /**
     * 表相关信息
     */
    @Data
    public static class TableDefinitionBean {

        private Map<String, List<TableDetail>> tables;

        @Data
        public static class TableDetail {
            private String name;
            private String desc;
        }
    }

    /**
     * 表结构相关信息
     */
    @Data
    public static class TableDDLDefinitionBean {
        // 表名
        private String tableName;

        // 列定义列表
        private List<ColumnDefinition> columns;

        // 主键定义
        private String primaryKey;

        // 外键定义
        private List<ForeignKey> foreignKeys ;

        // 存储引擎
        private String engine;

        // 表注释
        private String comment;

        @Data
        public static class ForeignKey {
            private String name; // 外键名称
            private String columns; // 外键关联的字段
            private String referenceTable; // 外键关联的表
            private String referenceColumns; // 外键关联的字段
        }

        @Data
        public static class ColumnDefinition {
            // 列名
            private String name;

            // 列类型
            private String type;

            // 是否自增
            private boolean autoIncrement;

            // 是否允许为空
            private boolean isNull;

            // 列注释
            private String comment;

            // 默认值
            private String defaultVal;
        }
    }

}
