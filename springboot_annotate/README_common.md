    
   网址
       
       https://blog.csdn.net/guorui_java/article/details/107379648
       
       
       
       
   一、什么是Spring Boot
    
    Spring Boot是一个快速开发框架，快速的将一些常用的第三方依赖整合（通过Maven子父亲工程的方式），简化xml配置，全部采用注解形式，内置Http服务器（Jetty和Tomcat），最终以Java应用程序进行执行。
   
   二、Spring常用注解
        
        Spring常用注解（绝对经典）
   
   三、Spring Boot常用注解
   
   
   1、@SpringBootApplication
   
    替代 @SpringBootConfiguration、@EnableAutoConfiguration、@ComponentScan
   
   2、@ImportAutoConfiguration
   
    导入配置类，一般做测试的时候使用，正常优先使用@EnableAutoConfiguration 
   
   3、@SpringBootConfiguration
   
        替代@Configuration
   
   4、@ImportResource
   
       将资源导入容器
   
   5、@PropertySource 
   
    导入properties文件
   
   6、PropertySources
   
    @PropertySource 的集合
   
   7、@Role
   
    bean角色定义为ROLE_APPLICATION(默认值)、ROLE_SUPPORT(辅助角色)、ROLE_INFRASTRUCTURE(后台角色，用户无感)
   8、@Scope
   
    指定bean的作用域，默认singleton，其它包括prototype、request、session、globalSession
   
   9、@Lazy
   
    使bean懒加载，取消bean预初始化。
   
   10、@Primary
   
    自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否者将抛出异常。
   
   11、@Profile
   
    指定Bean在哪个环境下被激活
   
   12、@DependsOn
   
    依赖的bean注册完成，才注册当前类，依赖bean不存在会报错。用于控制bean加载顺序
   
   13、@PostConstruct
   
       bean的属性都注入完毕后，执行注解标注的方式进行初始化工作
   
   14、@Autowired
   
    默认按类型装配，如果我们想使用按名称装配，可以结合@Qualifier注解一起使用。
   
   15、@Lookup
   
    根据方法返回的类型，去容器中捞出对应
   
   16、@Qualifier
   
    申明bean名字，且可以按bean名字加载bean
   
   17、@Required
   
    检查bean的属性setXXX()方法，要求属性砸死配置阶段必须已配置
   
   18、@Description
   
    添加bean的文字描述
   
   19、@EnableAspectConfiguration
   
    启动AspectJ自动配置
   
   20、EnableLoadTimeWeaving
   
    启动类加载器动态增强功能，使用instrumentation实现
   
   21、@AutoConfigurationPackage
   
    包含该注解的package会被AutoConfigurationPackages注册
   
   22、@AutoConfigureBefore
   
    在指定配置类初始化前加载
   
   23、@AutoConfigureAfter
   
     在指定配置类初始化后加载
   
   24、@AutoConfigureOrder
   
    指定配置类初始化顺序，越小初始化越早
   
   25、@ModelAttribute
   
       @ModelAttribute注解可被应用在方法和方法参数上。
   
        （1）对方法使用 @ModelAttribute 注解：
   
            注解在方法上的@ModelAttribute说明了方法的作用是用于添加一个或多个属性到model上。这样的方法能接受与@RequestMapping注解相同的参数类型，只不过不能直接被映射到具体的请求上。
   
            @ModelAttribute 方法会先被调用。
   
            在同一个控制器中，注解了@ModelAttribute的方法实际上会在@RequestMapping方法之前被调用。以下是几个例子：
   
               @Controller
               public class ModelAttributeController {
                
                @ModelAttribute
                public void init(Model model) {
                    System.out.println("@RequestMapping方法");
                }
                
                @RequestMapping("/model-attribute")
                public String get() {
                    System.out.println("@ModelAttribute方法");
                
                    return "model-attribute";
                }
                
               }
               
            可以使用 @ModelAttribute 标注的方法来设置其他 @ReqeustMapping 方法的公用参数 
   
            使用 @ModelAttribute("key") 来显示指定属性名。
   
        （2）@ModelAttribute 和 @RequestMapping 注解在同一个方法上
   
            如果 @ModelAttribute 和 @RequestMapping 注解在同一个方法上，那么代表给这个请求单独设置 Model 参数。此时返回的值是 Model 的参数值，而不是跳转的地址。跳转的地址是根据请求的 url 自动转换而来的。比如下面的例子中跳转页面不是 HelloWorld.jsp 而是 model-attribute.jsp。并且参数只有在 model-attribute.jsp 中能够取得，而 demo.jsp 中不能取得。
   
        （3）在方法参数上使用 @ModelAttribute 注解
   
            ① 数据绑定
   
                注解在方法参数上的@ModelAttribute说明了该方法参数的值将由model中取得。如果model中找不到，那么该参数会先被实例化，然后被添加到model中。在model中存在以后，请求中所有名称匹配的参数都会填充到该参数中。这在Spring MVC中被称为数据绑定，一个非常有用的特性，节约了你每次都需要手动从表格数据中转换这些字段数据的时间。
   
            ② 和 BindingResult 配合使用
   
                使用 @ModelAttribute 进行数据绑定之后，可以使用 BindingResult 来返回数据验证结果。数据验证可以使用 hibernate validation 的 @Valid 标签或者 spring Validator 校验机制的 @Validated 配合 BindingResult 使用。 或者自定义校验器来返回 BindingResult 对象来进行校验。你可以通过Spring的 <errors> 表单标签来在同一个表单上显示错误信息。
   
                 @Valid 校验器：
   
                       @RequestMapping(path = "/owners/{ownerId}/pets/{petId}/edit", method = RequestMethod.POST)
                       public String processSubmit(@Valid @ModelAttribute("pet") Pet pet, BindingResult result) {
                        
                           if (result.hasErrors()) {
                               return "petForm";
                           }
                        
                           // ...
                        
                       }
                @Validated 校验器：
   
                       @RequestMapping(path = "/owners/{ownerId}/pets/{petId}/edit", method = RequestMethod.POST)
                       public String processSubmit(@Validated @ModelAttribute("pet") Pet pet, BindingResult result) {
                        
                           if (result.hasErrors()) {
                               return "petForm";
                           }
                        
                           // ...
                        
                       }
                自定义校验器：
   
                       @RequestMapping(path = "/owners/{ownerId}/pets/{petId}/edit", method = RequestMethod.POST)
                       public String processSubmit(@ModelAttribute("pet") Pet pet, BindingResult result) {
                        
                           // 自己编写一个校验方法来处理 result 对象
                           new PetValidator().validate(pet, result);
                           if (result.hasErrors()) {
                               return "petForm";
                           }
                        
                           // ...
                        
                       }
                       
   四、选择器
   
           @Conditional，当指定的条件都满足时，组件才被注册
           @ConditionalOnBean，指定bean在上下文中时，才注册当前bean。用在方法上，则默认依赖类为方法的返回类型
           @ConditionalOnClass，指定类在classpath上时，才初始化当前bean。用在方法上，则默认依赖类为方法的返回类型
           @ConditionalOnCloudPlatform，在指定云平台才注册配置
           @ConditionalOnExpression，指定spel为true时注册配置
           @ConditionalOnJava，在指定java版本时注册配置
           @ConditionalOnJndi
           @ConditionalOnMissingBean，指定bean不在上下文中时，才初始化当前bean。用在方法上，则默认依赖类为方法的返回类型
           @ConditionalOnMissingClass，指定类不在classpath上时，才初始化当前bean。用在方法上，则默认依赖类为方法的返回类型
           @ConditionalOnNotWebApplication，不是在web环境才注册配置
           @ConditionalOnProperty，配置文件中的值与指定值是否相等，相等才注册配置
           @ConditionalOnResource，指定resources都在classpath上才注册配置
           @ConditionalOnSingleCandidate，上下文中只有一个候选者bean时才注册配置
           @ConditionalOnWebApplication，是在web环境才注册配置
   
   五、缓存
   
           @EnableCaching，开启缓存配置，支持子类代理或者AspectJ增强
           @CacheConfig，在一个类下，提供公共缓存配置
           @Cacheable，放着方法和类上，缓存方法或类下所有方法的返回值
           @CachePut，每次先执行方法，再将结果放入缓存
           @CacheEvict，删除缓存
           @Caching，可以配置@Cacheable、@CachePut、@CacheEvict
   
   六、定时器
   
           @EnableScheduling，开启定时任务功能
           @Scheduled，按指定执行周期执行方法
           @Schedules，包含多个@Scheduled，可同时运行多个周期配置
           @EnableAsync，开启方法异步执行的能力，通过@Async或者自定义注解找到需要异步执行的方法。通过实现AsyncConfigurer接口的getAsyncExecutor()和getAsyncUncaughtExceptionHandler()方法自定义Executor和异常处理。
           @Async，标记方法为异步线程中执行
   
   七、注入配置文件properties
           
           @EnableConfigurationProperties，启动@ConfigurationProperties功能
           @ConfigurationProperties，将properties文件里的内容，自动注入bean对应的属性中
           @DeprecatedConfigurationProperty，用在配置文件的getter()方法上，标记字段已经过期，并提示替换的字段。一般给spring-boot-configuration-processor使用。
           @NestedConfigurationProperty，标记在配置文件的字段上，提示spring-boot-configuration-processor，配置包含嵌套的配置。
           spring-configuration-metadata.json 提供配置的元信息，在写properties配置时，会有语法提示。在项目中引入spring-boot-configuration-processor项目，会扫描@ConfigurationProperties注解，自动生成spring-configuration-metadata.json
           
   八、Jpa
   
   
   1、@Entity ，@Table(name="")
   
        表明这是一个实体类，一般用于jpa，这两个注解一块使用，但是如果表名和实体类名相同的话，@Table可以省略。
   
   2、@MappedSuperClass
   
        基于代码复用和模型分离的思想，在项目开发中使用jpa的@MappedSuperClass注解，将实体类的多个属性分别封装到不同的非实体类中。例如，数据库表中都需要id来表示编号，id是这些映射实体类的通用属性，交给jpa统一生产主键id编号，那么使用一个父类来封装这些通用属性，并用@MappedSuperClass标识。
   
    注意：
   
       标注为@MappedSuperClass的类将不是一个完整的实体类，它将不会映射到数据库表，但是它的属性都映射到其子类的数据库字段中。
       标注@MappedSuperClass的类不能再标注@#Entity或@Table注解，也无需实现序列化接口。
       
   3、@NoRepositoryBean
   
        一般用做父类的repository，有这个注解，spring不会去实例化该repository。
   
   4、@Column
   
        如果字段名和列名相同，则可以省略。
   
   5、@Id
   
        表示该属性为主键。
   
   6、@Transient
   
       表示该属性并非一个到数据库表的字段的映射，ORM框架将忽略该属性。
       
       如果一个属性并非数据库表的字段映射，就务必将其标注为@Transient，否则，ORM框架默认将其注解为@Basic。
   
   7、@Basic
   
        @Basic 是实体类与数据库字段映射时最简单的类型。
   
             类型支持Java基本类型（byte、short、int、long、float、double、char、boolean），包装类，枚举类，以及实现了serializable接口的类型。
   
        @basic注解有两个属性：
   
       fetch用来指定属性的加载机制
       有两个选项：EAGER（即时加载，默认值）和LAZY（懒加载），即时加载意味着当实例化对象的时候必须加载该属性值，懒加载是指当实例化对象时不加载该对象，只有当调用该属性时才加载。
       
       optional用来指定属性是否可空
       有两个选项：true（可空，默认值）和false
       
       如果你的实体类上不加@Basic注解，它也会自动加上@Basic，并使用默认值。
   
   8、@JsonIgnore
   
        在实体类向前台返回数据时用来忽略不想传递给前台的属性或接口。
   
        Bean实体中会有某些运维字段，返回信息给前台的时候，不希望将对应值一并返回。此时可以在对应属性上加上@JsonIgnore，或者可以在User类上加上注解@JsonIgnoreProperties(value="{password}")
   
   9、@JoinColumn、@OneToOne、@OneToMany、@ManyToOne
       
             
   
   
   
   九、导入配置文件
   
   1、@PropertySource
   
       引入单个properties文件：
       
       @PropertySource(value = {"classpath:xxxx/xxxx.properties"})
       引入多个properties文件
       
       @PropertySource(value = {"classpath:xxxx/xxxx.properties","classpath:xxxx/xxxx.properties"})
       
   2、 @ImportResource导入xml配置文件
   
       可以分为两种模式，相对路径的classpath，绝对路径的file。
       
       注意：单文件可以不写value或locations。
       
       取值：使用@Value注解取配置文件中的值
       
       @Value("${properties中的键}")        
       private String xxx;
       
   3、@Import导入额外的配置文件
   
       功能类似XML配置的，用来导入配置类，可以导入带有@Configuration注解的配置类或实现了ImportSelector/ImportBeanDefinitionRegistrar。
   
           @SpringBootApplication
           @Import({SmsConfig.class})
           public class DemoApplication {
               public static void main(String[] args) {
                   SpringApplication.run(DemoApplication.class, args);
               }
           }
   十、事务注解
   
   @Transactional
   
       在Spring中，事务有两种实现，分别是编程式事务和声明式事务。
       
       编程式事务：
       
       编程式事务使用TransationTemplate或者直接使用底层的PlatformTransactionManager。对于编程式事务，spring推荐使用TransationTemplate。
       
       声明式事务：
       
       建立在AOP基础上，其本质是对方法前后进行拦截，然后再目标方法开始之前创建或者加入一个事务，在执行完目标方法之后根据执行情况提交或者回滚事务，通过@Transactional就可以进行事务操作，更快捷而且简单。推荐使用。
   
   十一、Spring Cloud
   
   1、@EnableEurekaServer
   
    用在springboot启动类上，表示这是一个eureka服务注册中心；
   
   2、@EnableDiscoveryClient
   
    用在springboot启动类上，表示这是一个服务，可以被注册中心找到；
   
   3、@LoadBalanced
   
    开启负载均衡能力；
   
   4、@EnableCircuitBreaker
   
    用在启动类上，开启断路器功能；
   
   5、@HystrixCommand(fallbackMethod=”backMethod”)
   
    用在方法上，fallbackMethod指定断路回调方法；
   
   6、@EnableConfigServer
   
    用在启动类上，表示这是一个配置中心，开启Config Server；
   
   7、@EnableZuulProxy
   
    开启zuul路由，用在启动类上；
   
   8、@SpringCloudApplication
   
       @SpringBootApplication
       @EnableDiscovertyClient
       @EnableCircuitBreaker
       分别是SpringBoot注解、注册服务中心Eureka注解、断路器注解。对于SpringCloud来说，这是每一微服务必须应有的三个注解，所以才推出了@SpringCloudApplication这一注解集合。
   
   9、@ConfigurationProperties
   
    （1）@ConfigurationProperties注解简介
   
         Spring源码中大量使用了ConfigurationProperties注解，比如server.port就是由该注解获取到的，通过与其他注解配合使用，能够实现Bean的按需配置。 
   
         该注解有一个prefix属性，通过指定的前缀，绑定配置文件中的配置，该注解可以放在类上，也可以放在方法上。
   
    （2）代码实例
   
       spring.datasource.url=jdbc:mysql://127.0.0.1:8888/test?useUnicode=false&autoReconnect=true&characterEncoding=utf-8
       spring.datasource.username=root
       spring.datasource.password=root
       spring.datasource.driver-class-name=com.mysql.jdbc.Driver
       spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
       
       
           @ConfigurationProperties(prefix = "spring.datasource")
           @Component
           public class DatasourcePro {
               private String url;
               private String username;
               private String password;
               // 配置文件中是driver-class-name, 转驼峰命名便可以绑定成
               private String driverClassName;
               private String type;
            
               ...
           }
           
         
         以上代码就实现了通过配置文件对属性的赋值。
           
    3）注意事项
   
       @ConfigurationProperties 和 @value 有着相同的功能，但是 @ConfigurationProperties的写法更为方便；
       @ConfigurationProperties 的 POJO类的命名比较严格,因为它必须和prefix的后缀名要一致, 不然值会绑定不上, 特殊的后缀名是“driver-class-name”这种带横杠的情况,在POJO里面的命名规则是 下划线转驼峰 就可以绑定成功，所以就是 “driverClassName”。
