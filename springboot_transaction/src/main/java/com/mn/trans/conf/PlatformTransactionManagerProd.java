package com.mn.trans.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class PlatformTransactionManagerProd {


    @Bean
//    @Bean//不为@Bean指定名字时，Spring会默认使用方法名作为Bean的名字，所以此数据源的名称为“prodDataSource”
    @Primary  //主数据源
    @ConfigurationProperties(prefix = "spring.datasource.prod")  //前缀与配置bean时指定的前缀一致，此处用properties做的配置
    public DataSource prodDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
//    public PlatformTransactionManager prodTransactionManager(@Qualifier("prodDataSource") DataSource prodDataSource) {
//    Spring会将变量名与Bean的名字做关联。在此我们入参数据源的名称和上面数据源Bean的方法名相匹配，所以也不需要用@Qualifier注解指定是哪个Bean。
//    但测试，加上是报错的。暂且这样。
    public PlatformTransactionManager prodTransactionManager(@Qualifier("prodDataSource") DataSource prodDataSource) {
        return new DataSourceTransactionManager(prodDataSource);
    }

}