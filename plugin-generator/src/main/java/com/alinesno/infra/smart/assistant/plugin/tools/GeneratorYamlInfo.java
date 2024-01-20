package com.alinesno.infra.smart.assistant.plugin.tools;

import com.alinesno.infra.smart.assistant.plugin.tools.bean.Agents;
import com.alinesno.infra.smart.assistant.plugin.tools.bean.PluginInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 生成java yaml信息
 */
public class GeneratorYamlInfo {

    private static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    public static void main(String[] args) throws IOException {

        String pluginYamlPath = args[0] ;
        String pluginPath = args[1] ;

        String pluginYaml = FileUtils.readFileToString(new File(pluginYamlPath) , Charset.defaultCharset())  ;
        Agents agent = YAMLMapper.fromYAML(pluginYaml , Agents.class) ;

        Map<String , String> pluginFileMap = getPluginFileMap(pluginPath) ;

        // 循环下载插件
        List<PluginInfo> newPluginInfo = new ArrayList<>() ;
        for (PluginInfo plugin : agent.getAgents()) {

            System.out.println("plugin = " + plugin);
            String pluginName = plugin.getName() ;

            String newJarName = pluginFileMap.get(pluginName) ;
            System.out.println("new jar file name = " + newJarName);

            PluginInfo n = new PluginInfo() ;

            n.setName(pluginName);
            n.setAuthor(plugin.getAuthor());
            n.setDesc(plugin.getDesc());
            n.setJarName(newJarName);

            newPluginInfo.add(n) ;
        }

        agent.setAgents(newPluginInfo);
        String newPluginYamlInfo = YAMLMapper.toYAML(agent) ;
        System.out.println(newPluginYamlInfo);

        FileUtils.writeStringToFile(new File(pluginYamlPath) , newPluginYamlInfo , Charset.defaultCharset(), false);

    }

    private static Map<String, String> getPluginFileMap(String pluginPath) {
        File[] pluginFile = new File(pluginPath).listFiles() ;

        Arrays.sort(pluginFile, new Comparator<File>() {
            public int compare(File f1, File f2) {
                long diff = f1.lastModified() - f2.lastModified();
                if (diff > 0)
                    return 1;
                else if (diff == 0)
                    return 0;
                else
                    return -1;//如果 if 中修改为 返回-1 同时此处修改为返回 1  排序就会是递减
            }

            public boolean equals(Object obj) {
                return true;
            }

        });

        Map<String , String> map = new HashMap<>() ;

        for(File jarFile : pluginFile){
            String jarName = jarFile.getName() ;

            if(jarName.endsWith(".jar")){
                jarName = jarName.substring(0 , jarName.indexOf("__")) ;
                map.put(jarName , jarFile.getName()) ;
                System.out.println("pluginName = " + jarName + " , jarName = " + jarFile.getName());
            }
        }

        for(String key : map.keySet()){
            System.out.println("key = " + key + " , jarName = " + map.get(key));
        }

        return map ;
    }
}
