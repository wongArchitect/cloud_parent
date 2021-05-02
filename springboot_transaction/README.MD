参考网页


   SpringBoot整合Mybatis，并实现事务控制： 
    
        https://blog.csdn.net/weixin_30677617/article/details/96351959
    
   一、加入jar包：此项目是cloud_parent的子模块，所以继承了cloud_parent父模块的jar包。
            
         只需添加
            <dependencies>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-jdbc</artifactId>
                </dependency>
        
                <dependency>
                    <groupId>mysql</groupId>
                    <artifactId>mysql-connector-java</artifactId>
                    <version>5.1.21</version>
                </dependency>
        
                <dependency>
                    <groupId>org.mybatis.spring.boot</groupId>
                    <artifactId>mybatis-spring-boot-starter</artifactId>
                    <version>1.3.2</version>
                </dependency>
            </dependencies>

   
   二、添加DataSource：此处用properties属性文件
        
        spring.datasource.url=jdbc:mysql://localhost:3306/springboot_demo
        spring.datasource.username=root
        spring.datasource.password=root
        spring.datasource.driver-class-name=com.mysql.jdbc.Driver
   
   三、数据库
   
   1、加上事务管理器，数据可以回滚.
        
        以及其他事务控制
   
   2、Idea操作数据库：可右键properties（属性）选择jump to control（打开控制台）用sql语句操作
   
    1）点击Idea右侧栏的Database，打开数据库管理窗口
    
       bug:连接测试不成功
         Server returns invalid timezone. Go to 'Advanced' tab and set 'serverTimezone' property manually.
       解决方法：右键连接名称“springboot_demo@localhost”--->properties（属性）----> General---->下面一行的Driver：MySQL --->选择 MySQL for 5.1
        
    2）点击属性，选择schemas，选择当前数据库
    
    3）创建table（表）
    
    4)右键properties（属性）选择jump to control（打开控制台）用sql语句操作
        
        插入数据
        insert into tbl_account(id,name,balance) values(1, 'andy','200');
        insert into tbl_account(id,name,balance) values(2, 'lucy','300');
        
        查看数据是否已插入
        select * from tbl_account;
           
   三、 Idea右侧栏的Database自动生成文件
   
   四、配置：
            
         1、启动类添加扫描
         2、属性文件
            mybatis.typ-eAliases-Package = com.mn.trans.pojo
            mybatis.mapper-Locations = classpath:mapper/*.xml加

   五、测试：
        
          http://localhost:8080/account/transfer
          
       数据库数据正确，转账成功了             
   
   六、添加事务并测试效果：@EnableAutoConfiguration + @Transactional
        
        1、添加
        
        启动类添加 @EnableAutoConfiguration
        Service实现类加 @Transactional
        
        测试加个异常可抛出
                
        2、应该都成功或者都不成功：在service实现类上添加@Transactional 注解来声明一个事务，抛出一个异常，测试数据是否回滚。
   
            ..............
            
            @Transactional
                  public void transfer(int outter, int inner, Integer money) {
                       accountDao.moveOut(outter, money); //转出
                       int i = 1/0;  // 抛出异常
                       
            ..............
    
          
   
   
   
   总结：
   
           jar包
           属性文件
           注解（启动类和Dao）
           数据库与自动生成的文件
           controller和Service
           
   
   注：
   1、不使用@Mapper也是可以的。
   
       1）不写mapper注解也可以是因为已经通过xml文件指定了mapper文件。并且SqlSessionFactory通过setMapperLocations方法指定了扫描的xml文件的位置 。并且使用了MapperScannerConfigurer这个类之后就能达到不使用注解的效果了。
          /**
          * 添加了@Mapper注解之后这个接口在编译时会生成相应的实现类
          * 
          * 需要注意的是：这个接口中不可以定义同名的方法，因为会生成相同的id
          * 也就是说这个接口是不支持重载的
          */
          
        2）mapper注解和你使用mybatis时的配置作用基本是等价的。
            接口层加mapper注解或者springboot项目在启动类通过mapperScan指定dao层接口的路径。
            比你每增加一个mapper接口都要加一行配置要省事的多。
            
        3）省事：加@Mapper可省去XML映射文件。
            加上@Mapper，可省略xml映射文件，甚至有的说就是为了省去映射文件的。
            
        4）@Mapper的类型要加对了，可能不是正确的jar包
            正确的应该是：import org.apache.ibatis.annotations.Mapper;

   
   2、Mybatis配置
   
       1) DataSource的配置
       
       spring.datasource.url=jdbc:mysql://localhost:3306/springboot_demo
       spring.datasource.username=root
       spring.datasource.password=root
       spring.datasource.driver-class-name=com.mysql.jdbc.Driver
       
       2）启动类中的配置：@MapperScan(value = {"com.mn.trans.dao"})
            
            1>扫描的是包名
            2>（）中直接写包名或正则表达式；或者，使用value={"xxx.xxx.xxx"} 或 basePackages = {"xxx.xxx.xxx"}
            
       2)属性文件中实体与映射文件的配置：注意是有“-”的
       
            mybatis.typ-eAliases-Package = com.mn.trans.pojo
            mybatis.mapper-Locations = classpath:mapper/*.xml
            
   2、启动类的配置
       
       @SpringBootApplication 
            等价于 
       @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
       @EnableAutoConfiguration
       
       
   3、Bug：mybatis-spring 1.2版本问题，缺少sqlSessionFactory 或者是  sqlSessionTemplate
        
            by: java.lang.IllegalArgumentException: Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required at org.springframework.util.Assert.notNull(Assert.java:112)
     
         异常的意思是  缺少sqlSessionFactory 或者是  sqlSessionTemplate
         
         有的dao层利用继承SqlSessionDaoSupport  然后可以直接通过this.getSqlSession() 来进行数据库的操作。
         后来通过搜索以及查看源代码发现，mybatis-spring 1.2版本没有自动注入sqlSessionFactory 
         
         解决方法是手动注入     
         复制如下代码
             public class BaseDaoImpl extends SqlSessionDaoSupport {
              
                  @Autowired
                  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){  
                          super.setSqlSessionFactory(sqlSessionFactory);  
                  }
             }
     
         然后在将dao实现类继承BaseDaoImpl就可以使用了
     
     
   4、@Mpper和@Repository的区别：@Mapper近似于@Repository+包扫描的轻量级实现
   
       1) @Mapper不需要配置扫描地址，可以单独使用，如果有多个mapper文件的话，可以在项目启动类中加入@MapperScan(“mapper文件所在包”)，这样就不需要每个mapper文件都加@Mapper注解了
       2) @Repository不可以单独使用，否则会报如下错误
            Field userMapper in com.liu.service.UserServiceImpl required a bean of type 'com.liu.mapper.UserMapper' that could not be found.
         找不到bean，这是因为项目启动的时候没有去扫描使用@Repository注解的文件，所以使用@Repository需要配置扫描地址
    
       3) 但在idea中，使用@Repository可以消除在业务层中注入mapper对象时的错误，如，@Autowired注解的变量报红。


5、事务失效的场景：    
    
    1、数据库不支持：可不用默认的引擎；实在不行，就重新安装或换版本。注意，msi、exe和免安装三种安装方式(用“sc delete 服务名”命令来删除MySQL服务。
    
       事务生效的前提是你的数据源得支持事务，比如 mysql 的 MyISAM 引擎就不支持事务，而 Innodb 支持事务
       
       1）查看引擎
       1> 查看有那些引擎：show engines；
       2> 查看默认引擎
          show variables like "default_storage_engine"
          
       2） 修改 mysql 默认的数据库引擎
       　 1>打开配置文件 my.ini，将“default-storage-engine=MYISAM”改为你想设定的，这里我们设定为 InnoDB，
             注意，在修改之前先备份一下配置文件
          2>还有一步比较好重要：
             找到 skip-innodb 项，将其改为 #skip-innodb（要不然知识修改了 InnoDB 服务起不来）
          3>之后重启电脑即可。
          
       3)查看表使用的存储引擎两种方法：
       
       a、show table status from db_name where name='table_name';
       b、show create table table_name;

    2、类内部访问：

        需要写到两个类中才可。

    3、私有方法：

        只针对Public方法生效

    4、异常不匹配：

         @Transactional注解默认处理运行时异常，即只有抛出运行时异常时，才会触发事务回滚，否则并不会。
         
         @Transactional只会监听RuntimeException，
         加上rollbackFor = Exception.class，可以根据业务中的异常回滚
         @Transactional(rollbackFor = Exception.class)

    5、多线程

    6、传播属性导致的
    
    7、MySQL服务出错：有可能安装了多个MySQL或连接的MySQL服务器不对。
          我这次就因此问题整了半天。
