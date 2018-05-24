package com.github.crab2died.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Primary
    @Bean("writeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.write-db")
    public DataSource writeDataSource(){
        logger.info("-----------------Datasource Write DB inited---------------------");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("read01DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read01-db")
    public DataSource read01DataSource(){
        logger.info("----------------Datasource Read DB-01 inited-------------------");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("read02DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read02-db")
    public DataSource read02DataSource(){
        logger.info("----------------Datasource Read DB-02 inited-------------------");
        return DruidDataSourceBuilder.create().build();
    }

}
