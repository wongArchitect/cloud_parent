

   网址：Spring Boot多数据源及其事务管理配置
      
      
      https://www.cnblogs.com/lixianguo/p/12522442.html
      
      其他参考： 
      https://blog.csdn.net/u014633852/article/details/69666090
      
   一、基本配置：
   
     数据源属性配置、交给spring（springboot）创建与加载、数据源配置。
     
   1、添加数据源（数据库）属性
   
        spring.datasource.prod.driverClassName=com.mysql.jdbc.Driver
        spring.datasource.prod.url=jdbc:mysql://127.0.0.1:3306/prod
        spring.datasource.prod.username=root
        spring.datasource.prod.password=123456
        
        spring.datasource.dev.driverClassName=com.mysql.jdbc.Driver
        spring.datasource.dev.url=jdbc:mysql://127.0.0.1:3306/dev
        spring.datasource.dev.username=root
        spring.datasource.dev.password=123456
        
   2、Java配置类，为其添加上注解 @Configuration。
    
          可以放在一个或多个文件中，只要 Bean 对应即可。
            
            通过方法上加注解@Bean实现的prodDataSource和devDataSource两个数据源类应对应于prodJdbcOperations和devJdbcOperations的两个JdbcOperations类。
                注：两个prodDataSource和devDataSource也对应JdbcOperations类创建的参数值
                    此处返回的不是JdbcTemplate的实现，而是其实现接口JdbcOperations。
            通过方法上加注解@Bean实现的prodTransactionManager和devTransactionManager两个事务管理类应对应于@Transactional(value = "devTransactionManager")和@Transactional(value = "devTransactionManager")两个注解。
                注：两个prodDataSource和devDataSource也对应事物管理创建的参数值
          
          此子模块写了两个事务管理器，放在两个类中，分别为 PlatformTransactionManagerProd 和 PlatformTransactionManagerDev
      
   3、创建数据源类：使用方法+@Bean创建，spring（springboot）启动时创建且加载到IOC容器。
          
          代码如下：
                @Bean
                @Primary
                @ConfigurationProperties(prefix = "spring.datasource.prod")
                public DataSource prodDataSource(){
                    return DataSourceBuilder.create().build();
                }
                
                @Bean
                @ConfigurationProperties(prefix = "spring.datasource.dev")
                public DataSource devDataSource(){
                    return DataSourceBuilder.create().build();
                }
                
          代码说明：
          
              @ConfigurationProperties加载属性文件的属性值。
              前缀设置：prefix，配置文件里的属性名是不需要写成spring.datasource.xxx的形式的，写成a.b.c.url也没有问题，只要在配置bean时指定前缀为a.b.c
          
              给其中一个数据源加上@Primary。因为在Spring Boot Jdbc的自动配置过程中，会对于开发者透明地使用dataSource进行一些相关配置，所以当有两个Datasource实现类时，Spring Boot将无法确定使用哪一个。
              当我们不为@Bean指定名字时，Spring会默认使用方法名作为Bean的名字，所以下面两个数据源的名字分别为prodDataSource和devDataSource。
              
              注：
              需要加@Qualifier("prodDataSource")指明：多个数据源情况下，在某一个上加 @Primary 的话，后面对数据源Bean的引用，如果不加@Qualifier("prodDataSource")来指明，会只使用这个主数据源。
              
   二、JdbcOperations的配置使用：包括JdbcTemplate和JdbcOperations区别。
   
           配置JdbcTemplate与使用
   
   1、配置JdbcTemplate：装载数据源
   
           代码如下：
   
               @Bean
               public JdbcOperations prodJdbcOperations(@Qualifier("prodDataSource") DataSource prodDataSource) {
                   return new JdbcTemplate(prodDataSource);
               }
               
               @Bean
               public JdbcOperations devJdbcOperations(@Qualifier("devDataSource") DataSource devDataSource) {
                   return new JdbcTemplate(devDataSource);
               }
               
           代码说明：
           
                在此我们返回的不是JdbcTemplate的实现，而是其实现接口JdbcOperations。
                
                使用@Qualifier注解指定该注入哪个bean，默认名字为定义该bean的方法名
                
                注：包引入错误便报红。
                    @Qualifier的类包为：
                        import org.springframework.beans.factory.annotation.Qualifier;
                    而不是：
                        import javax.inject.Qualifier;
    
   2、使用JdbcOperations：即使用JdbcTemplate，在Service或其他地方直接注入JdbcOperations即可。
   
                @Autowired
                private JdbcOperations devJdbcOperations;
            
                @Autowired
                private JdbcOperations prodJdbcOperations;     
                
           注：JdbcOperations接口定义了在JdbcTemplate类中可以使用的操作集合，包括添加、修改、查询和删除等操作（）。   
               JdbcTemplate 继承与实现了JdbcOperations接口。
               JdbcOperations类声明的源码：
                   public class JdbcTemplate extends JdbcAccessor implements JdbcOperations{ ...... }     
                   
                   
   三、 事务配置与使用  
    
        开启事务管理功能、配置事务管理器和使用
        
   1、在项目入口类（Application类），添加以下注解开启事务管理功能
   
                @EnableTransactionManagement
                
            其他：只是事务功能，两个足够了
                
                @SpringBootApplication
    
   2、配置事务
   
            代码如下：
            
                @Bean
                public PlatformTransactionManager prodTransactionManager(@Qualifier("prodDataSource") DataSource prodDataSource) {
                    return new DataSourceTransactionManager(prodDataSource);
                }
                
                @Bean
                public PlatformTransactionManager devTransactionManager(@Qualifier("devDataSource") DataSource sitDataSource) {
                    return new DataSourceTransactionManager(sitDataSource);
                }      
                
            注：@Qualifier("prodDataSource")的使用
                 
                 引入正确的包名
                 数据源要对应    
                
   3、使用：加上注解 @Transactional(value = "prodTransactionManager")，在Service或其他地方直接注入JdbcOperations即可。
       
                @Transactional(value = "prodTransactionManager")
                public void prod() {
                    prodJdbcOperations.queryForList("SELECT * FROM USER");
                }
                
                @Transactional(value = "devTransactionManager")
                public void dev() {
                    devJdbcOperations.queryForList("SELECT * FROM USER");
                }    
                
            注：加上rollbackFor = Exception.class，可以根据业务中的异常回滚
                      @Transactional(rollbackFor = Exception.class)              