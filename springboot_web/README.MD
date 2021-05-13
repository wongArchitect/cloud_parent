参考网页


   Controller层统一响应(Json格式)处理过程： 
    
        https://blog.csdn.net/whos2016/article/details/104808155
    
   springboot之全局异常拦截器： 
        
        https://blog.csdn.net/qq_36922927/article/details/82026683

一、异常处理：
        
        内置注解、自定义注解、Exception包装三种方式。

1、实现方法：通常情况我们在Controller层进行返回数据时候，对具体的业务数据要再次包装一下返回
      
      加注解来实现包装：@ControllerAdvice

2、已经实现的基础上额外包装：响应格式控制拦截器 ResponseFormatJsonInterceptor

        自定义注解使返回更简洁
        
3、全面些：统一异常处理，Exception异常类的包装。

        ExceptionAdviceHandler


二、SpringMVC的单例模式:

            单例中的ThreadLocal属性成员（可以多个） ---> 把线程和线程对应的数据以k-v封装为ThreadLocalMap ---> v为索引下标始终唯一的数组
            
  1、SpringMVC是单例的，高并发情况下，如何保证性能的？
    
     1) 参考博文：
            https://blog.csdn.net/wei55255555/article/details/78752858
            
     2) 单例解决高并发：单利模式因为大大节省了实例的创建和销毁，有利于提高性能，而ThreadLocal用来保证线程安全性。
     
            Java里有个API叫做ThreadLocal，spring单例模式下用它来切换不同线程之间的参数，使不同线程下的数据互相之间都是隔离的。而且，spring IOC的bean管理器是“绝对的线程安全”。
            大多数场景下这种方式对内存资源的消耗较少，而且并发越高优势越明显。
            
  2、ThreadLocal：无论多个ThreadLocal，还是多个线程key与数据Value，数组的索引下标总是唯一。      
                
                ThreadLocal 内部以Map<线程对象，数据>的key-value方式储存线程和数据，每个key（线程）线程对应的Value（数据）以数组存储，
                从而每个线程以map的特性（key元素不可重复）互相隔离，value数据也因对应key来存储而不会混乱。
                每个数组的下标以“hashCode与length位运算（取模）确定出一个索引值i”来定，并通过“反位运算”（反取模）来精确定位数据值。
            
  3、注解的问题：像controller注解、加了AOP的日志注解、自定义的权限注解等注解，高并发大访问量依然不会混乱。原因，猜测如下
  
            主要总能定位到相应的数组元素（索引下标始终唯一），所以在请求与响应上才不会产生因注解产生的混乱。
  
    
  注： ThreadLocal特性
         
         ThreadLocal和Synchronized都是为了解决多线程中相同变量的访问冲突问题，不同的点是
         
         Synchronized是通过线程等待，牺牲时间来解决访问冲突
         ThreadLocal是通过每个线程单独一份存储空间，牺牲空间来解决冲突，并且相比于Synchronized，ThreadLocal具有线程隔离的效果，只有在线程内才能获取到对应的值，线程外则不能访问到想要的值。
         正因为ThreadLocal的线程隔离特性，使他的应用场景相对来说更为特殊一些。在android中Looper、ActivityThread以及AMS中都用到了ThreadLocal。当某些数据是以线程为作用域并且不同线程具有不同的数据副本的时候，就可以考虑采用ThreadLocal。


其他
            
            积累

注：Idea 的 REST Client窗口

    打开 idea  ==>Toos ==> HTTP Client ==> Test RESTful Web Service

Lombok注解-@SneakyThrows

    1、Lombok的@SneakyThrows就是为了消除下面这样的模板代码。使用注解后不需要担心Exception的处理
        try{
        }catch(Exception e){
        throw new RuntimeException(e);
        }

    2、代码比较
      原代码
        @SneakyThrows
          public void run() {
            throw new Throwable();
          }
      实际代码
          public void run() {
              try {
                throw new Throwable();
              } catch (Throwable t) {
                throw Lombok.sneakyThrow(t);
              }
            }

    3、真相：
        利用泛型将我们传入的Throwable强转为RuntimeException。因为JVM并不关心是否是RuntimeException。泛型最后存储为字节码时并没有泛型的信息。这样写只是为了骗过javac编译器。
