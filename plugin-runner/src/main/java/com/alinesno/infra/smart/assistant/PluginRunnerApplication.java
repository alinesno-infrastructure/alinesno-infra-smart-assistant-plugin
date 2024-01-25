package com.alinesno.infra.smart.assistant;

import com.dtflys.forest.springboot.annotation.ForestScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 集成一个Java开发示例工具
 * @author LuoAnDong
 * @since 2023年8月3日 上午6:23:43
 */
@Slf4j
@ForestScan(basePackages = "com.alinesno.infra.smart.assistant.adapter")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class PluginRunnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PluginRunnerApplication.class, args);
	}

}