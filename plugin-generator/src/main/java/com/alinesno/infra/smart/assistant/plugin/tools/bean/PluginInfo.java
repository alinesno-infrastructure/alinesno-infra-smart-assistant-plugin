package com.alinesno.infra.smart.assistant.plugin.tools.bean;

/**
 * 插件信息类
 */
public class PluginInfo {

    private String name;
    private String desc;
    private String author;
    private String jarName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    @Override
    public String toString() {
        return "PluginInfo{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", author='" + author + '\'' +
                ", jarName='" + jarName + '\'' +
                '}';
    }
}