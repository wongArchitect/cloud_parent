package com.mn.trans.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * JdbcTemplate 继承与实现了JdbcOperations接口。
 * 类声明的源码：public class JdbcTemplate extends JdbcAccessor implements JdbcOperations{ ......}
 */
@Configuration
public class JdbcOperationsTest {
    @Bean
    public JdbcOperations devJdbcOperations(@Qualifier("devDataSource") DataSource devDataSource) {
        return new JdbcTemplate(devDataSource);
    }

    @Bean
//    public JdbcOperations prodJdbcOperations(@Qualifier("prodDataSource") DataSource prodDataSource) {
//    Spring会将变量名与Bean的名字做关联。在此我们入参数据源的名称和上面数据源Bean的方法名相匹配，所以也不需要用@Qualifier注解指定是哪个Bean。
    //    但测试，加上是报错的。暂且这样。
    public JdbcOperations prodJdbcOperations(@Qualifier("prodDataSource") DataSource prodDataSource) {
        return new JdbcTemplate(prodDataSource);
    }
}
