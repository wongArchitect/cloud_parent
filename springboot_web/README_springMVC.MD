参考网页

    springMVC请求流程详解
        https://www.cnblogs.com/leskang/p/6101368.html
        
        
一、springMVC请求流程详解
  
            SpringMVC框架是一个基于请求驱动的Web框架，并且使用了‘前端控制器’模型来进行设计，再根据‘请求映射规则’分发给相应的页面控制器进行处理。
        
        注：清晰的角色划分
            前端控制器（DispatcherServlet）、请求到处理器映射（HandlerMapping）、处理器适配器（HandlerAdapter）、视图解析器（ViewResolver）、
            处理器或页面控制器（Controller）、验证器（ Validator）、命令对象（Command  请求参数绑定到的对象就叫命令对象）、
            表单对象（Form Object 提供给表单展示和提交到的对象就叫表单对象）。
     
   1、整体流程
   
        具体步骤：
   
           1）  用户发送请求，前端控制器(DispatcherServlet)接收请求并通过处理器映射器（HandlerMapping）和处理器适配器（HandlerAdapter）找到对应的页面控制/处理器（Controller）做数据操作。
                首先用户发送请求到前端控制器，前端控制器根据请求信息（如 URL）来决定选择哪一个页面控制器进行处理并把请求委托给它，即以前的控制器的控制逻辑部分；图中的 1、2 步骤；
           2）  页面控制/处理器（controller）处理完后返给前端控制器一个ModelAndView。
                页面控制器接收到请求后，进行功能处理，首先需要收集和绑定请求参数到一个对象，这个对象在 Spring Web MVC 中叫命令对象，并进行验证，然后将命令对象委托给业务对象进行处理；处理完毕后返回一个 ModelAndView（模型数据和逻辑视图名）；图中的 3、4、5 步骤；
           3）  前端控制器把ModelAndView交给视图解析器（ViewResolver）做视图渲染返回View。
                前端控制器收回控制权，然后根据返回的逻辑视图名，选择相应的视图进行渲染，并把模型数据传入以便视图渲染；图中的步骤 6、7；
           4）  前端控制器把渲染好的页面填充数据后返回给用户展示。
                前端控制器再次收回控制权，将响应返回给用户，图中的步骤 8；至此整个结束。  
           
   2、具体步骤：
     
         第一步：发起请求到前端控制器(DispatcherServlet)
         
         第二步：前端控制器请求HandlerMapping查找 Handler （可以根据xml配置、注解进行查找）
         
         第三步：处理器映射器HandlerMapping向前端控制器返回Handler，HandlerMapping会把请求映射为HandlerExecutionChain对象（包含一个Handler处理器（页面控制器）对象，多个HandlerInterceptor拦截器对象），通过这种策略模式，很容易添加新的映射策略
         
         第四步：前端控制器调用处理器适配器去执行Handler
         
         第五步：处理器适配器HandlerAdapter将会根据适配的结果去执行Handler
         
         第六步：Handler执行完成给适配器返回ModelAndView
         
         第七步：处理器适配器向前端控制器返回ModelAndView （ModelAndView是springmvc框架的一个底层对象，包括 Model和view）
         
         第八步：前端控制器请求视图解析器去进行视图解析 （根据逻辑视图名解析成真正的视图(jsp)），通过这种策略很容易更换其他视图技术，只需要更改视图解析器即可
         
         第九步：视图解析器向前端控制器返回View
         
         第十步：前端控制器进行视图渲染 （视图渲染将模型数据(在ModelAndView对象中)填充到request域）
         
         第十一步：前端控制器向用户响应结果
     
 3、总结 核心开发步骤
 
           1）  DispatcherServlet 在 web.xml 中的部署描述，从而拦截请求到 Spring Web MVC
           
           2）  HandlerMapping 的配置，从而将请求映射到处理器
           
           3）  HandlerAdapter 的配置，从而支持多种类型的处理器
           
                注：处理器映射求和适配器使用纾解的话包含在了注解驱动中，不需要在单独配置
           
           4）  ViewResolver 的配置，从而将逻辑视图名解析为具体视图技术
           
           5）  处理器（页面控制器）的配置，从而进行功能处理 
       
   
         注：View是一个接口，实现类支持不同的View类型（jsp、freemarker、pdf...）                  