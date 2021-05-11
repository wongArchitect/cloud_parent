# 工程简介

原博文：登录CSDN，SpringCloud收藏夹，打开“maven多模块构建springcloud项目”

# 延伸阅读

一、自动部署

        小积累
   1、子模块
   
        idea菜单创建方式创建子模块（子项目），此父项目为Maven项目，子模块也应该以maven创建才会自动设置成子模块（子项目）的结构。
        子模块（子项目）创建完成后再添加springboot所需的文件。
    
   2、Jenkins自动部署
   
        Jenkins自动部署，只要可以构建打包（Build），然后用bat命令运行所要运行的项目jar包即可。
    
   3、springCloud
   
        springCloud管理的是uri（URL）和属性文件等。


二、 总结一下SpringCloud结果核心组件:
   
           Eureka:服务发现。
                个服务启动时,Eureka会将服务注册到EurekaService,并且EurakeClient还可以返回过来从EurekaService拉去注册表,从而知道服务在哪里
           
           Ribbon:负载均衡。
                服务间发起请求的时候,基于Ribbon服务做到负载均衡,从一个服务的对台机器中选择一台
           
           Feign:服务调用。
                基于fegin的动态代理机制,根据注解和选择机器,拼接Url地址,发起请求
           
           Hystrix:服务熔断。
                发起的请求是通过Hystrix的线程池来走,不同的服走不同的线程池,实现了不同的服务调度隔离,避免服务雪崩的问题 
           
           Zuul:服务网关。
                如果前端、后端、移动端调用后台系统,统一走zull网关进入,有zull网关转发请求给对应的服务

        注：原博文地址
       
                https://blog.csdn.net/weixin_35650916/article/details/103871946
                
三、springboot启动流程

        博文地址：
                https://blog.csdn.net/u010811939/article/details/80592461
                
     1、流程描述：SpringApplication的实例与run()方法、监听、事件、环境信息、上下文。    
        
        构造SpringApplication的实例（时序图步骤1-2）
        调用SpringApplication.run()方法（时序图步骤3）
        构造SpringApplicationRunListeners 实例（时序图步骤3.1.1）
        发布ApplicationStartedEvent事件（时序图步骤3.1.2）
        SpringApplicationRunListeners 实例准备环境信息（时序图步骤3.1.3）
        创建ApplicationContext对象（时序图步骤3.1.4）
        ApplicationContext实例准备环境信息（时序图步骤3.1.5）
        刷新的上下文（时序图步骤3.1.6）

     2、在doc文件夹中有“时序图”。
     
            doc/springboot/springboot启动流程.png               
                        

四、git 使用 积累
 
     git 取消提交（add）操作、未pull便push的bug、git取消修改，恢复版本 命令大全
     
        注：一般用户，Git提交的单个文件不得大于100M

   1、git 取消提交（add）操作：通过回退版本实现。
   
     1）查看版本号：
        
        git log

     2）执行回退命令：

        git reset --soft 6fc75f3291c661abdca154ac8d640977a8c97e4b

     3）更新最新版本：注意查看文件是否有误
     
        git pull origin master

     4）推到远程仓库
     
        git push origin

   2、Bug：未pull便push
   
      1） 在remote add后不要着急git add，一定要git pull origin master，否则会出现下面这个错误信息

            ! [rejected]        master -> master (non-fast-forward)
            error: failed to push some refs to 'https://github.com/wongArchitect/txtExport.git'

      2） 解决方法
      
            pull（拉取）下，再push（推）到远程仓库即可。
            
            注：当然，很有可能会产生冲突，可以试试清除缓存区，看着解决吧。


   3、git 取消修改，恢复版本 命令大全

        #取消对文件的修改。还原到最近的版本，废弃本地做的修改。
        git checkout -- <file>
        
        #取消已经暂存的文件。即，撤销先前"git add"的操作
        git reset HEAD <file>...
        
        #修改最后一次提交。用于修改上一次的提交信息，或漏提交文件等情况。
        git commit --amend
        
        #回退所有内容到上一个版本
        git reset HEAD^
        
        #回退a.py这个文件的版本到上一个版本  
        git reset HEAD^ a.py  
        
        #向前回退到第3个版本  
        git reset –soft HEAD~3  
        
        #将本地的状态回退到和远程的一样  
        git reset –hard origin/master  
        
        #回退到某个版本  
        git reset 057d  
        
        #回退到上一次提交的状态，按照某一次的commit完全反向的进行一次commit.(代码回滚到上个版本，并提交git)
        git revert HEAD

   4、一般用户，Git提交的单个文件不得大于100M。
   

三、@Scheduled注解：定时任务

           1、 入口程序ScheduledApplication添加注解@EnableScheduling
           
           2、创建定时任务类添加@Compoment注解将类纳入spring bean管理
           
           3、创建方法添加@Scheduled注解配置cron定时属性
           
        注： 属于spring框架的功能，引入基础的spring包或springboot包即可。
        
        