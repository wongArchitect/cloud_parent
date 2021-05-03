# 工程简介



# 延伸阅读

1、连接池的配置：springboot中，只需加 "datasource.type" 属性即可。

        spring.datasource.dev.datasource.type=com.alibaba.druid.pool.DruidDataSource
    
    注：有默认配置的。
    
2、其他配置：自定义，不用默认（修改配置的部分）。

    #数据库连接池配置
    
        spring.datasource.prod.druid.initial-size = 5
        spring.datasource.prod.druid.max-active = 20
        spring.datasource.prod.min-idle = 5
        spring.datasource.prod.druid.max-wait= 30000

3、多数据源和多事务管理：使用前缀来区分。
    
    再多也一样，只需在加上如上（上面 1 和 2 的内容）属性即可。
    
    注：前缀来区分多个
    
        如下举例：
        
            spring.datasource.dev.datasource.type=com.alibaba.druid.pool.DruidDataSource
            
            spring.datasource.prod.datasource.type=com.alibaba.druid.pool.DruidDataSource

