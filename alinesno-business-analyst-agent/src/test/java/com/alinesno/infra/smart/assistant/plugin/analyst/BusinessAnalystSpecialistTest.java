package com.alinesno.infra.smart.assistant.plugin.analyst;

import com.alinesno.infra.smart.assistant.role.utils.YAMLMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import com.alinesno.infra.smart.assistant.plugin.analyst.BusinessAnalystSpecialist.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BusinessAnalystSpecialistTest {

    @SneakyThrows
    @Test
    public void test02() {
        // 创建电商管理系统功能列表实例
        FunctionBean functionList = new FunctionBean();

        // 设置功能列表
        functionList.setFunction(Arrays.asList(
                        createFunction("用户管理", "管理系统用户，包括商家和消费者", Collections.singletonList(
                                        createFunctionModel("用户注册", "商家和消费者通过系统进行注册")),
                                Arrays.asList(
                                        createSubFunctionModel("用户注册", "商家和消费者通过系统进行注册"),
                                        createSubFunctionModel("用户登录", "注册用户通过系统进行登录")
                                )
                        ),
                createFunction("商品管理", "管理系统中的商品信息，包括添加、修改、删除商品", Collections.singletonList(
                                createFunctionModel("商品上架", "商家将商品信息添加到系统中")),
                        Arrays.asList(
                                createSubFunctionModel("添加商品信息", "商家添加商品的详细信息"),
                                createSubFunctionModel("修改商品信息", "商家修改已有商品的详细信息"),
                                createSubFunctionModel("删除商品信息", "商家从系统中删除商品信息")
                        )
                ),
                createFunction("订单处理", "处理用户的购物订单，包括下单、支付、发货、退款等操作", Collections.singletonList(
                                createFunctionModel("下单", "消费者选择商品并生成购物订单")),
                        Arrays.asList(
                                createSubFunctionModel("选择商品", "消费者在系统中浏览商品并选择购买"),
                                createSubFunctionModel("生成订单", "消费者确认购物车中的商品并生成订单"),
                                createSubFunctionModel("支付订单", "消费者通过系统支付购物订单"),
                                createSubFunctionModel("发货", "商家处理订单并进行商品发货"),
                                createSubFunctionModel("退款", "商家处理消费者的退款请求")
                        )
                ),
        createFunction("支付系统", "处理系统中的支付操作，包括支付方式管理和支付流程处理", Collections.singletonList(
                        createFunctionModel("支付方式管理", "系统管理员管理支持的支付方式")),
                Arrays.asList(
                        createSubFunctionModel("添加支付方式", "系统管理员添加新的支付方式"),
                        createSubFunctionModel("删除支付方式", "系统管理员删除不再支持的支付方式")
                )
        )
        ));

        System.out.println(YAMLMapper.toYAML(functionList));

    }



    private static FunctionBean.Function createFunction(String title, String summary,
                                                        List<FunctionBean.FunctionModel> models,
                                                        List<FunctionBean.SubFunctionModel> subFunctionModels) {
        FunctionBean.Function function = new FunctionBean.Function();
        function.setTitle(title);
        function.setSummary(summary);
        function.setModels(models);
        function.getModels().forEach(model -> model.setSubFunctionModel(subFunctionModels));
        return function;
    }

    private static FunctionBean.FunctionModel createFunctionModel(String name, String desc) {
        FunctionBean.FunctionModel model = new FunctionBean.FunctionModel();
        model.setName(name);
        model.setDesc(desc);
        return model;
    }

    private static FunctionBean.SubFunctionModel createSubFunctionModel(String name, String desc) {
        FunctionBean.SubFunctionModel subFunctionModel = new FunctionBean.SubFunctionModel();
        subFunctionModel.setName(name);
        subFunctionModel.setDesc(desc);
        return subFunctionModel;
    }

    @SneakyThrows
    @Test
    public void test01(){
        // 创建需求文档介绍部分实例
        BusinessAnalystSpecialist.DocumentIntroduction documentIntroduction = new BusinessAnalystSpecialist.DocumentIntroduction();

        // 设置文档的目的
        documentIntroduction.setPurpose("本规格说明描述了CRM项目的需求，作为系统设计、实现目标及验收的依据。"
                + "通过该需求分析，详细描述了用户的具体需求，定义了需求的具体规格和内容。"
                + "作为各方沟通的依据，本文档还将作为下一步工作的基准。"
                + "软件开发小组的每一位成员都应该阅读本需求说明，以明确项目最终要求完成的软件产品的特点。"
                + "通过使用方认可的需求说明将作为产品特征评价、仲裁的重要参考。");

        // 设置文档的范围
        documentIntroduction.setScope("本文档包括了CRM管理系统的整体需求和功能性需求，涵盖了用户注册与登录、客户管理、订单管理和支付与结算等核心模块。");

        // 设置文档的目标读者
        documentIntroduction.setAudience("本文档的读者对象包括开发团队成员、产品经理、测试人员以及其他相关的项目参与者。");

        // 设置术语和缩写的解释
        documentIntroduction.setTerminology("CRM: 客户关系管理系统是把有关市场和客户的信息进行统一管理、共享，并能进行有效分析的处理的新型应用系统。"
                + "它为企业内部的销售、营销、客户服务等提供全面的支持。");

        // 设置文档中相关材料的参考
        documentIntroduction.setReferences("### 相关的文件包括：\n"
                + "- 江苏淮微技术中心《CRM项目开发计划》；\n"
                + "### 参考资料：\n"
                + "- 国家标准《软件需求说明书（GB856T——88）》\n"
                + "- 《软件工程》\n"
                + "- 《设计模式》\n"
                + "- 《CRM客户关系管理系统》");

        System.out.println(YAMLMapper.toYAML(documentIntroduction));
    }
}