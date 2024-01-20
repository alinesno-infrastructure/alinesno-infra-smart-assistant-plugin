package com.alinesno.infra.smart.assistant.plugin.tools.bean;

import java.util.List;

/**
 * 专家列表
 */
public class Agents {

    private List<PluginInfo> agents ;

    public List<PluginInfo> getAgents() {
        return agents;
    }

    public void setAgents(List<PluginInfo> agents) {
        this.agents = agents;
    }
}
