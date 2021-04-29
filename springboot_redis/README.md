# 工程简介

单元测试空指针异常：注意@Test类来自不同的包。
一般，springbootTest使用Junit4中为org.junit.Test

# 延伸阅读


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
