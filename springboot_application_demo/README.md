# 工程简介

网址：
       
       spring boot：ApplicationRunner和CommandLineRunner用法区别
            https://blog.csdn.net/weixin_38362455/article/details/83023025  
      
       SpringApplicationBuilder：
            https://blog.csdn.net/qq_40794973/article/details/101039069
            

# 延伸阅读
        
        
一、spring boot：ApplicationRunner和CommandLineRunner用法区别

            spring boot：ApplicationRunner和CommandLineRunner用法区别

   1、业务场景: 启动时的数据或初始化操作。
            
            应用服务启动时，加载一些数据和执行一些应用的初始化动作。如：删除临时文件，清除缓存信息，读取配置文件信息，数据库连接等。
        
   2、SpringBoot提供了CommandLineRunner和ApplicationRunner接口。当接口有多个实现类时，提供了@order注解实现自定义执行顺序，也可以实现Ordered接口来自定义顺序。
            
            注意：数字越小，优先级越高，也就是@Order(1)注解的类会在@Order(2)注解的类之前执行。
   
   3、两者的区别在于：参数列表不同；其中，接收命令行参数需用ApplicationRunner接口！
            
            ApplicationRunner中run方法的参数为ApplicationArguments，而CommandLineRunner接口中run方法的参数为String数组。
            想要更详细地获取命令行参数，那就使用ApplicationRunner接口
            
    4、代码
            1）ApplicationRunner
            
                @Component
                @Order(value = 10)
                public class AgentApplicationRun2 implements ApplicationRunner {
                    @Override
                    public void run(ApplicationArguments applicationArguments) throws Exception {
                
                    }
                }
                
             2）CommandLineRunner
             
                @Component
                @Order(value = 11)
                public class AgentApplicationRun implements CommandLineRunner {
                
                	@Override
                	public void run(String... strings) throws Exception {
                
                	}
                }
   
         
二、SpringApplicationBuilder和SpringApplication

        SpringApplicationBuilder和SpringApplication

   1、SpringApplication.run 直接运行
    
            @SpringBootApplication
            public class App {
                public static void main(String[] args) {
                    SpringApplication.run(App.class, args);
                }
            }
            
       注： 默认的配置文件 application.properties 和 application.yml
       
   2、使用SpringApplicationBuilder指定其他配置
   
            如果想自己指定配置文件，可以在Spring容器的启动命令中加入参数

        部分代码：其他代码参见项目中的AgentApplicationRun类、AgentApplicationRun2类、TestProfiles2类、TestYaml类。
        
                @SpringBootApplication
                public class TestProfiles {
                 
                	public static void main(String[] args) {
                		ConfigurableApplicationContext context = new SpringApplicationBuilder(TestProfiles.class)
                				.properties("spring.config.location=classpath:/test-profiles.yml")
                				.properties("spring.profiles.active=oracle")
                				.run(args);
                		// 输出变量
                		System.out.println(context.getEnvironment().getProperty("jdbc.driver"));
                 
                		// 启动第二个Spring容器，指定端口为8848
                		ConfigurableApplicationContext context2 = new SpringApplicationBuilder(TestProfiles.class)
                				.properties("spring.config.location=classpath:/test-profiles.yml")
                				.properties("spring.profiles.active=mysql")
                				.properties("server.port=8848")
                				.run(args);
                		// 输出变量
                		System.out.println(context2.getEnvironment().getProperty("jdbc.driver"));
                	}
                }
               
             
             
三、其他  
        
        springBoot区分web和非web项目
    
   1、springBoot区分web和非web项目
    
            所谓的banner就是控制台打印的一堆线组成的spring
            
     1）老版本
     
        
        #server config
        #web_environment是否是web项目
        spring.main.web_environment=true
        #是否加载springboot banner
        spring.main.show_banner=false

     2）新版本
     
        #server config
        #是否设定web应用，none-非web，servlet-web应用
        spring.main.web-application-type=servlet
        #加载springboot banner的方式：off-关闭，console-控制台，log-日志
        spring.main.banner-mode=off

               
               
               
  