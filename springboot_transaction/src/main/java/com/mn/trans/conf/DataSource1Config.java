package com.mn.trans.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Properties;
import javax.sql.DataSource;

//@Configuration
//@MapperScan(basePackages = "com.bert.mapper.master1", sqlSessionTemplateRef = "master1SqlSessionTemplate")
public class DataSource1Config {

    @Bean(name = "master1DataSource")
    @ConfigurationProperties(prefix = "master1.datasource")
    @Primary
    public DataSource master1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "master1SqlSessionFactory")
    @Primary
    public SqlSessionFactory master1SqlSessionFactory(@Qualifier("master1DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/master1/*.xml"));

//        // 分页拦截器-begin
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

    @Bean(name = "master1TransactionManager")
    @Primary
    public DataSourceTransactionManager master1TransactionManager(@Qualifier("master1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "master1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate master1SqlSessionTemplate(
            @Qualifier("master1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

