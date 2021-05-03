package com.mn.trans.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class PlatformTransactionManagerDev {


    @Bean //此数据源的名称为“devDataSource”
    @ConfigurationProperties(prefix = "spring.datasource.dev")
//    @Primary  //主数据源
    public DataSource devDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager devTransactionManager(@Qualifier("devDataSource") DataSource devDataSource) {
        return new DataSourceTransactionManager(devDataSource);
    }

}