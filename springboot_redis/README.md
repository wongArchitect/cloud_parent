# 工程简介

单元测试空指针异常：注意@Test类来自不同的包。
一般，springbootTest使用Junit4中为org.junit.Test

# 延伸阅读

一、 非关系型数据库：

                Redis、mongodb。
        
   1、概念
   
           NoSQL最常见的解释是“non-relational”， “Not Only SQL”也被很多人接受。
           NoSQL仅仅是一个概念，泛指非关系型的数据库，区别于关系数据库，它们不保证关系数据的ACID特性。
           
   2、NoSQL数据库类型
        
        文档数据库——这些数据库通常将每个键与称为文档的复杂数据结构配对。文档可以包含键数组对、键值对甚至嵌套文档。示例：MongoDB、Apache CouchDB、ArangoDB、Couchbase、Cosmos DB、IBM Domino、MarkLogic、OrientDB。
        
        键值存储——每个单独的项都存储为键值对。键值存储是所有NoSQL数据库中最简单的数据库。示例：Redis, Memcached, Apache Ignite, Riak。
        
        宽列存储——这些类型的数据库针对大型数据集上的查询进行了优化，它们将数据列存储在一起，而不是行。示例：Cassandra，Hbase，Scylla。

二、Bug

        本项目的一个bug
        
1、NullPointerException空指针异常:

    如果你在使用Spring Boot进行单元测试时出现诸如NullPointerException空指针异常。但检查了所有的写法都没有问题，那么可能是引入类的问题。

        比如如下代码：

                package com.secbro2.learn.service;
                import com.secbro2.learn.model.Order;
                import org.junit.jupiter.api.Test;
                import org.springframework.boot.test.context.SpringBootTest;
                import javax.annotation.Resource;
                @SpringBootTest
                public class OrderServiceTest {
                    @Resource
                    private OrderService orderService;
                    @Test
                    public void testInsert() {
                        Order order = new Order();
                        order.setOrderNo("A001");
                        order.setUserId(100);
                        orderService.insert(order);
                    }
                } 
        在上面的代码中执行testInsert的测试，如果出现空指针异常最低级的错误是OrderService类没有通过@Service这样的注解进行初始化。
        
        另外一种错误就是testInsert方法上的@Test注解类的引入错误。这种情况经常发生在Junit4升级到Junit5的情况。因为这两个版本使用的@Test注解类是不同的类。

Junit4中为org.junit.Test，而Junit5中为org.junit.jupiter.api.Test。因此，检查一下你引入的@Test是否是你所需要的。
