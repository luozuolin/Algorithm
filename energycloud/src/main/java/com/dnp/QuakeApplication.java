package com.dnp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright 2016 DONOPO Ltd. All rights reserved.
 *
 * Remark   : 雷神引擎启动类
 * <p/>
 * Author   : Tim Mars
 * Project  : Quake
 * Date     : 6/22/2016
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.dnp")
@ConfigurationProperties(prefix = "app")
public class QuakeApplication {

    private String name;
    private String version;

	private static final Logger logger = LoggerFactory.getLogger(QuakeApplication.class);
	private static ApplicationContext ctx;

    public static ApplicationContext getCtx() {
        return ctx;
    }

    public static void main(String[] args) throws Exception {
        ctx = SpringApplication.run(QuakeApplication.class, args);
        QuakeApplication quakeApplication = ctx.getBean(QuakeApplication.class);
        quakeApplication.init();
	}

    private void init() {
        logger.info("QuakeApplication initialize!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
