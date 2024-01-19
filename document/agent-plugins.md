# 插件转换成yaml-info的prompt

你现在是一名yaml编写人员，根据xml的信息，参考原有的格式，编写和完善yaml内容：

下面是xml的内容:
```xml
<!-- 示例工程 -->
<module>alinesno-demo-agent</module>
<module>alinesno-office-default-agent</module> <!-- 前期调试的Agent(不再维护)-->

<!-- agent-plugin插件 -->
<module>alinesno-product-article-agent</module> <!-- 软文编写专家 agent -->
<module>alinesno-ansible-operation-agent</module> <!-- ansible脚本专家agent -->
<module>alinesno-business-analyst-agent</module> <!-- 需求分析专家agent -->
<module>alinesno-database-design-agent</module> <!-- 数据库设计专家agent -->
<module>alinesno-kubernetes-operation-agent</module> <!-- kubernetes脚本专家agent -->
<module>alinesno-api-design-agent</module> <!-- api接口编写agent -->
<module>alinesno-starter-code-generator-agent</module> <!-- 代码生成器编写agent -->
<module>alinesno-company-team-agent</module> <!-- 团队成长管理agent -->
```

下面是需要进一步完善的yaml信息:
```yaml
# Agent列表
agents:
  - name: "alinesno-demo-agent"
    desc: "示例工程"
    author: "luoxiaodong"
    jarName: "alinesno-demo-agent.jar"

  - name: "alinesno-office-default-agent"
    desc: "前期调试的Agent,不再维护,由其它agent维护"
    author: "luoxiaodong"
    jarName: "alinesno-office-default-agent.jar"

  - name: "alinesno-product-article-agent"
    desc: "软文编写专家"
    author: "luoxiaodong"
    jarName: "alinesno-product-article-agent.jar"

  - name: "alinesno-ansible-operation-agent"
    desc: "Ansible自动化脚本专家"
    author: "luoxiaodong"
    jarName: "alinesno-ansible-operation-agent.jar"
```

请编写完善agent内容:
```yaml
<在这里返回yaml信息>
```