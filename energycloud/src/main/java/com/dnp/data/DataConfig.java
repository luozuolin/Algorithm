package com.dnp.data;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Copyright 2016 DONOPO Ltd. All rights reserved.
 *
 * Remark   : 数据源控制器,通过一个最好的缓冲池进行管理
 * <p/>
 * Author   : Tim Mars
 * Project  : Quake
 * Date     : 6/22/2016
 */
@Configuration
@Lazy
@EnableTransactionManagement
@MapperScan( "com.dnp")
public class DataConfig {

    @Autowired
    DataParam dataParam;

    /**
     <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
     <!-- 基本属性 url、user、password -->
     <property name="url" value="${jdbc_url}" />
     <property name="username" value="${jdbc_user}" />
     <property name="password" value="${jdbc_password}" />

     <!-- 配置初始化大小、最小、最大 -->
     <property name="initialSize" value="1" />
     <property name="minIdle" value="1" />
     <property name="maxActive" value="20" />

     <!-- 配置获取连接等待超时的时间 -->
     <property name="maxWait" value="60000" />

     <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
     <property name="timeBetweenEvictionRunsMillis" value="60000" />

     <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
     <property name="minEvictableIdleTimeMillis" value="300000" />

     <property name="validationQuery" value="SELECT 'x'" />
     <property name="testWhileIdle" value="true" />
     <property name="testOnBorrow" value="false" />
     <property name="testOnReturn" value="false" />

     <!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
     <property name="poolPreparedStatements" value="true" />
     <property name="maxPoolPreparedStatementPerConnectionSize" value="20" />

     <!-- 配置监控统计拦截的filters -->
     <property name="filters" value="stat" />
     </bean>
\     */

    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dataParam.getDriver());
        druidDataSource.setUrl(dataParam.getUrl());
        druidDataSource.setUsername(dataParam.getUsername());
        druidDataSource.setPassword(dataParam.getPassword());
        druidDataSource.setDefaultAutoCommit(true);
        return druidDataSource;
    }

    @Bean
    @Autowired
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    @Bean
    public CommonsMultipartResolver multipartResolvers() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        return multipartResolver;
    }

}
