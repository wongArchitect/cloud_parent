参考网页


   Controller层统一响应(Json格式)处理过程： 
    
        https://blog.csdn.net/whos2016/article/details/104808155
    
   springboot之全局异常拦截器： 
        
        https://blog.csdn.net/qq_36922927/article/details/82026683


1、实现方法：通常情况我们在Controller层进行返回数据时候，对具体的业务数据要再次包装一下返回
      
      加注解来实现包装：@ControllerAdvice
        
2、全面些：统一异常处理，Exception异常类的包装。

        GlobalExceptionHandler


3、在springboot_web子项目中有对ResponseBody的包装：使返回更简洁。
       
       已经实现的基础上额外包装：响应格式控制拦截器 ResponseFormatJsonInterceptor
       



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
