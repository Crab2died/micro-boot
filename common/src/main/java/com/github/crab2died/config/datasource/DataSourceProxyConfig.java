package com.github.crab2died.config.datasource;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
public class DataSourceProxyConfig extends MybatisAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DataSourceProxyConfig.class);

    @Autowired
    private DataSource writeDataSource;

    @Autowired
    private DataSource readDataSource01;

    @Autowired
    private DataSource readDataSource02;

    public DataSourceProxyConfig(MybatisProperties properties,
                                 ObjectProvider<Interceptor[]> interceptorsProvider,
                                 ResourceLoader resourceLoader,
                                 ObjectProvider<DatabaseIdProvider> databaseIdProvider,
                                 ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        return super.sqlSessionFactory(routingDataSource());
    }

    @Bean(name = "routingDataSource")
    public AbstractRoutingDataSource routingDataSource() {

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.WRITE_DB.type(), writeDataSource);
        targetDataSources.put(DataSourceEnum.READE_DB.type() + "1", readDataSource01);
        targetDataSources.put(DataSourceEnum.READE_DB.type() + "2", readDataSource02);

        AbstractRoutingDataSource proxy = new AbstractRoutingDataSource() {

            @Override
            protected Object determineCurrentLookupKey() {
                String type = DataSourceContextHolder.jdbcType();
                if (null == type){
                    throw new NullPointerException();
                }
                if (type.equals(DataSourceEnum.WRITE_DB.type())){
                    log.info("Inherited write datasource ==> " + DataSourceEnum.WRITE_DB.type());
                    return DataSourceEnum.WRITE_DB.type();
                }
                String targetDb = DataSourceEnum.READE_DB.type() + ((new Random().nextInt(1000)) % 2 + 1);
                log.info("Select the read datasource ==> " + targetDb);
                return targetDb;
            }
        };
        proxy.setDefaultTargetDataSource(writeDataSource);
        proxy.setTargetDataSources(targetDataSources);

        return proxy;
    }
}
