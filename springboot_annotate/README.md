# 工程简介
一、注解：

    也许可以理解为，通过注入到IOC，让spring（springboot）来自动解决需求问题或增加功能。

    也可以理解为，通过标记做修饰（设计模式中的修饰模式）再交给spring（springboot），加强功能实现或实现功能加强。

    注：英文为annotate，an 加强 + not 标记 + ate 做 → 做标记 → 注解

二、元注解
    
    @Target、@Retention、@Inherited、@Documented
    @Retention：表明该注解的生命周期 。retention ，保持; 维持; 保留; (液体、热量等的)保持，阻滞; 记忆力; 记性。 
    @Inherited：是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。inherited，继承(金钱、财产等); 经遗传获得(品质、身体特征等); 接替(责任等); 继任。
# 延伸阅读

一、new菜单创建类：
    
   创建Class
   
    new --> class --> Annotation 类型为注解类，与接口相比，类名前多个“@”修饰符。在创建class是选择类型annotate，创建注解类。
    new --> class --> Interface 类型为接口类。
    new --> class --> Enum 类型为枚举类。
    
   创建Aspect

    new --> Aspect --> Aspect 类型为一般切面类。
    new --> Aspect --> @Aspect 类型为注解切面类，创建后类名前多个“@”。


二、@SpringBootApplication注解，启动Bug
   
   Bug信息：
   
          If you want an embedded database please put a supported one on the classpath. If you have database settings to be loaded from a particular profile you may need to active it (no profiles are currently active).

   解决方案：
    
      在启动类的@EnableAutoConfiguration或@SpringBootApplication中添加exclude = DataSourceAutoConfiguration.class，排除这个类的autoconfig，然后再次启动，就可以正常运行了
      @SpringBootApplication(exclude = DataSourceAutoConfiguration.class)

三、@RequestBody与@RequestParam：参数列表中使用的注解；解决bug“Required String parameter is not present”。
         
      1、解决bug：“Required String parameter is not present”
      
          1）public OverviewASRResponse getOverviewASR(@UserRole Integer role, @UserId Integer userId , @RequestParam(value="start_date") String start_date){}
          2）因为希望前端传给后端的是JOSN串，所以应该使用@RequestBody来接收数据，使用map的key-value形式去获取json传送过来的数据
          3）public OverviewASRResponse getOverviewASR(@UserRole Integer role, @UserId Integer userId , @RequestBody Map<String,Object> params){
                OverviewASRResponse response = new OverviewASRResponse();
                return response;
            }
        
      2、总结：
        
          ① form-data、x-www-form-urlencoded：不可以用@RequestBody；可以用@RequestParam。这两种方式的时候没有json字符串部分。
          ② application/json：json字符串部分可以用@RequestBody；url中的?后面参数可以用@RequestParam
          一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。比如异步获取json数据，加上@responsebody后，会直接返回json数据。


四、@RestController和@Controller+@Responsebody理解

    1、如果需要返回的是数据（如：JSON、XML或自定义的metatype等数据类型）时，@RestController完全等同于@Controller+@Responsebody：
       1） @RestController:返回的直接是index字符串（优先）
       2）@Controller:如果需要返回的是index字符串（JSON、XML等数据类型），则需要在每个方法体里面添加@ResponseBody注解（简单理解为@ResponseBody注解就是标记方法体不调用视图解析器）

    2、如果要返回的是jsp、html等页面时两种注解使用方法：
        1）@RestController:方法体配合ModelAndView使用
        2）@Controller:可直接返回jsp、index等页面（优先）
