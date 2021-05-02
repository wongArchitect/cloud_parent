package com.mn.trans.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

//@Configuration
//@MapperScan(basePackages = "com.bert.mapper.master2", sqlSessionTemplateRef = "master2SqlSessionTemplate")
public class DataSource2Config {

    @Bean(name = "master2DataSource")
    @ConfigurationProperties(prefix = "master2.datasource")
    public DataSource master2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "master2SqlSessionFactory")
    public SqlSessionFactory master2SqlSessionFactory(@Qualifier("master2DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/master2/*.xml"));

        // 分页拦截器-begin
//        PageInterceptor pageHelper = new PageInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty("helperDialect", "com.github.pagehelper.dialect.helper.SqlServerDialect");
//        properties.setProperty("offsetAsPageNum", "true");
//        properties.setProperty("rowBoundsWithCount", "false");
//        properties.setProperty("reasonable", "false");
//        pageHelper.setProperties(properties);
//        bean.getObject().getConfiguration().addInterceptor(pageHelper);

        return bean.getObject();
    }

    @Bean(name = "master2TransactionManager")
    public DataSourceTransactionManager master2TransactionManager(@Qualifier("master2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "master2SqlSessionTemplate")
    public SqlSessionTemplate master2SqlSessionTemplate(
            @Qualifier("master2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

