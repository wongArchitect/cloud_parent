
 
一、POM不加版本号解析
    
        POM文件如何做到不加版本号，会引入相应版本的jar包
        
        原文链接：
                https://blog.csdn.net/Exiler_gusu/article/details/103357162

        
   1、 使用<dependencyManagement>标签的scope属性：
   
            <--  不使用spring-boot-starter-parent，使用<dependencyManagement>标签的scope属性来定制统一一致的版本(号)。 -->
                	<dependencyManagement>
                		<dependencies>
                			<dependency>
                				<groupId>org.springframework.boot</groupId>
                				<artifactId>spring-boot-dependencies</artifactId>
                				<version>2.2.2.RELEASE</version>
                				<type>pom</type>
                				<scope>import</scope>
                			</dependency>
                		</dependencies>
                	</dependencyManagement>
   
   2、引入spring-boot-starter-parent父jar包：内置<dependencyManagement>标签的scope属性。
   
            <--  父项目或本子项目的pom.xml文件中引入  -->
            <!-- Inherit defaults from Spring Boot -->
            <parent>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-parent</artifactId>
                <version>2.2.1.RELEASE</version>
            </parent>  
            
            
            jar包剖析：
            
            spring-boot-starter-parent-2.2.1.RELEASE.pom以spring-boot-dependencies作为父pom。
            进入spring-boot-dependencies.pom文件中我们会发现一个<dependencyManagement>元素。

            <dependencyManagement>
                <dependencies>
                  <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot</artifactId>
                    <version>2.2.1.RELEASE</version>
                  </dependency>
                  ......
                  <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-web</artifactId>
                    <version>2.2.1.RELEASE</version>
                  </dependency>
                  ......
                </dependencies>
            </dependencyManagement>
    
   注: 子项目覆盖与需显示声明：dependencyManagement只做依赖的声明；子项目还需显示声明，也可有自己的版本来覆盖父项目。
    
            如果子pom文件中指定jar的版本号，子pom中的版本会覆盖父pom中声明的版本。而且dependencyManagement只是做依赖的声明，并不做引入操作，所以子项目中还需要显式的声明依赖的jar

