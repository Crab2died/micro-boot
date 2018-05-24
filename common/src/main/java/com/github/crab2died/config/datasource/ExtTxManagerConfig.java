package com.github.crab2died.config.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class ExtTxManagerConfig extends DataSourceTransactionManagerAutoConfiguration {

    @Resource(name = "routingDataSource")
    private DataSource routingDataSource;

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManagers() {
        return new DataSourceTransactionManager(routingDataSource);
    }
}
